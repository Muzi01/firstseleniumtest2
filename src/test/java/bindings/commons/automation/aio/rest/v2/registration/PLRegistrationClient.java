package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface PLRegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST authentication/user-registration/register-pl")
  void registerInit(UserRegistrationInfoDTO registrationInfo);
}
