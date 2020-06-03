package bindings.commons.automation.generator.portals;

public class ProductNotReplicatedException extends RuntimeException {
  public ProductNotReplicatedException(final String ssn, final Throwable e) {
    super(String.format("%n%nProduct not replicated for customer SSN: %s %n", ssn), e);

  }
}
