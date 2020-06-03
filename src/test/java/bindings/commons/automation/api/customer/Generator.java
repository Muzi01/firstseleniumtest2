package bindings.commons.automation.api.customer;

interface Generator<RETURN_TYPE, DATA_TO_GENERATE> {
  RETURN_TYPE generate(DATA_TO_GENERATE object);
}
