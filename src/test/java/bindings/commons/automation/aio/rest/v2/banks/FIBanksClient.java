package bindings.commons.automation.aio.rest.v2.banks;

import com.ipfdigital.automation.aio.rest.dto.BankAuthenticationRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAuthenticationResponseDTO;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface FIBanksClient {
  @RequestLine("POST authentication/banks_fi/nordea")
  BankAuthenticationResponseDTO bankAuthentication(BankAuthenticationRequestDTO request);

  @RequestLine("GET authentication/signicat?callback={callback}&code={code}&state={state}&session.state={session.state}")
  Response signicat(@Param("callback") String callback, @Param("code") String code,
      @Param("state") String state, @Param("session.state") String sessionState);
}
