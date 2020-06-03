package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface ESRegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST /authentication/user-registration/register-init-es")
  void registerInit(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /authentication/user-registration/register-confirm-es")
  void registerConfirm(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /authentication/user-registration/register-es/register")
  void registerUser(UserRegistrationInfoDTO registrationInfo);
}
