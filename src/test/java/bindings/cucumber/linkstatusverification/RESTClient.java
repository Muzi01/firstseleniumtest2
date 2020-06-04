package bindings.cucumber.linkstatusverification;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/** @deprecated */
@Deprecated
public class RESTClient {
    private static final Logger LOGGER = LogManager.getLogger(RESTClient.class);
    private final String host;
    private static final List<Integer> ACCEPTED_RESPONSE_CODES = Arrays.asList(200, 201, 204, 303);
    private static final int MAX_REQUEST_RESEND_ATTEMPTS = 5;
    private Map<String, String> statusData = new HashMap();
    private KibanaLogger kibanaLogger = new KibanaLogger();
    protected final ObjectMapper mapper;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String LOG_BODY_MESSAGE = "Sending request body:\n{}";
    protected Map<String, String> headers;

    public void setKibanaLogger(KibanaLogger kibanaLogger) {
        this.kibanaLogger = kibanaLogger;
    }

    public void addHeaders(Map<String, String> newHeaders) {
        this.headers.putAll(newHeaders);
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public RESTClient(String host) {
        this.mapper = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setSerializationInclusion(Include.NON_NULL).registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.headers = new HashMap();
        this.host = host;
    }

    public String getHost() {
        return this.host;
    }

    protected String sendRequest(RequestType type, String address, Map<String, String> headers) throws IOException {
        return this.sendRequest(type, address, headers, (String)null);
    }

    public String sendFormRequest(String address, Map<String, String> headers, Map<String, String> values) throws IOException {
        byte[] postData = this.formDataBytes(values);
        String message = String.format("Sending POST request to %s/%s...", this.host, address);
        LOGGER.info(message);
        URL url = new URL(this.host + "/" + address);
        HttpURLConnection connection = this.prepareConnection(url, "POST", false, false, true, false);
        this.setFormRequestProperties(connection, postData);
        addHeaders(connection, headers);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        Throwable var9 = null;

        try {
            outputStream.write(postData);
        } catch (Throwable var18) {
            var9 = var18;
            throw var18;
        } finally {
            if (outputStream != null) {
                if (var9 != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable var17) {
                        var9.addSuppressed(var17);
                    }
                } else {
                    outputStream.close();
                }
            }

        }

        this.createAndSaveKibanaLoggerDetails(message);
        this.handleResponseStatus(address, connection, this.getStringErrorMsg(connection));
        return this.handleSessionCookie(connection);
    }

    private String getStringErrorMsg(HttpURLConnection connection) {
        if (connection.getErrorStream() != null) {
            String errorMsg = getStringError(connection);
            LOGGER.info("Response error: " + errorMsg);
            return errorMsg;
        } else {
            return "Error message was not provided to HTTP response";
        }
    }

    private byte[] formDataBytes(Map<String, String> parameters) {
        String urlParameters = (String)parameters.entrySet().stream().map(this::encodeParams).collect(Collectors.joining("&"));
        LOGGER.debug("Form data parameters: {}", urlParameters);
        return urlParameters.getBytes(StandardCharsets.ISO_8859_1);
    }

    private String encodeParams(Entry<String, String> pair) {
        try {
            return URLEncoder.encode((String)pair.getKey(), "ISO-8859-1") + "=" + URLEncoder.encode((String)pair.getValue(), "ISO-8859-1");
        } catch (UnsupportedEncodingException var3) {
            LOGGER.warn("Unable to encode post parameters!", var3);
            return (String)pair.getKey() + "=" + (String)pair.getValue();
        }
    }

    private void setFormRequestProperties(HttpURLConnection connection, byte[] postData) {
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
    }

    protected String sendRequest(RequestType type, String address, Map<String, String> headers, String body) throws IOException {
        String message = String.format("Sending %s request to %s/%s", type, this.host, address);
        LOGGER.info(message);

        for(int retries = 5; retries > 0; --retries) {
            HttpURLConnection connection = this.setupConnection(address, type);
            int responseCode = this.handleConnection(connection, headers, address, message, body);
            if (ACCEPTED_RESPONSE_CODES.contains(responseCode)) {
                return this.handleSessionCookie(connection);
            }

            this.retryConnectionMsg(responseCode);
        }

        throw new IllegalAccessError("Unable to send request!");
    }

