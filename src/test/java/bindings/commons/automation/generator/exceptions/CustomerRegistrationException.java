package bindings.commons.automation.generator.exceptions;

public class CustomerRegistrationException extends RuntimeException {

  public CustomerRegistrationException(final Exception e) {
    super(e);
  }

  public CustomerRegistrationException(final String message, final Throwable e) {
    super(message, e);
  }

  public CustomerRegistrationException(final String message) {
    super(message);
  }
}
