package bindings.commons.automation.customer;

import com.ipfdigital.automation.api.customer.CustomerAddressGenerator;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.NameGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;
import com.ipfdigital.automation.customer.ssn.GenerateSsnParamsDTO;
import com.ipfdigital.automation.customer.ssn.SsnType;
import com.ipfdigital.automation.generators.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

class GenericCustomerGeneratorStrategy implements GenerateCustomerStrategy {

  private static final String COMPANY_NO = "Company no. ";
  private static final String CONTEXT_OF_GENERATOR_USE_DEFAULT_VALUE = "AUTOMATION_TEST";
  private static final String CONTEXT_OF_GENERATOR_USE_PARAM_NAME = "contextOfGeneratorUse";
  private static final int UNDERAGESSN_AGE = 15;
  private static final int OVERAGESSN_AGE = 95;
  private static final int OPTIMALSSN_AGE = 55;

  private final CustomerBankAccountGenerator bankAccountGenerator;
  private final CustomerAddressGenerator customerAddressGenerator;
  private final MsisdnGenerator msisdnGenerator;
  private final NameGenerator nameGenerator;
  private final SsnGenerator ssnGenerator;

  public GenericCustomerGeneratorStrategy(
      final CustomerBankAccountGenerator bankAccountGenerator,
      final CustomerAddressGenerator customerAddressGenerator,
      final MsisdnGenerator msisdnGenerator,
      final NameGenerator nameGenerator,
      final SsnGenerator ssnGenerator) {
    this.bankAccountGenerator = bankAccountGenerator;
    this.customerAddressGenerator = customerAddressGenerator;
    this.msisdnGenerator = msisdnGenerator;
    this.nameGenerator = nameGenerator;
    this.ssnGenerator = ssnGenerator;
  }

  private String generateEmailSuffixForGeneratorContext(final String contextOfGeneratorUse) {
    return ContextOfGeneratorUse.valueOf(contextOfGeneratorUse).emailSuffix;
  }

  @Override
  public boolean accept(final Country country) {
    return false;
  }

  @Override
  public GeneratedCustomerDTO generate(final GenerateCustomerDTO dto) {
    final GeneratedCustomerDTO generatedCustomerDTO = new GeneratedCustomerDTO();
    final LocalDate birthDate = RandomUtils.randomBirthday(dto.minAge, dto.maxAge);
    final Gender gender = RandomUtils.randomEnum(Gender.class);
    final String contextOfGeneratorUse = System.getProperty(
        CONTEXT_OF_GENERATOR_USE_PARAM_NAME, CONTEXT_OF_GENERATOR_USE_DEFAULT_VALUE);
    prepareMsisdnValues(dto, generatedCustomerDTO);
    prepareNamesValues(dto, generatedCustomerDTO, gender);
    prepareSsnValues(dto, generatedCustomerDTO, gender, birthDate);
    generatedCustomerDTO.setCountry(dto.country);
    generatedCustomerDTO.setGender(gender);
    generatedCustomerDTO.setDateOfBirth(birthDate);
    generatedCustomerDTO.setForeign(dto.isFeign);
    generatedCustomerDTO.setContextOfGeneratorUse(contextOfGeneratorUse);
    generatedCustomerDTO.setEmail(generateEmail(contextOfGeneratorUse, dto.country));
    generatedCustomerDTO.setBankAccount(bankAccountGenerator.generate(dto.country));
    generatedCustomerDTO.setAddress(customerAddressGenerator.generate(dto.country));
    generatedCustomerDTO.setCompanyName(generateCompanyName());
    return generatedCustomerDTO;
  }

  private void prepareMsisdnValues(final GenerateCustomerDTO dto,
      final GeneratedCustomerDTO generatedCustomerDTO) {
    final String msisdn = msisdnGenerator.generate(dto.country);
    generatedCustomerDTO.setMsisdn(msisdn);
    generatedCustomerDTO.setPrefixedMsisdn(generateMsisdnWithPrefix(msisdn, dto.country));
    generatedCustomerDTO.setMsisdn2(msisdnGenerator.generate(dto.country));
  }

  private void prepareNamesValues(final GenerateCustomerDTO dto,
      final GeneratedCustomerDTO generatedCustomerDTO, final Gender gender) {
    generatedCustomerDTO.setFirstName(nameGenerator.generateFirstName(dto.country, gender));
    generatedCustomerDTO.setSecondFirstName(nameGenerator.generateFirstName(dto.country, gender));
    generatedCustomerDTO.setLastName(nameGenerator.generateLastName(dto.country, gender));
    generatedCustomerDTO.setSecondLastName(nameGenerator.generateLastName(dto.country, gender));
  }

  private void prepareSsnValues(final GenerateCustomerDTO dto,
      final GeneratedCustomerDTO generatedCustomerDTO, final Gender gender,
      final LocalDate birthDate) {
    generatedCustomerDTO
        .setIdentifier(ssnGenerator.generate(buildGenerateSSNParamsDTO(gender, birthDate, dto)));
    generatedCustomerDTO.setOptimalSSN(ssnGenerator.generate(buildGenerateSSNParamsDTO(gender,
        RandomUtils.randomBirthday(OPTIMALSSN_AGE, OPTIMALSSN_AGE), dto)));
    generatedCustomerDTO.setOverageSSN(ssnGenerator.generate(buildGenerateSSNParamsDTO(gender,
        RandomUtils.randomBirthday(OVERAGESSN_AGE, OVERAGESSN_AGE), dto)));
    generatedCustomerDTO.setUnderageSSN(ssnGenerator.generate(buildGenerateSSNParamsDTO(gender,
        RandomUtils.randomBirthday(UNDERAGESSN_AGE, UNDERAGESSN_AGE), dto)));
    generatedCustomerDTO
        .setOptimalBirthday(RandomUtils.randomBirthday(OPTIMALSSN_AGE, OPTIMALSSN_AGE).toString());
    generatedCustomerDTO.setNieSSN(ssnGenerator
        .generate(new GenerateSsnParamsDTO(gender, birthDate, SsnType.NIE, dto.country)));
  }

  private String generateMsisdnWithPrefix(final String msisdn, final Country country) {
    return country.getPrefix() + msisdn;
  }

  private String generateCompanyName() {
    return COMPANY_NO + RandomUtils.randomInt(99999999);
  }

  private String generateEmail(final String contextOfGeneratorUse, final Country country) {
    return (RandomStringUtils.randomAlphanumeric(10)
        + generateEmailSuffixForGeneratorContext(contextOfGeneratorUse) + "@fakemail."
        + country.name()).toLowerCase();
  }

  private GenerateSsnParamsDTO buildGenerateSSNParamsDTO(final Gender gender,
      final LocalDate birthDate,
      final GenerateCustomerDTO dataParametersDTO) {
    if (dataParametersDTO.isFeign) {
      return new GenerateSsnParamsDTO(gender, birthDate, SsnType.NIE, dataParametersDTO.country);
    }
    return new GenerateSsnParamsDTO(gender, birthDate, SsnType.DNI, dataParametersDTO.country);
  }
}
