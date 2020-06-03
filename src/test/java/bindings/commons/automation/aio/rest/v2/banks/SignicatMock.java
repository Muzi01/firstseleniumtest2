package bindings.commons.automation.aio.rest.v2.banks;

import com.ipfdigital.automation.aio.rest.dto.SignicatAuthorizeRequestDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;


public interface SignicatMock {

  @RequestLine("POST /oidc/authorize")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  Response authorize(@Param("request") String request);

  @RequestLine("POST /signicat/login_form/accept")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  Response accept(SignicatAuthorizeRequestDTO dto);
}
