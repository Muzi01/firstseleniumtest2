package bindings.commons.automation.aio.rest.v2.banks;

import feign.Param;
import feign.RequestLine;

public interface LTBanksClient {
  @RequestLine("GET /authentication/banks_lt/one-cent-payment-status-lt?customer_id={customerId}")
  void initOneCentVerification(@Param("customerId") long customerId);
}
