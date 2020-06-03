package bindings.commons.automation.aio.rest.v2.customer;

import com.ipfdigital.automation.aio.rest.dto.gdpr.RegistrationFinlandDTO;
import feign.RequestLine;

public interface FICustomerClient extends GenericCustomerClient {
  @RequestLine("PUT /customer/registration")
  void register(RegistrationFinlandDTO registrationFinland);
}
