package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface MXRegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST /authentication/user-registration/register-mx/register")
  void registerUser(UserRegistrationInfoDTO registrationInfo);
}
