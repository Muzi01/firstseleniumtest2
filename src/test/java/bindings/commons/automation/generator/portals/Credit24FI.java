package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.aio.rest.dto.AuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAuthenticationRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Loan;
import com.ipfdigital.automation.aio.rest.dto.MsisdnUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.MsisdnVerificationDTO;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.OccupationType;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.RegistrationDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Residence;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.PasswordGdprDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.RegistrationFinlandDTO;
import com.ipfdigital.automation.aio.rest.finishfinancialdata.FinnishFinancialData;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.SignicatMockHandlingService;
import com.ipfdigital.automation.aio.rest.v2.banks.FIBanksClient;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.GenericCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.FICustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.GenericRegistrationClient;
import com.ipfdigital.automation.config.PropertyProviderFactory;
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
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import feign.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Credit24FI extends Portal {

  private static final Logger LOG = LogManager.getLogger(Credit24FI.class);
  public static final String REQUEST = "request";
  public static final String STATE = "state";
  public static final String CODE = "code";
  private static final String SIGNICAT_CALLBACK = "signicat.callbackUrl";
  // Values used in customer registration:
  private static final String OTP = "1234";
  private static final String DEFAULT_GDPR_PASS = "Qwerty123";
  private static final CommunicationSettingsDTO.DeliveryMethod CONTRACT_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final CommunicationSettingsDTO.DeliveryMethod INVOICE_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final Boolean MARKETING_PERMISSION = true;
  private Education education = Education.UNIVERSITY;
  private Occupation occupation = Occupation.FIXED_TERM;
  private OccupationType occupationType = OccupationType.MANAGER;
  private EmploymentDuration employmentDuration = EmploymentDuration.MORE_THAN_THREE_YEARS;
  private Integer salary = 1_000_00;
  private static final Integer BENEFITS = 0;
  private static final Integer PENSION = 0;
  private static final Integer OTHER_INCOME = 0;
  private Integer housingCosts = 0;
  private Integer livingCosts = 0;
  private Integer otherLoans = 0;
  private ArrayList<Loan> loans = new ArrayList<>(Arrays.asList(Loan.CREDIT_CARD));
  private Residence residence = Residence.OWN;
  private Integer householdChildren = 0;

  private Credit24FI() {
    loans.add(Loan.HOME_LOAN);
    LOG.info("Instantiated Credit24FI portal.");
  }

  private static Credit24FI instance;

  public static synchronized Credit24FI getInstance() {
    if (instance == null) {
      instance = new Credit24FI();
    }
    return instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "FI market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params)
      throws UnsupportedEncodingException {
    verifyRegistrationParams(params);

    final Environment env = params.getEnvironment();

    final GeneratedCustomerDTO generatedData = getCustomerData(params, Country.FI, age);
    getScoringParams(params, generatedData.getEmail(), generatedData.getMsisdn());
    final AIOBackendRestClientProvider provider = getProvider(params);

    signicatAuthentication(env, generatedData.getIdentifier(),
        provider.provide(FIBanksClient.class));

    Customer customer = authenticateCustomer(provider.provide(GenericRegistrationClient.class),
        generatedData.getIdentifier(), env);
    notifyCustomerRegistered(customer);
    startCustomerRegistration(provider, generatedData);

    final ProductDTO product = chooseProductAndSubmitApplication(
        provider.provide(GenericCreditApplicationClient.class), generatedData, params);
    customer = fetchCustomerWithAdditionalData(customer, product, generatedData.getEmail(), env);

    verification(params, customer);
    emailVerification(env, generatedData.getEmail(), customer);
    LOG.info(REGISTRATION_COMPLETE_PATTERN, env, customer.id);
    return customer;
  }

  private AIOBackendRestClientProvider getProvider(final RegistrationParams params) {
    return new AIOBackendRestClientProvider(params.getEnvironment().envName,
        Brand.CREDIT24.name().toLowerCase(), Country.FI, restEventListener);
  }

  public Response signicatAuthentication(final Environment env, final String identifier,
      final FIBanksClient provider) throws UnsupportedEncodingException {
    final SignicatMockHandlingService signicatMockService =
        new SignicatMockHandlingService(env);

    final String signicatCallback = String.format(
        PropertyProviderFactory.propertyProvider(env.envName).getProperty(SIGNICAT_CALLBACK),
        env.envName);

    final BankAuthenticationResponseDTO bankAuthResponse =
        provider.bankAuthentication(new BankAuthenticationRequestDTO(signicatCallback));
    final String request = bankAuthResponse.parameters.get(REQUEST);

    final Response authResponse = signicatMockService.authorize(request);
    final String state = signicatMockService.extractFromLocationHeader(authResponse, STATE);

    final Response accResponse = signicatMockService.accept(state, identifier);
    final String code = signicatMockService.extractFromLocationHeader(accResponse, CODE);

    return provider.signicat(signicatCallback, code, state, state);
  }


  private void getScoringParams(final RegistrationParams params, final String email,
      final String msisdn) {
    LOG.info("Scoring param: {}", params.getScoring());
    if (params.getScoring() != null) {
      setCustomerWithScore(params.getScoring(), email, msisdn);
    }
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.FI || params.getBrand() != Brand.CREDIT24) {
      final String msg =
          String.format("Credit24FI can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
    if (params.getProductType() == ProductType.IL) {
      LOG.warn("IL product type is not available in Credit24FI. Applying for CL instead.");
    }
  }

  private Customer authenticateCustomer(final GenericRegistrationClient client,
      final String identifier,
      final Environment env) {
    final AuthenticationResponseDTO authenticationResponse = client.authenticate();
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName, identifier);
    LOG.debug("GDPR FI Customer ID from REST: {}, from DB: {}",
        authenticationResponse.customer.id, customer.id);
    return customer;
  }

  private void startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData) {
    final RegistrationDataDTO registrationData = prepareRegistrationData(generatedData);
    final GenericCreditApplicationClient creditApplicationClient =
        provider.provide(GenericCreditApplicationClient.class);
    creditApplicationClient.startNewApplication(registrationData);

    final FICustomerClient customerClient = provider.provide(FICustomerClient.class);

    final MsisdnUpdateDTO msisdnUpdateDTO = new MsisdnUpdateDTO();
    msisdnUpdateDTO.msisdn = generatedData.getMsisdn();
    customerClient.updateMsisdn(msisdnUpdateDTO);

    final RegistrationFinlandDTO registrationFinland = prepareRegistationForm(generatedData);
    customerClient.register(registrationFinland);
    customerClient.updateMarketingConsent(getMarketingConsentList());
    customerClient.updateCommunicationSettings(registrationFinland.communicationSettings);

    final BankAccountUpdateDTO bankAccountUpdateDTO = new BankAccountUpdateDTO();
    bankAccountUpdateDTO.bankAccount = generatedData.getBankAccount().getNumber();
    customerClient.validateBankAccount(bankAccountUpdateDTO);
    customerClient.updateBankAccount(bankAccountUpdateDTO);
    creditApplicationClient.storeFinancialData(prepareFinancialDataDTO());
  }

  private RegistrationFinlandDTO prepareRegistationForm(
      final GeneratedCustomerDTO generatedCustomerDTO) {
    final RegistrationFinlandDTO registrationFinland = new RegistrationFinlandDTO();
    registrationFinland.communicationSettings = getDefaultCommunicationSettings();

    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedCustomerDTO.getEmail();
    registrationFinland.email = emailUpdateDTO;

    final MsisdnVerificationDTO msisdnVerificationDTO = new MsisdnVerificationDTO();
    msisdnVerificationDTO.msisdn = generatedCustomerDTO.getMsisdn();
    msisdnVerificationDTO.otp = OTP;
    registrationFinland.msisdnVerification = msisdnVerificationDTO;

    final PasswordGdprDTO passwordGdprDTO = new PasswordGdprDTO();
    passwordGdprDTO.password = DEFAULT_GDPR_PASS;
    registrationFinland.password = passwordGdprDTO;

    return registrationFinland;
  }

  private CommunicationSettingsDTO getDefaultCommunicationSettings() {
    final CommunicationSettingsDTO communicationSettingsDTO = new CommunicationSettingsDTO();
    communicationSettingsDTO.contractDeliveryMethod = CommunicationSettingsDTO.DeliveryMethod.EMAIL;
    communicationSettingsDTO.invoiceDeliveryMethod = CommunicationSettingsDTO.DeliveryMethod.EMAIL;
    return communicationSettingsDTO;
  }

  private RegistrationDataDTO prepareRegistrationData(
      final GeneratedCustomerDTO generatedCustomerDTO) {
    final RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();

    final MsisdnVerificationDTO msisdnVerificationDTO = new MsisdnVerificationDTO();
    msisdnVerificationDTO.msisdn = generatedCustomerDTO.getMsisdn();
    msisdnVerificationDTO.otp = OTP;
    registrationDataDTO.msisdnVerification = msisdnVerificationDTO;

    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedCustomerDTO.getEmail();
    registrationDataDTO.email = emailUpdateDTO;

    final BankAccountUpdateDTO bankAccountUpdateDTO = new BankAccountUpdateDTO();
    bankAccountUpdateDTO.bankAccount = generatedCustomerDTO.getBankAccount().getNumber();
    registrationDataDTO.bankAccount = bankAccountUpdateDTO;

    final PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();

    registrationDataDTO.password = passwordUpdateDTO;

    final CommunicationSettingsDTO communicationSettingsDTO = new CommunicationSettingsDTO();
    communicationSettingsDTO.contractDeliveryMethod = CONTRACT_DELIVERY_METHOD;
    communicationSettingsDTO.invoiceDeliveryMethod = INVOICE_DELIVERY_METHOD;
    communicationSettingsDTO.marketingPermission = MARKETING_PERMISSION;
    registrationDataDTO.communicationSettings = communicationSettingsDTO;

    return registrationDataDTO;
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_3RD_PARTY, ConsentVersion.FI_3RD_PARTY, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.FI_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.FI_IPFD_CHANNELS, true));

    return marketingConsents;
  }

  private FinancialDataDTO prepareFinancialDataDTO() {
    final Loan[] loansArray = new Loan[loans.size()];
    loans.toArray(loansArray);

    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.education = education;
    financialDataDTO.occupation = occupation;
    financialDataDTO.occupationType = occupationType;
    financialDataDTO.employmentDuration = employmentDuration;
    financialDataDTO.salary = salary;
    financialDataDTO.benefits = BENEFITS;
    financialDataDTO.pension = PENSION;
    financialDataDTO.otherIncome = OTHER_INCOME;
    financialDataDTO.housingCosts = housingCosts;
    financialDataDTO.livingCosts = livingCosts;
    financialDataDTO.otherLoans = otherLoans;
    financialDataDTO.loans = loansArray;
    financialDataDTO.residence = residence;
    financialDataDTO.householdChildren = householdChildren;
    return financialDataDTO;
  }

  private ProductDTO chooseProductAndSubmitApplication(final GenericCreditApplicationClient client,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params) {
    final List<ProductDTO> productsList = client.getAvailableCreditLineProducts();
    final ProductDTO product = chooseProductByFilters(params, productsList);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);
    client.selectProduct(buildProductSelectionDTO(product, params.getDrawPercentage()));
    client.submitApplication(
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage()));
    return product;
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    final boolean isDraw = params.getDrawPercentage() > 0;
    if (params.getVerification()) {
      verifyCustomer(customer, isDraw, params.getEnvironment());
    }
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {
    final String bankAccount = getBankAccount(customer);
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    try {
      tryCloseSFTask(salesforceProvider, SFTask.INVESTIGATION, customer.id);
    } catch (SalesForceExecutionException e) {
      LOG.info("no investigation tasks were closed", e);
    }
    if (salesforceProvider.isMissingDocuments(customer.id)) {
      salesforceProvider.uploadAttachment(accountId, SFAttachmentType.SALARY);
      salesforceProvider.uploadAttachment(accountId, SFAttachmentType.OTHER);
      salesforceProvider.uploadAttachment(accountId, SFAttachmentType.BANK_STATEMENT);
      tryCloseSFTask(salesforceProvider, SFTask.WAIT_UPLOAD_DOCUMENTS, customer.id);
    }
    if (isDraw) {
      salesforceProvider.addArchiveBankAccount(customer.id, bankAccount);
      tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
    }
    checkIfContractIsReady(salesforceProvider, accountId);
  }

  @Override
  public void verifyDrawCase(final Customer customer, final Environment environment) {
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final String bankAccount = getBankAccount(customer);
    salesforceProvider.addArchiveBankAccount(customer.id, bankAccount);
    salesforceProvider.closeTask(customer.id, SFTask.PAY_MONEY);
  }

  private String getBankAccount(final Customer customer) {
    if (customer.bankAccount == null) {
      final GenerateCustomerDTO dto = new GenerateCustomerDTO(Country.FI,
          GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
          GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE);
      return customerGenerator.generate(dto).getBankAccount().getNumber();
    }
    return customer.bankAccount;
  }

  @Override
  public void checkVerificationImplementation() {
    // Implemented
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
  public void setEducation(final String newEducation) {
    education = Education.valueOf(newEducation);
  }

  @Override
  public String getEducation() {
    return null;
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
  public void setHousingCosts(final String newHousingCosts) {
    housingCosts = Integer.parseInt(newHousingCosts);
  }

  @Override
  public Integer getHousingCosts() {
    return housingCosts;
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
  public void setOccupation(final String newOccupation) {
    occupation = Occupation.valueOf(newOccupation);
  }

  @Override
  public String getOccupation() {
    return occupation.name();
  }

  @Override
  public void setOccupationType(final String newOccupationType) {
    if (newOccupationType.isEmpty()) {
      occupationType = null;
    } else {
      occupationType = OccupationType.valueOf(newOccupationType);
    }
  }

  @Override
  public String getOccupationType() {
    if (occupationType == null) {
      return null;
    } else {
      return occupationType.name();
    }
  }

  @Override
  public void setOtherLoans(final String newOtherLoans) {
    otherLoans = Integer.parseInt(newOtherLoans);
  }

  @Override
  public Integer getOtherLoans() {
    return otherLoans;
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
    salary = Integer.parseInt(newSalary);
  }

  @Override
  public Integer getSalary() {
    return salary;
  }

  @Override
  public void setLoan(final String loan) {
    loans = new ArrayList<>();
    if (StringUtils.isNotBlank(loan)) {
      loans.addAll(Arrays
          .stream(loan.split(","))
          .map(Loan::valueOf)
          .collect(Collectors.toList()));
    }
  }

  @Override
  public List<String> getLoan() {
    return loans.stream().map(Loan::name).collect(Collectors.toList());
  }

  @Override
  public Integer getCustomerTotalScore() {
    final Integer ageValue = FinnishFinancialData.getAgeScoreValue(getAge());
    final Integer residenceValue = FinnishFinancialData.getResidenceScoreValue(getResidence());
    final Integer occupationValue =
        FinnishFinancialData.getOccupationScoreValue(getOccupation(), getOccupationType());
    final Integer housingCostValue =
        FinnishFinancialData.getHousingCostScoreValue(getHousingCosts());
    final Integer debtToIncomeValue = FinnishFinancialData.getDebtToIncomeScoreValue(
        getHousingCosts() + getOtherLoans() + getLivingCosts(), getSalary());

    final Integer smallLoanValue = FinnishFinancialData.getSmallLoanScoreValue(getLoan());
    final Integer daysAtAddressValue = FinnishFinancialData.provideDaysAtAddressScoreValue();
    return FinnishFinancialData.FIXED_SCORE + ageValue + residenceValue + occupationValue
        + housingCostValue + debtToIncomeValue + smallLoanValue
        + daysAtAddressValue;
  }

  @Override
  public void setCustomerWithScore(
      final Integer expectedCustomerScore, final String email, final String msisdn) {
    setLivingCosts("0");
    setOtherLoans("0");
    final List<Map.Entry<Integer, String>> scoreMap =
        FinnishFinancialData.getScoreMap(salary, email, msisdn);

    findAndSetScoreParameters(expectedCustomerScore, scoreMap);
  }

  private void findAndSetScoreParameters(final Integer expectedScore,
      final List<Map.Entry<Integer, String>> scoreMap) {
    final Integer minScore = scoreMap.get(0).getKey();
    final Integer maxScore = scoreMap.get(scoreMap.size() - 1).getKey();

    final List<String> list = scoreMap.stream().filter(item -> item.getKey() >= expectedScore)
        .map(item -> Arrays.asList(item.getValue().split(",")))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String
            .format("For FI Client Total Score Value can be in range <%d,%d>", minScore,
                maxScore)));

    setLoan(list.get(0));
    setResidence(list.get(1));
    setHousingCosts(list.get(2));

  }
}
