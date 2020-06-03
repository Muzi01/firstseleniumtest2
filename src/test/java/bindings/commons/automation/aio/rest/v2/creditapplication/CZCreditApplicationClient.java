package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.CzechFinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.CzechStartCreditApplicationDTO;
import feign.RequestLine;

public interface CZCreditApplicationClient extends GenericCreditApplicationClient {

  @RequestLine("PUT /credit-application-cz")
  void putCreditApplication(CzechStartCreditApplicationDTO czechStartCreditApplicationDTO);

  @RequestLine("PUT /credit-application/financialdata")
  void storeFinancialData(CzechFinancialDataDTO financialData);
}
