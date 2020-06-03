package bindings.commons.automation.customer;

public interface AcceptStrategy<DATA_TO_ACCEPT> {
  boolean accept(final DATA_TO_ACCEPT object);
}
