package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.ApplicationScoringDTO;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.RegistrationDataDTO;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import feign.Param;
import feign.RequestLine;

public interface ESCreditApplicationClient extends GenericCreditApplicationClient {
  @Override
  @RequestLine("PUT /credit-application-es")
  void startNewApplication(RegistrationDataDTO registrationData);

  @RequestLine("POST /credit-application-es/submit-init-pin-es")
  void submitInitPin(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /credit-application-es/submit-validate-pin-es")
  void submitValidatePin(UserRegistrationInfoDTO registrationInfo);

  @RequestLine("POST /credit-application-es/scoring")
  ApplicationScoringDTO startScoring(FinancialDataDTO financialDataDTO);

  @RequestLine("GET /credit-application-es/scoring/{id}")
  ApplicationScoringDTO getScoring(@Param("id") long id);
}
