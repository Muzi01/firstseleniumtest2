package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.ApplicationCheckstateDTO;
import feign.RequestLine;

public interface FICreditApplicationClient extends GenericCreditApplicationClient {
  @RequestLine("GET /credit-application/checkstate")
  ApplicationCheckstateDTO getCheckstate();
}
