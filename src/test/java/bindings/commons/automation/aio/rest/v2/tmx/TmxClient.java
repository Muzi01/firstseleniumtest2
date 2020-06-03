package bindings.commons.automation.aio.rest.v2.tmx;

import com.ipfdigital.automation.aio.rest.dto.TMXInitRequestDTO;
import feign.RequestLine;

public interface TmxClient {
  @RequestLine("POST /tmx/init-session")
  void tmxInitSession(TMXInitRequestDTO tmxInitRequest);
}
