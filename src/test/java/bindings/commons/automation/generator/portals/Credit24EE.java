package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.aio.rest.dto.AuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.EstonianFinancialData;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Loan;
import com.ipfdigital.automation.aio.rest.dto.LoanDTO;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.MsisdnUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.MsisdnVerificationDTO;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.OccupationType;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.RegistrationDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Residence;
import com.ipfdigital.automation.aio.rest.dto.gdpr.AuthpostofficeDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.banks.EEBanksClient;
import com.ipfdigital.automation.aio.rest.v2.banks.MobileIdAuthRequestDTO;
import com.ipfdigital.automation.aio.rest.v2.banks.MobileIdAuthResponseDTO;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.EECreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.GenericCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.GenericCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.GenericRegistrationClient;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.model.aio.CreditApplication;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.database.connection.DBServiceProvider;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Credit24EE extends Portal {

  private static final String PENDING = "PENDING";
  private static final Logger LOG = LogManager.getLogger(Credit24EE.class);

  // Values used in customer registration:
  private static final String OTP = "1234";
  private static final CommunicationSettingsDTO.DeliveryMethod CONTRACT_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final CommunicationSettingsDTO.DeliveryMethod INVOICE_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final Boolean SMS_REMINDER = true;
  private String preferredLanguage = "et";
  private MaritalStatus maritalStatus = MaritalStatus.SINGLE;
  private Education education = Education.UNIVERSITY;
  private Occupation occupation = Occupation.FULL_TIME;
  private OccupationType occupationType = OccupationType.WORKER;
  private EmploymentDuration employmentDuration = EmploymentDuration.YEARS_OVER_10;
  private static final String COMPANY = "pzu";
  private Integer netIncome = 300000;
  private Integer livingCosts = 10000;
  private final ArrayList<Loan> debtTypesExperienced = new ArrayList<>();
  private Residence residence = Residence.RENT;
  private Integer householdChildren = 0;
  private EstonianFinancialData.WeekdayHourOfApplication weekdayHourOfApplication =
      EstonianFinancialData.WeekdayHourOfApplication.WORKING;
  private static final String AUTH_POST_OFFICE_ID = "54";

  private final LoanDTO loanDTO = buildLoanDTO(Loan.HOME_LOAN);

  private final LoanDTO[] otherDebts = new LoanDTO[] {loanDTO};


  private static final String UPDATED_CREDIT_APLICATION_DATES = "UPDATE CreditApplication ca " +
      "SET ca.created = DATE_FORMAT(ca.created, REPLACE('%Y-%m-%d %H:%i:%s', '%H', ?)), " +
      "ca.entityVersion = ca.entityVersion+1 " +
      "WHERE ca.customer_ID = ?";

  private Credit24EE() {
    LOG.info("Instantiated Credit24EE portal.");
  }

  private static class SingletonHolder {
    static final Credit24EE instance = new Credit24EE();

    private SingletonHolder() {
    }
  }

  public static Credit24EE getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "EE market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {

    final Environment env = params.getEnvironment();
    preferredLanguage = params.getCommunicationLanguage();

    verifyRegistrationParams(params);
    getScoringParams(params);

    final AIOBackendRestClientProvider provider = getAioRestClientProvider(params);
    final GeneratedCustomerDTO generatedData = getCustomerData(params, Country.EE, age);

    mobileIdRequest(provider.provide(EEBanksClient.class), generatedData.getIdentifier(),
        generatedData.getMsisdn());

    Customer customer = authenticateCustomer(
        provider.provide(GenericRegistrationClient.class), generatedData.getIdentifier(), env);
    notifyCustomerRegistered(customer);
    startCustomerRegistration(provider, generatedData, customer, env);
    final ProductDTO product = chooseProductAndSubmitApplication(
        provider.provide(EECreditApplicationClient.class), generatedData, params);
    customer = fetchCustomerWithAdditionalData(customer, product, generatedData.getEmail(), env);
    if (params.getVerification()) {
      verification(params, customer);
    }
    sendVerificationRequest(env, provider, customer, "ee");
    LOG.info(REGISTRATION_COMPLETE_PATTERN, env, customer.id);
    return customer;
  }

  private AIOBackendRestClientProvider getAioRestClientProvider(final RegistrationParams params) {
    return new AIOBackendRestClientProvider(params.getEnvironment().envName,
        Brand.CREDIT24.name().toLowerCase(), Country.EE, restEventListener);
  }


  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.EE || params.getBrand() != Brand.CREDIT24) {
      final String msg =
          String.format("Credit24EE can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
  }

  private void mobileIdRequest(final EEBanksClient client, final String identifier,
      final String msisdn) {

    final MobileIdAuthRequestDTO mobileIdAuthRequestDTO =
        new MobileIdAuthRequestDTO(identifier, msisdn);
    final MobileIdAuthResponseDTO mobileIdAuthResponse =
        client.mobileIdAuth(mobileIdAuthRequestDTO);
    try {
      client.mobileIdVerify(mobileIdAuthResponse);
    } catch (final FeignException e) {
      handleExpectedHTTP303Code(e);
    }

  }

  private Customer authenticateCustomer(final GenericRegistrationClient client,
      final String identifier,
      final Environment env) {
    final AuthenticationResponseDTO authenticationResponse = client.authenticate();
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName, identifier);
    LOG.debug("GDPR EE Customer ID from REST: {}, from DB: {}",
        authenticationResponse.customer.id, customer.id);
    return customer;
  }

  private void startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData,
      final Customer customer, final Environment environment) {
    final RegistrationDataDTO registrationData = prepareRegistrationData(generatedData);
    final GenericCreditApplicationClient creditApplicationClient =
        provider.provide(GenericCreditApplicationClient.class);
    creditApplicationClient.startNewApplication(registrationData);
    updateApplicationHour(customer.id, environment);
    final GenericCustomerClient customerClient = provider.provide(GenericCustomerClient.class);

    final MsisdnUpdateDTO msisdnUpdateDTO = new MsisdnUpdateDTO();
    msisdnUpdateDTO.msisdn = generatedData.getMsisdn();
    customerClient.updateMsisdn(msisdnUpdateDTO);

    customerClient.updateMarketingConsent(buildMarketingConsentList());
    customerClient.register(registrationData);
    final AuthpostofficeDTO authpostoffice = new AuthpostofficeDTO();
    authpostoffice.authenticationPostOfficeId = AUTH_POST_OFFICE_ID;
    creditApplicationClient.updateAuthPostOffice(authpostoffice);
    creditApplicationClient.storeFinancialData(prepareFinancialDataDTO());
  }

  private RegistrationDataDTO prepareRegistrationData(final GeneratedCustomerDTO generatedData) {
    final RegistrationDataDTO dataDTO = new RegistrationDataDTO();
    dataDTO.address = mapToDTO(generatedData.getAddress(), generatedData.getCountry());

    final BankAccountUpdateDTO bankAccountUpdateDTO = new BankAccountUpdateDTO();
    bankAccountUpdateDTO.bankAccount = generatedData.getBankAccount().getNumber();
    dataDTO.bankAccount = bankAccountUpdateDTO;

    final CommunicationSettingsDTO communicationSettingsDTO = new CommunicationSettingsDTO();
    communicationSettingsDTO.contractDeliveryMethod = CONTRACT_DELIVERY_METHOD;
    communicationSettingsDTO.invoiceDeliveryMethod = INVOICE_DELIVERY_METHOD;
    communicationSettingsDTO.preDueDateReminderSMS = SMS_REMINDER;
    dataDTO.communicationSettings = communicationSettingsDTO;

    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedData.getEmail();
    dataDTO.email = emailUpdateDTO;

    final MsisdnVerificationDTO verificationDTO = new MsisdnVerificationDTO();
    verificationDTO.otp = OTP;
    verificationDTO.msisdn = generatedData.getMsisdn();
    dataDTO.msisdnVerification = verificationDTO;

    final PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();

    dataDTO.password = passwordUpdateDTO;
    dataDTO.preferredLanguage = preferredLanguage;
    return dataDTO;
  }

  private void updateApplicationHour(final Long customerID, final Environment env) {

    DBServiceProvider.aioDBService()
        .callDB(env, UPDATED_CREDIT_APLICATION_DATES, statement -> {
          statement.setString(1, weekdayHourOfApplication.getHour());
          statement.setLong(2, customerID);
          if (statement.executeUpdate() < 1) {
            throw new IllegalStateException("Unable to update hour of CreditApplication.created.");
          }
          return null;
        });
  }

  private List<MarketingConsentDTO> buildMarketingConsentList() {

    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_3RD_PARTY, ConsentVersion.EE_3RD_PARTY, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_DATA_TRANSFER, ConsentVersion.EE_NA, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.EE_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.EE_IPFD_CHANNELS, true));

    return marketingConsents;
  }

  private FinancialDataDTO prepareFinancialDataDTO() {
    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.maritalStatus = maritalStatus;
    financialDataDTO.education = education;
    financialDataDTO.occupation = occupation;
    financialDataDTO.occupationType = occupationType;
    financialDataDTO.employmentDuration = employmentDuration;
    financialDataDTO.company = COMPANY;
    financialDataDTO.netIncome = netIncome;
    financialDataDTO.livingCosts = livingCosts;
    financialDataDTO.debtTypesExperienced = debtTypesExperienced;
    financialDataDTO.residence = residence;
    financialDataDTO.householdChildren = householdChildren;
    financialDataDTO.otherDebts = new LoanDTO[] {buildLoanDTO(Loan.HOME_LOAN)};
    return financialDataDTO;
  }

  private ProductDTO chooseProductAndSubmitApplication(final EECreditApplicationClient client,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params) {
    final List<ProductDTO> productsList =
        params.getProductType() == ProductType.CL ? client.getAvailableCreditLineProducts()
            : client.getAvailableInstallmentProducts();
    ProductDTO product = chooseProductByFilters(params, productsList);
    product = getMaxProductFromAutoApprove(client, product);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);

    client.selectProduct(buildProductSelectionDTO(product, params.getDrawPercentage()));
    client.submitApplication(
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage()));
    return product;
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(params.getEnvironment());
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    final boolean isDraw = params.getDrawPercentage() > 0 ||
        params.getProductType() == ProductType.IL;

    verifyCustomer(customer, isDraw, params.getEnvironment());
    if (isDraw) {
      verifyDrawCase(customer, params.getEnvironment());
      checkIfContractIsReady(salesforceProvider, accountId);
    }
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {
    handleIdentification(customer, Country.EE, environment);

    final CreditApplication creditApplication = (CreditApplication) new DAOFactory()
        .getCreditApplicationDAO().getByCustomerId(customer.id, environment.envName);
    if (creditApplication.state.equals(PENDING)) {
      final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
      tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);
    }
  }

  @Override
  public void verifyDrawCase(final Customer customer, final Environment environment) {
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
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

    education = (newEducation.isEmpty() || newEducation.equals("MISSING")) ? null
        : Education.valueOf(newEducation);
  }

  @Override
  public String getEducation() {
    return (education == null) ? "MISSING" : education.name();
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

    residence = (newResidence.isEmpty()) ? null : Residence.valueOf(newResidence);
  }

  @Override
  public String getResidence() {
    return (residence == null) ? "OTHER" : residence.name();
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
  public void setDebt(final String newDebt) {

    otherDebts[0] = buildLoanDTO(Loan.valueOf(newDebt));
  }

  @Override
  public String getDebt() {
    return otherDebts[0].type.name();
  }

  @Override
  public void setLoan(final String loan) {

    debtTypesExperienced.clear();
    if (!loan.isEmpty()) {
      final String[] list = loan.split(",");
      for (final String i : list) {
        debtTypesExperienced.add(Loan.valueOf(i));
      }
    }
  }

  @Override
  public List<String> getLoan() {
    final List<String> list = new ArrayList<>();
    for (final Loan x : debtTypesExperienced) {
      list.add(x.name());
    }
    return list;
  }

  @Override
  public String getApplicationWeekdayHour() {
    return weekdayHourOfApplication.name();
  }

  @Override
  public void setApplicationWeekdayHour(final String applicationWeekdayHour) {

    weekdayHourOfApplication =
        EstonianFinancialData.WeekdayHourOfApplication.valueOf(applicationWeekdayHour);
  }

  @Override
  public Integer getCustomerTotalScore() {
    final int ageValue = EstonianFinancialData.getAgeScoreValue(getAge());
    final int debtValue = EstonianFinancialData.getDebtScoreValue(getDebt());
    final int educationValue = EstonianFinancialData.getEducationScoreValue(getEducation());
    final int residenceValue = EstonianFinancialData.getResidenceScoreValue(getResidence());
    final int hourValue =
        EstonianFinancialData.getWeekdayHourScoreValue(getApplicationWeekdayHour());
    return EstonianFinancialData.FIXED_SCORE + ageValue + debtValue + educationValue
        + residenceValue + hourValue;
  }

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    final List<Map.Entry<Integer, String>> scoreMap = EstonianFinancialData.getScoreMap();
    final Integer minScore = scoreMap.get(0).getKey();
    final Integer maxScore = scoreMap.get(scoreMap.size() - 1).getKey();
    if (minScore > expectedCustomerScore || maxScore < expectedCustomerScore) {
      final String errorMessage = String
          .format("For Customer EE, Total Score Value can be in range <%d,%d>", minScore, maxScore);
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
        setDebt(list.get(1));
        setEducation(list.get(2));
        setResidence(list.get(3));
        setApplicationWeekdayHour(list.get(4));
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

  private LoanDTO buildLoanDTO(final Loan type) {
    final LoanDTO loanDTO = new LoanDTO();
    loanDTO.type = type;
    loanDTO.issuerKey = "employer";
    loanDTO.monthlyPayment = 10_000;
    loanDTO.lastPaymentDate = "2017";
    return loanDTO;
  }
}
