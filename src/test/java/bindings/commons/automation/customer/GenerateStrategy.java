package bindings.commons.automation.customer;

public interface GenerateStrategy<DATA_TO_ACCEPT, RETURN_TYPE>
    extends AcceptStrategy<DATA_TO_ACCEPT> {
  RETURN_TYPE generate();
}
