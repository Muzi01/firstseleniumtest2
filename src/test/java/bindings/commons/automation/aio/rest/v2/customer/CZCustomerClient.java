package bindings.commons.automation.aio.rest.v2.customer;

import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.CzechGeneralCustomerDataDTO;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import feign.RequestLine;


public interface CZCustomerClient extends GenericCustomerClient {
  @RequestLine("POST /customer-cz/registration-details/general")
  void updateGeneralCustomerData(CzechGeneralCustomerDataDTO czechGeneralCustomerDataDTO);

  @RequestLine("POST /customer-cz/registration-details/address")
  void updateCustomerAddress(AddressDTO addressDTO);

  @RequestLine("PUT /customer-cz/password")
  void updatePassword(PasswordUpdateDTO password);
}
