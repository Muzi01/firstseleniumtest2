package bindings.commons.automation.rest.client;

import org.apache.http.HttpEntity;

import java.util.Map;

public class Request {
  private final String uri;
  private Map<String, String> headers;
  private HttpEntity entity;

  public Request(final String uri) {
    this.uri = uri;
    this.headers = null;
    this.entity = null;
  }

  public String getUri() {
    return this.uri;
  }

  public Map<String, String> getHeaders() {
    return this.headers;
  }

  public void setHeaders(final Map<String, String> headers) {
    this.headers = headers;
  }

  public HttpEntity getEntity() {
    return this.entity;
  }

  public void setEntity(final HttpEntity entity) {
    this.entity = entity;
  }
}
