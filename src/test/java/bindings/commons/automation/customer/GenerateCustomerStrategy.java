package bindings.commons.automation.customer;

interface GenerateCustomerStrategy {
  boolean accept(Country country);

  GeneratedCustomerDTO generate(final GenerateCustomerDTO dto);
}
