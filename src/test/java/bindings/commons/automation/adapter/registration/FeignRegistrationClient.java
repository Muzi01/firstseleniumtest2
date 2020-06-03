package bindings.commons.automation.adapter.registration;

import com.ipfdigital.automation.aio.rest.dto.AuthenticationImpersonationRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationRequestDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.registration.GenericRegistrationClient;
import com.ipfdigital.automation.api.registration.RegistrationClient;
import feign.FeignException;

public class FeignRegistrationClient implements RegistrationClient {

  private final AIOBackendRestClientProvider provider;

  public FeignRegistrationClient(final AIOBackendRestClientProvider provider) {
    this.provider = provider;
  }

  @Override
  public void authenticate(final AuthenticationRequestDTO dto) throws WrongCredentialsException {
    try {
      this.provider.provide(GenericRegistrationClient.class).authenticate(dto);
    } catch (final FeignException.Unauthorized e) {
      throw new WrongCredentialsException("User or password is wrong");
    }
  }

  @Override
  public void authenticate(final AuthenticationImpersonationRequestDTO request) {
    this.provider.provide(GenericRegistrationClient.class).authenticate(request);
  }

  @Override
  public void authenticateBySessionId() {
    this.provider.provide(GenericRegistrationClient.class).authenticate();
  }
}