    private int handleConnection(HttpURLConnection connection, Map<String, String> headers, String address, String message, String body) throws IOException {
        addHeaders(connection, headers);
        this.writeBodyIfExists(connection, body);
        int responseCode = connection.getResponseCode();
        LOGGER.info("Response message: " + connection.getResponseMessage());
        this.createAndSaveKibanaLoggerDetails(message + " Body: " + body);
        this.handleResponseStatus(address, connection, this.getStringErrorMsg(connection));
        return responseCode;
    }

    private void createAndSaveKibanaLoggerDetails(String message) {
        KibanaLoggerDetails kibanaLoggerDetails = new KibanaLoggerDetails(this.kibanaLogger.kibanaLoggerWithNextProcessStep());
        kibanaLoggerDetails.setMessage(message);
        kibanaLoggerDetails.save();
    }

    private HttpURLConnection setupConnection(String address, RequestType type) throws IOException {
        URL url = new URL(this.host + "/" + address);
        return this.prepareConnection(url, String.valueOf(type), true, true, true, true);
    }

    private void writeBodyIfExists(HttpURLConnection connection, String body) throws IOException {
        if (body != null && !body.isEmpty()) {
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            Throwable var4 = null;

            try {
                writer.write(body);
            } catch (Throwable var13) {
                var4 = var13;
                throw var13;
            } finally {
                if (writer != null) {
                    if (var4 != null) {
                        try {
                            writer.close();
                        } catch (Throwable var12) {
                            var4.addSuppressed(var12);
                        }
                    } else {
                        writer.close();
                    }
                }

            }

            LOGGER.info("Sending request body:\n{}", body);
        }

    }

    private void retryConnectionMsg(int responseCode) {
        String wMessage = String.format("Received %s code!. Retrying.", responseCode);
        LOGGER.warn(wMessage);
    }

    private void useJSONinConnection(HttpURLConnection connection) {
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    }

    public static String buildRequestBody(Map<String, String> values) {
        return "{" + (String)values.entrySet().stream().map((entry) -> {
            return String.format(!((String)entry.getValue()).startsWith("{") && !((String)entry.getValue()).startsWith("[") ? "\"%s\": \"%s\"" : "\"%s\": %s", entry.getKey(), entry.getValue());
        }).collect(Collectors.joining(", ")) + "}";
    }

    public String getStringResponse(RequestType type, String address, Map<String, String> headers, String body) throws IOException {
        LOGGER.info("Sending {} request to {}/{}...", type, this.host, address);
        URL url = new URL(this.host + "/" + address);
        HttpURLConnection connection = this.prepareConnection(url, String.valueOf(type), true, true, true, true);
        addHeaders(connection, headers);
        this.writeBodyIfExists(connection, body);
        this.handleResponseStatus(address, connection, this.getStringErrorMsg(connection));
        this.handleSessionCookie(connection);
        return getStringResponse(connection);
    }

    private <T> List<T> getObjectListFromRequest(RequestType type, String address, Map<String, String> headers, String body, Class<T> objectClass) throws IOException {
        return (List)this.mapper.readValue(this.getStringResponse(type, address, headers, body), this.mapper.getTypeFactory().constructCollectionType(List.class, objectClass));
    }

    protected <T> T getObjectFromRequest(RequestType type, String address, Map<String, String> headers, String body, Class<T> objectClass) throws IOException {
        return this.mapper.readValue(this.getStringResponse(type, address, headers, body), objectClass);
    }

    protected <T> List<T> getObjectListFromRequest(String address, Map<String, String> headers, Class<T> objectClass) throws IOException {
        return this.getObjectListFromRequest(RequestType.GET, address, headers, (String)null, objectClass);
    }

