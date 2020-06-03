package bindings.commons.automation.api.registration;

import com.ipfdigital.automation.adapter.registration.WrongCredentialsException;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationImpersonationRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationRequestDTO;

public interface RegistrationClient {
  void authenticate(AuthenticationRequestDTO dto) throws WrongCredentialsException;

  void authenticate(AuthenticationImpersonationRequestDTO request);

  void authenticateBySessionId();
}
