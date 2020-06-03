package bindings.commons.automation.rest.client.exceptions;

public class HttpClientException extends RuntimeException {

  public HttpClientException(final Throwable cause) {
    super(cause);
  }

  public HttpClientException(final String message) {
    super(message);
  }

  public HttpClientException(final String message, final Throwable e) {
    super(message, e);
  }
}