    public <T> T getObjectFromList(String address, Map<String, String> headers, Class<T> objectClass) throws IOException {
        List<T> objects = this.getObjectListFromRequest(address, headers, objectClass);
        if (objects.isEmpty()) {
            throw new HttpClientException("Received list of " + objectClass + " is empty!");
        } else {
            return objects.get(0);
        }
    }

    private HttpURLConnection prepareConnection(URL url, String requestType, boolean useJson, boolean input, boolean output, boolean redirect) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(requestType);
        if (useJson) {
            this.useJSONinConnection(connection);
        }

        if (input) {
            connection.setDoInput(true);
        }

        connection.setDoOutput(output);
        if (!redirect) {
            connection.setInstanceFollowRedirects(false);
        }

        return connection;
    }

    public HttpURLConnection openConnection(String type, String address) throws IOException {
        return this.openConnection(type, address, (Map)null, (String)null, true);
    }

    public HttpURLConnection openConnection(String type, String address, Map<String, String> headers, String body, boolean redirect) throws IOException {
        URL url = new URL(this.host + "/" + address);
        HttpURLConnection connection = this.prepareConnection(url, type, true, true, true, redirect);
        addHeaders(connection, headers);
        if (body != null && !body.isEmpty()) {
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(body);
            writer.close();
            LOGGER.info("Sending request body:\n{}", body);
        }

        logHeaders(connection);
        return connection;
    }

    private static HttpURLConnection addHeaders(HttpURLConnection connection, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(connection::setRequestProperty);
        }

        return connection;
    }

    public static String getStringResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        return builder.toString();
    }

    private static void logHeaders(HttpURLConnection connection) {
        connection.getHeaderFields().forEach((key, value) -> {
            LOGGER.info(String.format("Header - %s: %s", key, value));
        });
    }

    public static List<String> getHeadersByName(HttpURLConnection connection, String headerName) {
        return (List)connection.getHeaderFields().get(headerName);
    }

    private void handleResponseStatus(String address, HttpURLConnection connection, String errorMsg) throws IOException {
        LOGGER.info("Response code: {}", connection.getResponseCode());
        if (!ACCEPTED_RESPONSE_CODES.contains(connection.getResponseCode())) {
            String message = String.format("Error %s connecting to service: %s! %n %s %n Response errorMsg: %s", connection.getResponseCode(), address, this.getStatusDataAsHtml(), errorMsg);
            LOGGER.error(message);
            this.createAndSaveKibanaLoggerDetails(message);
            throw new RESTClientException(message, connection.getResponseCode());
        }
    }

    private static String getStringError(HttpURLConnection connection) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        return (String)reader.lines().collect(Collectors.joining());
    }

    private String handleSessionCookie(HttpURLConnection connection) {
        String sessionId = null;
        List<String> sessionCookie = (List)connection.getHeaderFields().get("Set-Cookie");
        if (sessionCookie == null && this.headers != null) {
            sessionId = (String)this.headers.get("Cookie");
        } else if (sessionCookie != null) {
            sessionId = (String)sessionCookie.stream().filter((c) -> {
                return c.startsWith("JSESSIONID") || c.startsWith("AWSELB");
            }).map((c) -> {
                return c.split(";")[0];
            }).collect(Collectors.joining("; "));
        }

        LOGGER.info("Session ID: " + sessionId);
        return sessionId;
    }

    public Map<String, String> getStatusData() {
        return this.statusData;
    }

    public void setStatusData(Map<String, String> statusData) {
        this.statusData = statusData;
    }

    public void addStatusData(String key, String value) {
        this.statusData.put(key, value);
    }

    private String getStatusDataAsHtml() {
        StringBuilder statusDataAsString = new StringBuilder();
        statusDataAsString.append("<br>");
        Iterator var2 = this.statusData.entrySet().iterator();

        while(var2.hasNext()) {
            Object status = var2.next();
            Entry pair = (Entry)status;
            statusDataAsString.append("<p>");
            statusDataAsString.append(pair.getKey());
            statusDataAsString.append(" : ");
            statusDataAsString.append(pair.getValue());
            statusDataAsString.append("<p>");
        }

        return statusDataAsString.toString();
    }
}
