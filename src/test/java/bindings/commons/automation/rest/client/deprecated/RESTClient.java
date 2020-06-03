package bindings.commons.automation.rest.client.deprecated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ipfdigital.automation.kibanalogger.KibanaLogger;
import com.ipfdigital.automation.kibanalogger.KibanaLoggerDetails;
import com.ipfdigital.automation.rest.client.RequestType;
import com.ipfdigital.automation.rest.client.deprecated.exceptions.RESTClientException;
import com.ipfdigital.automation.rest.client.exceptions.HttpClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @deprecated
 *
 *             To handle GET/PUT/POST messages, please use "ApacheClient" from
 *             "com.ipfdigital.rest.client" package
 */
@Deprecated
public class RESTClient {

  private static final Logger LOGGER = LogManager.getLogger(RESTClient.class);

  private final String host;
  private static final List<Integer> ACCEPTED_RESPONSE_CODES = Arrays.asList(200, 201, 204, 303);
  private static final int MAX_REQUEST_RESEND_ATTEMPTS = 5;

  private Map<String, String> statusData = new HashMap<>();

  private KibanaLogger kibanaLogger = new KibanaLogger();

  protected final ObjectMapper mapper =
      new ObjectMapper ().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .setSerializationInclusion(JsonInclude.Include.NON_NULL)
          .registerModule(new JavaTimeModule ())
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final String LOG_BODY_MESSAGE = "Sending request body:\n{}";

  public void setKibanaLogger(final KibanaLogger kibanaLogger) {
    this.kibanaLogger = kibanaLogger;
  }

  protected Map<String, String> headers = new HashMap<>();

  public void addHeaders(final Map<String, String> newHeaders) {
    headers.putAll(newHeaders);
  }

  public void addHeader(final String key, final String value) {
    headers.put(key, value);
  }

  public RESTClient(final String host) {
    this.host = host;
  }

  public String getHost() {
    return host;
  }

  protected String sendRequest(final RequestType type, final String address,
      final Map<String, String> headers)
      throws IOException {
    return sendRequest(type, address, headers, null);
  }

  public String sendFormRequest(final String address, final Map<String, String> headers,
      final Map<String, String> values) throws IOException {
    final byte[] postData = formDataBytes(values);
    final String message = String.format("Sending POST request to %s/%s...", host, address);
    LOGGER.info(message);
    final URL url = new URL(host + "/" + address);
    final HttpURLConnection connection = prepareConnection(url, "POST", false, false, true, false);
    setFormRequestProperties(connection, postData);
    addHeaders(connection, headers);
    try (final DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
      outputStream.write(postData);
    }

    createAndSaveKibanaLoggerDetails(message);
    handleResponseStatus(address, connection, getStringErrorMsg(connection));
    return handleSessionCookie(connection);
  }

  private String getStringErrorMsg(final HttpURLConnection connection) {
    if (connection.getErrorStream() != null) {
      final String errorMsg = getStringError(connection);
      LOGGER.info("Response error: " + errorMsg);
      return errorMsg;
    }
    return "Error message was not provided to HTTP response";
  }

  private byte[] formDataBytes(final Map<String, String> parameters) {
    final String urlParameters = parameters.entrySet().stream()
        .map(this::encodeParams)
        .collect(Collectors.joining("&"));
    LOGGER.debug("Form data parameters: {}", urlParameters);
    return urlParameters.getBytes(StandardCharsets.ISO_8859_1);
  }

  private String encodeParams(final Map.Entry<String, String> pair) {
    try {
      return URLEncoder.encode(pair.getKey(), "ISO-8859-1") + "="
          + URLEncoder.encode(pair.getValue(), "ISO-8859-1");
    } catch (final UnsupportedEncodingException e) {
      LOGGER.warn("Unable to encode post parameters!", e);
      return pair.getKey() + "=" + pair.getValue();
    }
  }

  private void setFormRequestProperties(final HttpURLConnection connection, final byte[] postData) {
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
  }

  protected String sendRequest(final RequestType type, final String address,
      final Map<String, String> headers,
      final String body) throws IOException {
    final String message = String.format("Sending %s request to %s/%s", type, host, address);
    LOGGER.info(message);
    int retries = MAX_REQUEST_RESEND_ATTEMPTS;
    while (retries > 0) {
      final HttpURLConnection connection = setupConnection(address, type);
      final int responseCode = handleConnection(connection, headers, address, message, body);
      if (ACCEPTED_RESPONSE_CODES.contains(responseCode)) {
        return handleSessionCookie(connection);
      } else {
        retryConnectionMsg(responseCode);
        retries--;
      }
    }
    throw new IllegalAccessError("Unable to send request!");
  }

