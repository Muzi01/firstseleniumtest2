package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface AURegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST /authentication/user-registration/register-au/register-init")
  void registerInit(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /authentication/user-registration/register-au/register-confirm")
  void registerConfirm(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /authentication/user-registration/register-au/register-complete")
  void registerComplete(UserRegistrationInfoDTO registrationInfo);
}
