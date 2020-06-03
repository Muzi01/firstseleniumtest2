package bindings.commons.automation.generator.observer;

import com.ipfdigital.automation.generator.model.aio.Customer;

public class CustomerCreatedEvent {
  private final Customer customer;

  public static CustomerCreatedEvent aCustomerCreatedEvent(final Customer customer) {
    return new CustomerCreatedEvent(customer);
  }

  public CustomerCreatedEvent(final Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return this.customer;
  }
}
