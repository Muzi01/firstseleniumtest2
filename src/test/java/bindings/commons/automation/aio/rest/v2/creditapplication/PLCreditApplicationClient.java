package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import feign.RequestLine;

public interface PLCreditApplicationClient extends GenericCreditApplicationClient {
  @Override
  @RequestLine("PUT /credit-application-pl")
  void startNewApplication();

  @RequestLine("PUT /credit-application-pl/financialdata-step2")
  void storeFinancialDataStep2(FinancialDataDTO financialData);
}
