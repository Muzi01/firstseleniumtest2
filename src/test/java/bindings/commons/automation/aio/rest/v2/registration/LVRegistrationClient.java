package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface LVRegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST /authentication/user-registration/register-init")
  void registerInit(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /authentication/user-registration/register-confirm-lv")
  void registerConfirm(UserRegistrationInfoDTO registrationInfo);
}
