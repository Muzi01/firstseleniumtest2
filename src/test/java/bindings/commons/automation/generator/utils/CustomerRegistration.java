package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.generator.model.aio.Customer;

import java.io.IOException;

public interface CustomerRegistration {
  Customer createCustomerAccount(RegistrationParams params, AIOBackendRestClientProvider provider)
      throws IOException;

  Customer createCustomerWithApplication(RegistrationParams params)
      throws IOException;

  void verifyCustomer(Customer customer, boolean isDraw,
      Environment environment);

  void verification(RegistrationParams params, Customer customer);

  void verifyDrawCase(Customer customer, Environment environment);
}
