package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.BankUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.LatviaFinancialData;
import com.ipfdigital.automation.aio.rest.dto.Loan;
import com.ipfdigital.automation.aio.rest.dto.LoanPurpose;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.OccupationType;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.RegistrationDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Residence;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.GenericCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.GenericCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.LVRegistrationClient;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GenerateCustomerDTO;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Credit24LV extends Portal {

  private static final Logger LOG = LogManager.getLogger(Credit24LV.class);

  // Values used in customer registration:
  private static final String OTP = "1234";
  private static final CommunicationSettingsDTO.DeliveryMethod CONTRACT_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final CommunicationSettingsDTO.DeliveryMethod INVOICE_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private String preferredLanguage = "lv";
  private static final String BANK = "SEB";
  private Education education = Education.UNIVERSITY;
  private EmploymentDuration employmentDuration = EmploymentDuration.YEARS_OVER_10;
  private MaritalStatus maritalStatus = MaritalStatus.MARRIED;
  private Integer livingCosts = 0;
  private Integer netIncome = 500000;
  private static final String COMPANY = "IPF test company";
  private Occupation occupation = Occupation.FULL_TIME;
  private OccupationType occupationType = OccupationType.MANAGER;
  private Residence residence = Residence.OWN;
  private Integer householdChildren = 0;
  private static final Integer TOTAL_MONTHLY_OBLIGATIONS = 10000;
  private final Loan[] loans = {Loan.OTHER};
  private static final LoanPurpose LOAN_PURPOSE = LoanPurpose.OTHER;
  private static final String CHANNEL = "loan_app";
  private String emailExtension = null;
  private static final Long CUSTOM_WAIT_TIME = 20000L;


  private Credit24LV() {
    LOG.info("Instantiated Credit24LV portal.");
  }

  private static class SingletonHolder {
    static final Credit24LV instance = new Credit24LV();

    private SingletonHolder() {
    }
  }

  public static Credit24LV getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "LV market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params)
      throws IOException {

    final Environment env = params.getEnvironment();

    verifyRegistrationParams(params);
    getScoringParams(params);
    final GeneratedCustomerDTO generatedData = getCustomerData(params);
    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.CREDIT24.name().toLowerCase(), Country.LV, restEventListener);
    setRegistrationInfo(provider.provide(LVRegistrationClient.class), generatedData);
    Customer customer = authenticateCustomer(provider, generatedData.getIdentifier(), env);
    notifyCustomerRegistered(customer);
    final GenericCreditApplicationClient creditApplicationClient =
        provider.provide(GenericCreditApplicationClient.class);
    startCustomerRegistration(provider.provide(GenericCustomerClient.class),
        creditApplicationClient, generatedData);
    final ProductDTO product =
        chooseProductAndSubmitApplication(creditApplicationClient, generatedData, params);
    customer = fetchCustomerWithAdditionalData(customer, product, generatedData.getEmail(), env);
    verification(params, customer);
    emailVerification(env, generatedData.getEmail(), customer);
    LOG.info(Portal.REGISTRATION_COMPLETE_PATTERN, env, customer.id);
    return customer;
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.LV || params.getBrand() != Brand.CREDIT24) {
      final String msg =
          String.format("Credit24LV can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
  }

  private GeneratedCustomerDTO getCustomerData(final RegistrationParams params) {
    final GenerateCustomerDTO dto;
    if (age == null) {
      dto =
          new GenerateCustomerDTO(Country.LV,
              GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
              GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE);
    } else {
      dto = new GenerateCustomerDTO(Country.LV, age, age);
    }
    final GeneratedCustomerDTO generatedData = customerGenerator.generate(dto);
    if (params.getCustomEmail())
      generatedData.setEmail(params.getCustomEmailValue());
    if (getEmailExtension() != null) {
      replaceEmailExtension(generatedData);
    }
    if (params.getCustomSsn()) {
      generatedData.setIdentifier(params.getCustomSsnValue());
    } else {
      generateNewSsnIfActualUsed(params, 5, generatedData);
    }
    preferredLanguage = params.getCommunicationLanguage();
    return generatedData;
  }

  private void setRegistrationInfo(final LVRegistrationClient client,
      final GeneratedCustomerDTO generatedData) {
    final UserRegistrationInfoDTO userRegistrationInfo = new UserRegistrationInfoDTO();
    userRegistrationInfo.ssn = generatedData.getIdentifier();
    userRegistrationInfo.msisdn = generatedData.getMsisdn();
    client.registerInit(userRegistrationInfo);
    userRegistrationInfo.otp = OTP;
    userRegistrationInfo.firstName = generatedData.getFirstName();
    userRegistrationInfo.lastName = generatedData.getLastName();
    userRegistrationInfo.gender = generatedData.getGender();
    userRegistrationInfo.password = findDefaultUserPass();
    client.registerConfirm(userRegistrationInfo);
  }

  private Customer authenticateCustomer(final AIOBackendRestClientProvider provider,
      final String identifier,
      final Environment env) {
    final AuthenticationResponseDTO authenticationResponse =
        provider.provide(LVRegistrationClient.class).authenticate();
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName, identifier);
    LOG.debug("GDPR LV Customer ID from REST: {}, from DB: {}",
        authenticationResponse.customer.id, customer.id);
    provider.provide(GenericCreditApplicationClient.class).startNewApplication();
    return customer;
  }

  private void startCustomerRegistration(final GenericCustomerClient client,
      final GenericCreditApplicationClient creditApplicationClient,
      final GeneratedCustomerDTO generatedData) {
    final CommunicationSettingsDTO communicationSettingsGdpr = prepareCommunicationSettingsGdpr();
    final RegistrationDataDTO registrationData =
        prepareRegistrationData(generatedData, communicationSettingsGdpr);
    client.register(registrationData);

    final BankUpdateDTO bankUpdateDTO = new BankUpdateDTO();
    bankUpdateDTO.bank = BANK;
    client.updateBank(bankUpdateDTO);

    client.updateMarketingConsent(getMarketingConsentList());
    client.updateAddress(
        mapToDTO(generatedData.getAddress(), generatedData.getCountry()));
    creditApplicationClient.storeFinancialData(prepareFinancialDatDTO());
  }

  private CommunicationSettingsDTO prepareCommunicationSettingsGdpr() {
    final CommunicationSettingsDTO communicationSettingsDTO = new CommunicationSettingsDTO();
    communicationSettingsDTO.contractDeliveryMethod = CONTRACT_DELIVERY_METHOD;
    communicationSettingsDTO.invoiceDeliveryMethod = INVOICE_DELIVERY_METHOD;
    return communicationSettingsDTO;
  }

  private RegistrationDataDTO prepareRegistrationData(final GeneratedCustomerDTO generatedData,
      final CommunicationSettingsDTO communicationSettingsGdpr) {
    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedData.getEmail();

    final RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
    registrationDataDTO.address = mapToDTO(generatedData.getAddress(), generatedData.getCountry());
    registrationDataDTO.communicationSettings = communicationSettingsGdpr;
    registrationDataDTO.email = emailUpdateDTO;
    registrationDataDTO.preferredLanguage = preferredLanguage;
    return registrationDataDTO;
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_3RD_PARTY, ConsentVersion.LV_3RD_PARTY, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.LV_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.LV_IPFD_CHANNELS, true));

    return marketingConsents;
  }

  private FinancialDataDTO prepareFinancialDatDTO() {
    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.employmentDuration = employmentDuration;
    financialDataDTO.education = education;
    financialDataDTO.householdChildren = householdChildren;
    financialDataDTO.maritalStatus = maritalStatus;
    financialDataDTO.livingCosts = livingCosts;
    financialDataDTO.netIncome = netIncome;
    financialDataDTO.company = COMPANY;
    financialDataDTO.occupation = occupation;
    financialDataDTO.occupationType = occupationType;
    financialDataDTO.residence = residence;
    financialDataDTO.totalMonthlyObligations = TOTAL_MONTHLY_OBLIGATIONS;
    financialDataDTO.loans = loans;
    financialDataDTO.loanPurpose = LOAN_PURPOSE;
    financialDataDTO.channel = CHANNEL;
    return financialDataDTO;
  }

  private ProductDTO chooseProductAndSubmitApplication(final GenericCreditApplicationClient client,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params) {
    final List<ProductDTO> productsList =
        params.getProductType() == ProductType.CL ? client.getAvailableCreditLineProducts()
            : client.getAvailableInstallmentProducts();
    final ProductDTO product = chooseProductByFilters(params, productsList);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);
    final ProductSelectionDTO productSelection =
        buildProductSelectionDTO(product, params.getDrawPercentage());
    client.selectProduct(productSelection);
    final ApplicationSubmitDTO applicationSubmit =
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage());
    client.submitApplication(applicationSubmit);
    return product;
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    final boolean isDraw =
        params.getDrawPercentage() > 0 || params.getProductType() == ProductType.IL;
    if (params.getVerification()) {
      verifyCustomer(customer, isDraw, params.getEnvironment());
    }
  }

  private void provideBankAccount(final Customer customer) {
    if (StringUtils.isBlank(customer.bankAccount)) {
      final GenerateCustomerDTO dto = new GenerateCustomerDTO(Country.LV,
          GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
          GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE);

      customer.bankAccount = customerGenerator.generate(dto).getBankAccount().getNumber();
    }
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {
    provideBankAccount(customer);
    final String bankAccount = customer.bankAccount;
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    environment.getSoapUtils().handleIdentificationV2(Country.LV, customer.id,
        SfParamsConstants.IDENTIFICATION_PARAM_EXPECTED_VALUE, bankAccount);
    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);
    salesforceProvider.uploadAttachment(accountId, SFAttachmentType.BANK_STATEMENT);
    tryCloseSFTask(salesforceProvider, SFTask.WAIT_UPLOAD_DOCUMENTS, customer.id);
    if (salesforceProvider.isTaskReplicated(customer.id, // TODO: AT-2565 - waiting for more
        // info about creating this task
        SFTask.CHECK_IDENTIFICATION, CUSTOM_WAIT_TIME)) {
      tryCloseSFTask(salesforceProvider, SFTask.CHECK_IDENTIFICATION, customer.id);
    }
    if (isDraw) {
      tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
    }
    checkIfContractIsReady(salesforceProvider, accountId);
  }

  @Override
  public void verifyDrawCase(final Customer customer, final Environment environment) {
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
  }

  private void replaceEmailExtension(final GeneratedCustomerDTO generatedData) {
    generatedData.setEmail(
        generatedData.getEmail().substring(0, generatedData.getEmail().lastIndexOf('.') + 1)
            + emailExtension.toLowerCase());
  }

  @Override
  public String getEmailExtension() {
    return emailExtension;
  }

  @Override
  public void setEmailExtension(final String emailExtension) {
    this.emailExtension = emailExtension;
  }

  @Override
  public void checkVerificationImplementation() {
    // Implemented
  }

  @Override
  public void setEducation(final String newEducation) {
    education = Education.valueOf(newEducation);
  }

  @Override
  public String getEducation() {
    return education.name();
  }

  @Override
  public void setEmploymentDuration(final String newEmploymentDuration) {
    employmentDuration = EmploymentDuration.valueOf(newEmploymentDuration);
  }

  @Override
  public String getEmploymentDuration() {
    return employmentDuration.name();
  }

  @Override
  public void setHouseholdChildren(final String newHouseholdChildren) {
    householdChildren = Integer.parseInt(newHouseholdChildren);
  }

  @Override
  public Integer getHouseholdChildrens() {
    return householdChildren;
  }

  @Override
  public void setLivingCosts(final String newLivingCosts) {
    livingCosts = Integer.parseInt(newLivingCosts);
  }

  @Override
  public Integer getLivingCosts() {
    return livingCosts;
  }

  @Override
  public void setMaritalStatus(final String newMaritalStatus) {
    maritalStatus = MaritalStatus.valueOf(newMaritalStatus);
  }

  @Override
  public String getMaritalStatus() {
    return maritalStatus.name();
  }

  @Override
  public void setOccupation(final String newOccupation) {
    occupation = Occupation.valueOf(newOccupation);
  }

  @Override
  public String getOccupation() {
    return occupation.name();
  }

  @Override
  public void setOccupationType(final String newOccupationType) {
    occupationType = OccupationType.valueOf(newOccupationType);
  }

  @Override
  public String getOccupationType() {
    return occupationType.name();
  }

  @Override
  public void setResidence(final String newResidence) {
    residence = Residence.valueOf(newResidence);
  }

  @Override
  public String getResidence() {
    return residence.name();
  }

  @Override
  public void setSalary(final String newSalary) {
    netIncome = Integer.parseInt(newSalary);
  }

  @Override
  public Integer getSalary() {
    return netIncome;
  }

  @Override
  public void setAge(final String newAge) {
    age = Integer.parseInt(newAge);
  }

  @Override
  public Integer getAge() {
    return age;
  }

  @Override
  public Integer getCustomerTotalScore() {
    final int ageValue = LatviaFinancialData.getAgeScoreValue(getAge());
    final int emailValue = LatviaFinancialData.getEmailExtensionScoreValue(getEmailExtension());
    return LatviaFinancialData.FIXED_SCORE + ageValue + emailValue;
  }

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    final List<Map.Entry<Integer, String>> scoreMap = LatviaFinancialData.getScoreMap();
    final Integer minScore = scoreMap.get(0).getKey();
    final Integer maxScore = scoreMap.get(scoreMap.size() - 1).getKey();
    if (minScore > expectedCustomerScore || maxScore < expectedCustomerScore) {
      final String errorMessage = String
          .format("For Customer LV, Total Score Value can be in range <%d,%d>", minScore, maxScore);
      throw new IllegalArgumentException(errorMessage);
    }
    findAndSetScoreParameters(expectedCustomerScore, scoreMap);
  }

  private void findAndSetScoreParameters(final Integer expectedScore,
      final List<Map.Entry<Integer, String>> map) {
    for (final Map.Entry<Integer, String> item : map) {
      if (item.getKey() >= expectedScore) {
        final List<String> list = Arrays.asList(item.getValue().split(","));
        setAge(list.get(0));
        setEmailExtension(list.get(1));
        break;
      }
    }
  }

  private void getScoringParams(final RegistrationParams params) {
    LOG.info("Scoring param: {}", params.getScoring());
    if (params.getScoring() != null) {
      setCustomerWithScore(params.getScoring());
    }
  }
}
