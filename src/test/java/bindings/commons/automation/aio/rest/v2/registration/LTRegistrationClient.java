package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface LTRegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST /authentication/user-registration/register-init-lt-c24")
  void registerInit(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /authentication/user-registration/register-confirm-lt-c24")
  void registerConfirm(UserRegistrationInfoDTO registrationInfo);
}
