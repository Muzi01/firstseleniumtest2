package bindings.commons.automation.aio.rest.v2.customer;

import com.ipfdigital.automation.aio.rest.dto.UpdateCustomerDTO;
import feign.RequestLine;

public interface ESCustomerClient extends GenericCustomerClient {
  @RequestLine("PUT /customer/info-es")
  void updateCustomerInfo(UpdateCustomerDTO updateCustomerDTO);
}
