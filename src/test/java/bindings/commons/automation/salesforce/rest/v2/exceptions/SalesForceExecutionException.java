package bindings.commons.automation.salesforce.rest.v2.exceptions;

public class SalesForceExecutionException extends RuntimeException {

  public SalesForceExecutionException(final String message) {
    super(message);
  }

  public SalesForceExecutionException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
