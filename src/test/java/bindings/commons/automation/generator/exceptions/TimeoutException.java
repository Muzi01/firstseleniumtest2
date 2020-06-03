package bindings.commons.automation.generator.exceptions;

public class TimeoutException extends RuntimeException {
  public TimeoutException(final String message) {
    super(message);
  }

  public TimeoutException(final String message, final Throwable throwable) {
    super(message, throwable);
  }
}