  private int handleConnection(final HttpURLConnection connection,
      final Map<String, String> headers,
      final String address, final String message, final String body) throws IOException {
    addHeaders(connection, headers);
    writeBodyIfExists(connection, body);
    final int responseCode = connection.getResponseCode();
    LOGGER.info("Response message: " + connection.getResponseMessage());

    createAndSaveKibanaLoggerDetails(message + " Body: " + body);
    handleResponseStatus(address, connection, getStringErrorMsg(connection));
    return responseCode;
  }

  private void createAndSaveKibanaLoggerDetails(final String message) {
    final KibanaLoggerDetails kibanaLoggerDetails =
        new KibanaLoggerDetails(kibanaLogger.kibanaLoggerWithNextProcessStep());
    kibanaLoggerDetails.setMessage(message);
    kibanaLoggerDetails.save();
  }

  private HttpURLConnection setupConnection(final String address, final RequestType type)
      throws IOException {
    final URL url = new URL(host + "/" + address);
    return prepareConnection(url, String.valueOf(type), true, true, true, true);
  }

  private void writeBodyIfExists(final HttpURLConnection connection, final String body)
      throws IOException {
    if (body != null && !body.isEmpty()) {
      try (final OutputStreamWriter writer =
          new OutputStreamWriter(connection.getOutputStream(), DEFAULT_CHARSET)) {
        writer.write(body);
      }
      LOGGER.info(LOG_BODY_MESSAGE, body);
    }
  }

  private void retryConnectionMsg(final int responseCode) {
    final String wMessage = String.format("Received %s code!. Retrying.", responseCode);
    LOGGER.warn(wMessage);
  }

  private void useJSONinConnection(final HttpURLConnection connection) {
    connection.setRequestProperty("Accept", "application/json");
    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
  }

