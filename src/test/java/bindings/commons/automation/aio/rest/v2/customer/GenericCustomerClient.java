package bindings.commons.automation.aio.rest.v2.customer;

import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.BankUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.CalmaConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.MsisdnUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.RegistrationDataDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.AdditionalConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface GenericCustomerClient {
  @RequestLine("PUT /customer/bankaccount")
  void updateBankAccount(BankAccountUpdateDTO bankAccountUpdate);

  @RequestLine("POST /customer/bankaccount/validation")
  void validateBankAccount(BankAccountUpdateDTO bankAccountUpdate);

  @RequestLine("PUT /customer/marketing-consent")
  void updateMarketingConsent(List<MarketingConsentDTO> marketingConsent);

  @RequestLine("PUT /customer/additional-consent")
  void updateAdditionalConsent(List<AdditionalConsentDTO> additionalConsent);

  @RequestLine("PUT /customer/address")
  void updateAddress(AddressDTO address);

  @RequestLine("PUT /customer/bank")
  void updateBank(BankUpdateDTO bank);

  @RequestLine("PUT /customer/msisdn")
  void updateMsisdn(MsisdnUpdateDTO msisdn);

  @RequestLine("GET /customer/email/verification?key={validationKey}&country={country}")
  void verifyEmail(@Param("validationKey") String validationKey, @Param("country") String country);

  @RequestLine("PUT /customer/registration")
  void register(RegistrationDataDTO data);

  @RequestLine("PUT /customer/additional-consent")
  void acceptCalmaConsents(List<CalmaConsentDTO> consents);

  @RequestLine("PUT /customer/communication-settings")
  void updateCommunicationSettings(CommunicationSettingsDTO communicationSettings);
}
