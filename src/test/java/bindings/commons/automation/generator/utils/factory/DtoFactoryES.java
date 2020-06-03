package bindings.commons.automation.generator.utils.factory;

import com.google.common.collect.ImmutableList;
import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.CustomerDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.customer.address.CustomerAddressDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName.*;
import static com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion.ES_IPFD_CHANNELS;

public class DtoFactoryES {

  private static final List<ConsentName> CONSENTS_ES = ImmutableList
      .of(MARKETING_IM, MARKETING_PAPER_MAIL, MARKETING_PUSH, MARKETING_IPFD,
          MARKETING_DATA_TRANSFER, MARKETING_SMS, MARKETING_EMAIL, MARKETING_ROBO, MARKETING_PHONE,
          MARKETING_3RD_PARTY);

  private DtoFactoryES() {
  }

  public static CustomerDTO createCustomerDTO(final GeneratedCustomerDTO dataES,
      final LocalDate birthDate) {
    final CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.firstName = dataES.getFirstName();
    customerDTO.lastName = dataES.getLastName();
    customerDTO.secondLastName = dataES.getSecondLastName();
    customerDTO.gender = dataES.getGender();
    customerDTO.countryOfBirth = Country.ES.name();
    customerDTO.msisdn2 = dataES.getMsisdn2();
    customerDTO.dateOfBirth = birthDate;
    return customerDTO;
  }

  public static AddressDTO createAddressDTO(final CustomerAddressDTO customerAddress) {
    final AddressDTO addressDTO = new AddressDTO();
    addressDTO.street = customerAddress.getStreet() + " " + customerAddress.getDoor();
    addressDTO.postcode = customerAddress.getPostcode();
    addressDTO.city = customerAddress.getCity();
    addressDTO.province = customerAddress.getCity();
    return addressDTO;
  }

  public static FinancialDataDTO createFinancialDataDTO(final Education education,
      final EmploymentDuration employmentDuration, final MaritalStatus maritalStatus,
      final Integer netIncome, final Occupation occupation) {
    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.education = education;
    financialDataDTO.employmentDuration = employmentDuration;
    financialDataDTO.maritalStatus = maritalStatus;
    financialDataDTO.netIncome = netIncome;
    financialDataDTO.occupation = occupation;
    return financialDataDTO;
  }

  public static List<MarketingConsentDTO> createMarketingConsentList() {
    final List<MarketingConsentDTO> consentsDto = new ArrayList<>();
    CONSENTS_ES
        .forEach(consent -> consentsDto.add(createConsent(consent, ES_IPFD_CHANNELS, false)));
    return consentsDto;
  }

  private static MarketingConsentDTO createConsent(final ConsentName consentName,
      final ConsentVersion version, final boolean value) {
    final MarketingConsentDTO marketingConsentDTO = new MarketingConsentDTO();
    marketingConsentDTO.consentName = consentName;
    marketingConsentDTO.value = value;
    marketingConsentDTO.version = version.toString();
    marketingConsentDTO.validUntil = "2999-12-31";
    return marketingConsentDTO;
  }
}