  /**
   * To be replaced by some out-of-the-box JSON builder.
   */
  public static String buildRequestBody(final Map<String, String> values) {
    return "{" +
        values.entrySet().stream()
            .map(entry -> String.format(
                entry.getValue().startsWith("{") || entry.getValue().startsWith("[") ? "\"%s\": %s"
                    : "\"%s\": \"%s\"",
                entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(", "))
        +
        "}";
  }

  public String getStringResponse(final RequestType type, final String address,
      final Map<String, String> headers,
      final String body) throws IOException {
    LOGGER.info("Sending {} request to {}/{}...", type, host, address);
    final URL url = new URL(host + "/" + address);
    final HttpURLConnection connection =
        prepareConnection(url, String.valueOf(type), true, true, true, true);
    addHeaders(connection, headers);
    writeBodyIfExists(connection, body);
    handleResponseStatus(address, connection, getStringErrorMsg(connection));
    handleSessionCookie(connection);
    return getStringResponse(connection);
  }

  private <T> List<T> getObjectListFromRequest(final RequestType type, final String address,
      final Map<String, String> headers, final String body, final Class<T> objectClass)
      throws IOException {

    return mapper.readValue(getStringResponse(type, address, headers, body),
        mapper.getTypeFactory().constructCollectionType(List.class, objectClass));

  }

  protected <T> T getObjectFromRequest(final RequestType type, final String address,
      final Map<String, String> headers, final String body, final Class<T> objectClass)
      throws IOException {

    return mapper.readValue(getStringResponse(type, address, headers, body), objectClass);
  }

  /**
   * Sends an empty GET request and reads objects from the response
   */
  protected <T> List<T> getObjectListFromRequest(final String address,
      final Map<String, String> headers,
      final Class<T> objectClass) throws IOException {
    return getObjectListFromRequest(RequestType.GET, address, headers, null, objectClass);
  }

  /**
   * Sends an empty GET request and reads a single object from the response
   */
  public <T> T getObjectFromList(
      final String address, final Map<String, String> headers, final Class<T> objectClass)
      throws IOException {
    final List<T> objects = getObjectListFromRequest(address, headers, objectClass);
    if (objects.isEmpty()) {
      throw new HttpClientException("Received list of " + objectClass + " is empty!");
    }
    return objects.get(0);
  }

  // FROM SERVICES TESTS

  private HttpURLConnection prepareConnection(final URL url, final String requestType,
      final boolean useJson,
      final boolean input, final boolean output, final boolean redirect) throws IOException {
    final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod(requestType);
    if (useJson)
      useJSONinConnection(connection);
    if (input)
      connection.setDoInput(true);
    connection.setDoOutput(output);
    if (!redirect)
      connection.setInstanceFollowRedirects(false);
    return connection;
  }

  public HttpURLConnection openConnection(final String type, final String address)
      throws IOException {
    return openConnection(type, address, null, null, true);
  }

  public HttpURLConnection openConnection(final String type, final String address,
      final Map<String, String> headers,
      final String body, final boolean redirect) throws IOException {
    final URL url = new URL(host + "/" + address);
    final HttpURLConnection connection = prepareConnection(url, type, true, true, true, redirect);
    addHeaders(connection, headers);
    if (body != null && !body.isEmpty()) {
      final OutputStreamWriter writer =
          new OutputStreamWriter(connection.getOutputStream(), DEFAULT_CHARSET);
      writer.write(body);
      writer.close();
      LOGGER.info(LOG_BODY_MESSAGE, body);
    }
    logHeaders(connection);
    return connection;
  }

  private static HttpURLConnection addHeaders(final HttpURLConnection connection,
      final Map<String, String> headers) {
    if (headers != null) {
      headers.forEach(connection::setRequestProperty);
    }
    return connection;
  }

  public static String getStringResponse(final HttpURLConnection connection) throws IOException {
    final BufferedReader reader =
        new BufferedReader(new InputStreamReader(connection.getInputStream()));
    final StringBuilder builder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line);
    }
    reader.close();
    return builder.toString();
  }

  private static void logHeaders(final HttpURLConnection connection) {
    connection.getHeaderFields()
        .forEach((key, value) -> LOGGER.info(String.format("Header - %s: %s", key, value)));
  }

  public static List<String> getHeadersByName(final HttpURLConnection connection,
      final String headerName) {
    return connection.getHeaderFields().get(headerName);
  }

  private void handleResponseStatus(
      final String address, final HttpURLConnection connection, final String errorMsg)
      throws IOException {
    LOGGER.info("Response code: {}", connection.getResponseCode());
    if (!ACCEPTED_RESPONSE_CODES.contains(connection.getResponseCode())) {
      final String message =
          String.format("Error %s connecting to service: %s! %n %s %n Response errorMsg: %s",
              connection.getResponseCode(), address, getStatusDataAsHtml(), errorMsg);
      LOGGER.error(message);
      createAndSaveKibanaLoggerDetails(message);
      throw new RESTClientException(message, connection.getResponseCode());
    }
  }

  private static String getStringError(final HttpURLConnection connection) {
    final BufferedReader reader =
        new BufferedReader(new InputStreamReader(connection.getErrorStream()));
    return reader.lines().collect(Collectors.joining());
  }

  private String handleSessionCookie(final HttpURLConnection connection) {
    String sessionId = null;
    final List<String> sessionCookie = connection.getHeaderFields().get("Set-Cookie");
    if (sessionCookie == null && headers != null) {
      sessionId = headers.get("Cookie");
    } else if (sessionCookie != null) {
      sessionId =
          sessionCookie.stream().filter(c -> c.startsWith("JSESSIONID") || c.startsWith("AWSELB"))
              .map(c -> c.split(";")[0])
              .collect(Collectors.joining("; "));
    }
    LOGGER.info("Session ID: " + sessionId);
    return sessionId;
  }

  public Map<String, String> getStatusData() {
    return statusData;
  }

  public void setStatusData(final Map<String, String> statusData) {
    this.statusData = statusData;
  }

  public void addStatusData(final String key, final String value) {
    statusData.put(key, value);
  }

  private String getStatusDataAsHtml() {
    final StringBuilder statusDataAsString = new StringBuilder();
    statusDataAsString.append("<br>");
    for (final Object status : statusData.entrySet()) {
      final Map.Entry pair = (Map.Entry) status;
      statusDataAsString.append("<p>");
      statusDataAsString.append(pair.getKey());
      statusDataAsString.append(" : ");
      statusDataAsString.append(pair.getValue());
      statusDataAsString.append("<p>");

    }
    return statusDataAsString.toString();
  }
}
