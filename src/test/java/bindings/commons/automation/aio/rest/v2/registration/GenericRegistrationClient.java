package bindings.commons.automation.aio.rest.v2.registration;

import com.ipfdigital.automation.aio.rest.dto.AuthenticationImpersonationRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.BankDetailsDTO;
import feign.RequestLine;

public interface GenericRegistrationClient {
  @RequestLine("GET /authentication")
  AuthenticationResponseDTO authenticate();

  @RequestLine("POST /authentication")
  void authenticate(AuthenticationRequestDTO request);

  @RequestLine("POST /authentication/impersonation")
  void authenticate(AuthenticationImpersonationRequestDTO request);

  @RequestLine("POST /authentication/user-registration/request-bank")
  void registerRequestBank(BankDetailsDTO bankDetails);
}
