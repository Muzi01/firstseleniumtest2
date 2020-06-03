package bindings.commons.automation.aio.rest.v2.passwordrecovery;

import com.ipfdigital.automation.aio.rest.dto.PasswordRecoveryDTO;
import feign.RequestLine;

public interface PasswordRecoveryClient {
  @RequestLine("POST /password-recovery/send-email")
  void sendEmail(PasswordRecoveryDTO passwordRecovery);
}
