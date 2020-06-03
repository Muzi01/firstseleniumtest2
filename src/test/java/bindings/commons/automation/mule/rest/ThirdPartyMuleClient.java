package bindings.commons.automation.mule.rest;

import com.ipfdigital.automation.mule.model.thirdpartyclient.CustomerPartnerDTO;
import feign.Param;
import feign.RequestLine;

public interface ThirdPartyMuleClient {

  @RequestLine("GET /api/partner/customer?ssn={ssn}")
  CustomerPartnerDTO getCustomerPartnerData(@Param("ssn") String ssn);

}
