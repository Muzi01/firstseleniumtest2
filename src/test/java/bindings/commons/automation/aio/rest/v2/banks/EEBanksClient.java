package bindings.commons.automation.aio.rest.v2.banks;

import feign.RequestLine;

public interface EEBanksClient {
  @RequestLine("POST mobile_id/start_auth")
  MobileIdAuthResponseDTO mobileIdAuth(MobileIdAuthRequestDTO request);

  @RequestLine("POST mobile_id/verify")
  MobileIdAuthResponseDTO mobileIdVerify(MobileIdAuthResponseDTO request);
}
