package bindings.commons.automation.aio.soap.exceptions;

public class XMLTransformationException extends RuntimeException {

  public XMLTransformationException(final Exception message) {
    super(message);
  }

  public XMLTransformationException(final String message) {
    super(message);
  }

}
