package bindings.commons.automation.rest.client;

import com.ipfdigital.automation.rest.client.exceptions.HttpClientException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApacheClient extends HttpClient {
  private static final Logger LOGGER = LogManager.getLogger(ApacheClient.class);

  private static final List<Integer> ACCEPTED_RESPONSE_CODES = Arrays.asList(200, 201, 204, 303);

  private static final int MAX_CONNECTION_TIMEOUT_MS = 300000;

  private final CloseableHttpClient client;
  private HttpClientContext context;
  private Response lastResponse;

  public ApacheClient() {
    this.client = HttpClients.custom()
        .setDefaultRequestConfig(returnRequestConfig())
        .build();

    prepareContext();
  }

  public ApacheClient(final String login, final String password) {
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider ();
    final UsernamePasswordCredentials credentials =
        new UsernamePasswordCredentials (login, password);
    credentialsProvider.setCredentials(AuthScope.ANY, credentials);

    this.client = HttpClientBuilder.create()
        .setDefaultRequestConfig(returnRequestConfig())
        .setDefaultCredentialsProvider(credentialsProvider)
        .build();

    prepareContext();
  }

  private RequestConfig returnRequestConfig() {
    return RequestConfig.custom()
        .setConnectTimeout(MAX_CONNECTION_TIMEOUT_MS)
        .setConnectionRequestTimeout(MAX_CONNECTION_TIMEOUT_MS)
        .setSocketTimeout(MAX_CONNECTION_TIMEOUT_MS)
        .build();
  }

  private void prepareContext() {
    this.context = HttpClientContext.create();
    final CookieStore cookieStore = new BasicCookieStore ();
    this.context.setCookieStore(cookieStore);
  }

  @Override
  public void sendDELETE(final Request request) {
    final HttpDelete httpDelete = new HttpDelete (request.getUri());
    sendGenericGETRequest(httpDelete);
  }

  @Override
  public void sendGET(final Request request) {
    final HttpGet httpGet = new HttpGet (request.getUri());
    sendGenericGETRequest(httpGet);
  }

  @Override
  public void sendHEAD(final Request request) {
    final HttpHead httpHead = new HttpHead (request.getUri());
    sendGenericGETRequest(httpHead);
  }

  @Override
  public void sendPATCH(final Request request) {
    final HttpPatch httpPatch = new HttpPatch (request.getUri());
    sendGenericHttpRequest(httpPatch, request);
  }

  @Override
  public void sendPOST(final Request request) {
    final HttpPost httpPost = new HttpPost (request.getUri());
    sendGenericHttpRequest(httpPost, request);
  }

  @Override
  public void sendPUT(final Request request) {
    final HttpPut httpPut = new HttpPut (request.getUri());
    sendGenericHttpRequest(httpPut, request);
  }

  @Override
  public void close() {
    if (this.client != null) {
      try {
        this.client.close();

      } catch (final IOException eStackTrace) {
        final String eMessage = "Error occurred while closing HTTP Client!";
        LOGGER.error(eMessage);
        throw new HttpClientException(eMessage, eStackTrace);
      }
    }
  }

  @Override
  public Response getLastResponse() {
    if (this.lastResponse != null) {
      return this.lastResponse;

    } else {
      final String eMessage = "No stored response!";
      LOGGER.error(eMessage);
      throw new IllegalArgumentException(eMessage);
    }
  }

  private void sendHttpRequest(final HttpEntityEnclosingRequestBase httpRequest) {
    try (
        final CloseableHttpResponse httpResponse = this.client.execute(httpRequest, this.context)) {
      logRequest(httpRequest);
      storeResponse(httpResponse);
      final int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
      LOGGER.info(String.format("Received response code: %s", responseStatusCode));
      if (!ACCEPTED_RESPONSE_CODES.contains(responseStatusCode)) {
        logErrorStackTrace(httpRequest, httpResponse);
      }
    } catch (final IOException exception) {
      final String eMessage = "Error occurred while sending HTTP request!";
      LOGGER.error(eMessage);
      throw new HttpClientException(eMessage, exception);
    }
  }

  private void sendHttpGETRequest(final HttpRequestBase httpRequest) {
    try (
        final CloseableHttpResponse httpResponse = this.client.execute(httpRequest, this.context)) {
      storeResponse(httpResponse);
      final int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
      LOGGER.info(String.format("Received response code: %s", responseStatusCode));

    } catch (final IOException exception) {
      final String eMessage = "Error occurred while sending GET request!";
      LOGGER.error(eMessage);
      throw new HttpClientException(eMessage, exception);
    }
  }

  private void storeResponse(final CloseableHttpResponse httpResponse) {
    final int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
    final String responseBody = readEntityContent(httpResponse.getEntity());
    final Map<String, String> mappedHeaders = new HashMap<>();
    final Header[] headers = httpResponse.getAllHeaders();
    for (final Header header : headers) {
      mappedHeaders.put(header.getName(), header.getValue());
    }

    this.lastResponse = getResponse(responseStatusCode, responseBody, mappedHeaders);
  }

  private Response getResponse(final int responseStatusCode, final String responseBody,
      final Map<String, String> mappedHeaders) {
    final Response response = new Response();
    response.setHttpCode(responseStatusCode);
    response.setHeaders(mappedHeaders);
    response.setBody(responseBody);
    return response;
  }

  private void sendGenericHttpRequest(final HttpEntityEnclosingRequestBase genericHttpType,
      final Request request) {
    if (request.getEntity() != null) {
      genericHttpType.setEntity(request.getEntity());
    }
    final Map<String, String> mappedHeaders = request.getHeaders();
    if (mappedHeaders != null) {
      for (final Map.Entry<String, String> header : mappedHeaders.entrySet()) {
        genericHttpType.addHeader(header.getKey(), header.getValue());
      }
    }
    sendHttpRequest(genericHttpType);
  }

  private void sendGenericGETRequest(final HttpRequestBase genericHttpType) {
    sendHttpGETRequest(genericHttpType);
  }

  private void logErrorStackTrace(final HttpEntityEnclosingRequestBase httpRequest,
      final CloseableHttpResponse httpResponse) {
    final int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
    final String responseBody = readEntityContent(httpResponse.getEntity());
    final String requestBody = readEntityContent(httpRequest.getEntity());

    final String eMessage = String.format(
        "Received not accepted response code: %s! Stack trace:%n" +
            "URI: %s%n" +
            "Send request: %s%n" +
            "Received response: %s%n",
        responseStatusCode, httpRequest.getURI(),
        requestBody, responseBody);

    LOGGER.error(eMessage);
    throw new HttpClientException(eMessage);
  }

  private void logRequest(final HttpEntityEnclosingRequestBase httpRequest) {
    /*
     * Don't use EntityUtils.toString(httpRequest.getEntity()) before send request. This may cause
     * IOException Attempted read from closed stream
     */
    LOGGER.info(
        String.format("Sending %s message to URI: %s",
            httpRequest.getMethod(), httpRequest.getURI()));
  }

  private String readEntityContent(final HttpEntity entity) {
    if (entity != null) {
      try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
        entity.writeTo(byteArrayOutputStream);
        return byteArrayOutputStream.toString();
      } catch (final IOException e) {
        LOGGER.error("Error when read entity content", e);
        return StringUtils.EMPTY;
      }
    }
    LOGGER.info("Entity not found");
    return StringUtils.EMPTY;

  }
}
