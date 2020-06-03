package bindings.commons.automation.aio.rest.v2.citizenship;

import com.ipfdigital.automation.aio.rest.dto.CitizenshipUpdateDTO;
import feign.RequestLine;

public interface CitizenshipClient {
  @RequestLine("PUT /citizenships/update-citizenships")
  void updateCitizenships(CitizenshipUpdateDTO citizenship);
}
