package bindings.commons.automation.rest.client;

import java.util.Map;

public class Response {
  private int httpCode;
  private Map<String, String> headers;
  private String body;

  public int getHttpCode() {
    return this.httpCode;
  }

  public void setHttpCode(final int httpCode) {
    this.httpCode = httpCode;
  }

  public Map<String, String> getHeaders() {
    return this.headers;
  }

  public void setHeaders(final Map<String, String> headers) {
    this.headers = headers;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(final String body) {
    this.body = body;
  }
}
