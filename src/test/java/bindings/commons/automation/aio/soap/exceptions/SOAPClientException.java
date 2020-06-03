package bindings.commons.automation.aio.soap.exceptions;

public class SOAPClientException extends RuntimeException {

  public SOAPClientException(final String message) {
    super(message);
  }

  public SOAPClientException(final String message, final Throwable e) {
    super(message, e);
  }
}
