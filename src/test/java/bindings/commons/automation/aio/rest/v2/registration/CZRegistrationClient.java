package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.RequestLine;

public interface CZRegistrationClient extends GenericRegistrationClient {
  @RequestLine("POST /authentication/user-registration/register-cz/register")
  void registerUser(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("PUT /authentication/user-registration/register-cz/send-otp-request")
  void sendOtp(boolean hideError);

  @RequestLine("POST /authentication/user-registration/register-cz/verify-otp-request")
  void verifyOtp(UserRegistrationInfoDTO otp);
}
