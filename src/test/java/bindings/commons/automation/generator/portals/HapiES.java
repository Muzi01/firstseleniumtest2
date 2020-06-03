package bindings.commons.automation.generator.portals;

import com.google.common.collect.ImmutableList;
import com.ipfdigital.automation.aio.rest.dto.ApplicationScoringDTO;
import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.LoanPurpose;
import com.ipfdigital.automation.aio.rest.dto.LoanPurposeDTO;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData.CommunityMedianIncome;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData.ProbabilityOfDefault;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData.RiskMicroScore;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData.SavingsCapability;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData.TimeFromPortability;
import com.ipfdigital.automation.aio.rest.dto.TMXInitRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.UpdateCustomerDTO;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.ESCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.ESCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.ESRegistrationClient;
import com.ipfdigital.automation.aio.rest.v2.tmx.TmxClient;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GenerateCustomerDTO;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.exceptions.CustomerRegistrationException;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.DatabaseQuery;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.generator.utils.factory.DtoFactoryES;
import com.ipfdigital.automation.generator.utils.scores.ScoreES;
import com.ipfdigital.automation.generators.RandomUtils;
import com.ipfdigital.automation.mule.rest.MuleDocumentsUploader;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.database.connection.DBServiceProvider;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.ipfdigital.automation.generator.utils.factory.DtoFactoryES.*;
import static com.ipfdigital.automation.generator.utils.scores.ScoreES.*;

public class HapiES extends Portal {

  private static final Logger LOG = LogManager.getLogger(HapiES.class);

  // Values used in customer registration:
  private static final String OTP = "1234";
  private static final String TMX_TOP_DOMAIN = "hapicredito.com";
  private static final Integer TMX_SELECTED_AMOUNT = 50000;
  private static final LocalDate DATE_OF_BIRTH = LocalDate.now().minusYears(55);
  private static final LoanPurpose LOAN_PURPOSE = LoanPurpose.FINANCING_BIG_PURCHASE;
  private static final ImmutableList<String> SCORING_END_STEPS = ImmutableList
      .of("FINISHED", "REJECTED");
  private static final Duration SCORING_INTERVAL = Duration.ofSeconds(10);
  private static final Duration SCORING_TIMEOUT = Duration.ofMinutes(1);

  private EmploymentDuration employmentDuration = EmploymentDuration.YEARS_OVER_10;
  private Education education = Education.UNIVERSITY;
  private MaritalStatus maritalStatus = MaritalStatus.MARRIED;
  private Integer netIncome = 620000;
  private Occupation occupation = Occupation.FULL_TIME;
  private Integer savingsCapability = SavingsCapability.SAVINGS4.getRandomValueFromGivenRange();
  private Double probabilityOfDefault =
      ProbabilityOfDefault.PROBABILITY1.getRandomValueFromGivenRange();
  private Double timeFromPortability = TimeFromPortability.TIME_LOW.getRandomValueForGivenRange();
  private SpanishFinancialData.Mobile mobileOriginalOperator = SpanishFinancialData.Mobile.MOVISTAR;
  private CommunityMedianIncome communityMedianIncome = null;
  private SpanishFinancialData.NumIndicator numIndicator = SpanishFinancialData.NumIndicator.OTHER;
  private RiskMicroScore riskMicroScore = RiskMicroScore.C1;
  private final List<SFAttachmentType> attachmentsToSend =
      Arrays.asList(SFAttachmentType.PASSPORT_A, SFAttachmentType.PASSPORT_B,
          SFAttachmentType.IBAN_PROOF, SFAttachmentType.ADDRESS_PROOF,
          SFAttachmentType.INCOME_PROOF, SFAttachmentType.SELFIE);

  @Override
  public List<SFAttachmentType> getAttachmentTypesToUpload() {
    return attachmentsToSend;
  }

  private HapiES() {
    LOG.info("Instantiated HapiES portal.");
  }

  private static class SingletonHolder {
    static final HapiES instance = new HapiES();

    private SingletonHolder() {
    }
  }

  public static HapiES getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "ES market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {

    final Environment env = params.getEnvironment();

    verifyRegistrationParams(params);
    getScoringParams(params);
    final GeneratedCustomerDTO generatedData = getCustomerData(params);
    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.HAPI.name().toLowerCase(), Country.ES, restEventListener);
    final UserRegistrationInfoDTO registrationInfo = buildUserRegistrationInfo(generatedData);
    startCustomerRegistration(provider, generatedData, registrationInfo, params);
    final ProductDTO product =
        chooseProductAndSubmitApplication(provider, generatedData, params, registrationInfo);
    final Customer customer =
        updateCustomer(product, provider.provide(ESCustomerClient.class),
            generatedData.getIdentifier(), generatedData.getEmail(),
            env);
    notifyCustomerRegistered(customer);
    verification(params, customer);
    LOG.info(Portal.REGISTRATION_COMPLETE_PATTERN, env, customer.id);
    return customer;
  }

  @Override
  protected UserRegistrationInfoDTO buildUserRegistrationInfo(
      final GeneratedCustomerDTO generatedCustomerDTOES) {
    final UserRegistrationInfoDTO userRegistrationInfoDTO = new UserRegistrationInfoDTO();
    userRegistrationInfoDTO.ssn = generatedCustomerDTOES.getIdentifier();
    userRegistrationInfoDTO.msisdn = generatedCustomerDTOES.getPrefixedMsisdn();
    return userRegistrationInfoDTO;
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.ES || params.getBrand() != Brand.HAPI) {
      final String msg =
          String.format("HapiES can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
    if (params.getProductType() == ProductType.IL && !params.getForeignCustomer()) {
      throw new CustomerRegistrationException(
          "IL product is unavailable for local (DNI) customers!");
    }
  }

  private GeneratedCustomerDTO getCustomerData(final RegistrationParams params) {
    final GenerateCustomerDTO dto = new GenerateCustomerDTO(Country.ES,
        GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
        GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE, params.getForeignCustomer());
    final GeneratedCustomerDTO generatedData = customerGenerator.generate(dto);
    if (communityMedianIncome != null) {
      generatedData.getAddress().setPostcode(communityMedianIncome.getPostCode());
    }
    if (params.getCustomEmail()) {
      generatedData.setEmail(params.getCustomEmailValue());
    }
    if (params.getCustomSsn()) {
      generatedData.setIdentifier(params.getCustomSsnValue());
    }
    if (params.getCustomSsn()) {
      generatedData.setIdentifier(params.getCustomSsnValue());
    } else {
      generateNewSsnIfActualUsed(params, 5, generatedData);
    }
    return generatedData;
  }

  private void insertScore(final Environment env, final ScoreES score, final String ssn,
      final RegistrationParams registrationParams) {
    insertScore(env, score, ssn, registrationParams.isEquifaxCache());
  }

  public void insertScore(final Environment env, final ScoreES score, final String ssn) {
    insertScore(env, score, ssn, false);
  }

  private void insertScore(final Environment env, final ScoreES score, final String ssn,
      final boolean insertToEquifaxCache) {

    LOG.info("Inserting spanish {} for ssn: {} on environment: {}", score, ssn, env);
    try {
      DBServiceProvider.muleDBService()
          .callDB(env, INSERT_SCORE_TO_MULE_QUERY, statement -> {
            insertHocelotCacheData(score, ssn, statement);
            if (insertToEquifaxCache) {
              insertEquifaxCacheData(score, ssn, statement);
            }
            LOG.info("Successfully inserted spanish score {} for ssn: {} on environment: {}", score,
                ssn,
                env);
            return null;
          });
    } catch (final Exception e) {
      throw new CustomerRegistrationException(e);
    }
  }

  private void insertCacheData(final ScoreES scoreES, final String ssn, final String data,
      final PreparedStatement statement) {
    final String registryName = scoreES.getFullName();
    try {
      statement.setString(1, registryName);
      statement.setString(2, ssn);
      statement.setString(3, data);
      if (statement.executeUpdate() < 1) {
        LOG.error("Insert into MULE cache failed");
        throw new IllegalStateException(formatExceptionMessage(registryName, ssn));
      }
    } catch (final SQLException sqlException) {
      LOG.error("Exception executing statement to insert into MULE cache", sqlException);
      throw new IllegalStateException(formatExceptionMessage(registryName, ssn));
    }
  }

  private String formatExceptionMessage(final String registryName, final String ssn) {
    return String.format("Insert of [%s] into mule cache has failed! SSN: %s.", registryName, ssn);
  }

  private void insertHocelotCacheData(final ScoreES score, final String ssn,
      final PreparedStatement statement) {
    final String data = score.getMessageForHocelot(savingsCapability,
        probabilityOfDefault,
        timeFromPortability, mobileOriginalOperator.name());
    insertCacheData(HOCELOT, ssn, data, statement);
  }

  private void insertEquifaxCacheData(final ScoreES score, final String ssn,
      final PreparedStatement statement) {
    final String equifaxIncData = score.getIncMessageForEquifax();
    final String equifaxIdrsk2Data = score.getIdrsk2MessageForEquifax();
    final String equifaxIpfData =
        score.getIpfMessageForEquifax(numIndicator.getValue(), riskMicroScore);

    insertCacheData(EQUIFAX_IDRSK2, ssn, equifaxIdrsk2Data, statement);
    insertCacheData(EQUIFAX_INC, ssn, equifaxIncData, statement);
    insertCacheData(EQUIFAX_IPF, ssn, equifaxIpfData, statement);
  }

  private void startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData, final UserRegistrationInfoDTO registrationInfo,
      final RegistrationParams params) {
    insertScore(params.getEnvironment(), ScoreES.HOCELOT, generatedData.getIdentifier(), params);

    final ESRegistrationClient registrationClient = provider.provide(ESRegistrationClient.class);
    registrationClient.registerUser(registrationInfo);

    final ESCreditApplicationClient creditApplicationClient =
        provider.provide(ESCreditApplicationClient.class);
    creditApplicationClient.startNewApplication();

    final TmxClient tmxClient = provider.provide(TmxClient.class);

    tmxClient.tmxInitSession(new TMXInitRequestDTO(TMX_TOP_DOMAIN, TMX_SELECTED_AMOUNT));

    final ESCustomerClient customerClient = provider.provide(ESCustomerClient.class);
    customerClient.updateCustomerInfo(getCustomerInfo(generatedData));
    customerClient.updateMarketingConsent(createMarketingConsentList());

    final LoanPurposeDTO loanPurposeDTO = new LoanPurposeDTO();
    loanPurposeDTO.loanPurpose = LOAN_PURPOSE;
    creditApplicationClient.storeLoanPurpose(loanPurposeDTO);

    final ApplicationScoringDTO applicationScoring =
        creditApplicationClient.startScoring(prepareFinancialDataDTO());
    waitForScoringStepEnd(creditApplicationClient, applicationScoring);
  }

  private FinancialDataDTO prepareFinancialDataDTO() {
    return DtoFactoryES
        .createFinancialDataDTO(education, employmentDuration, maritalStatus,
            netIncome,
            occupation);
  }

  private void waitForScoringStepEnd(final ESCreditApplicationClient client,
      final ApplicationScoringDTO applicationScoring) {
    Awaitility
        .with()
        .atMost(SCORING_TIMEOUT)
        .pollInterval(SCORING_INTERVAL)
        .await()
        .until(() -> client.getScoring(applicationScoring.id), this::isScoringEnded);
  }

  private boolean isScoringEnded(final ApplicationScoringDTO applicationScoring) {
    return SCORING_END_STEPS.contains(applicationScoring.currentStep);
  }

  private UpdateCustomerDTO getCustomerInfo(final GeneratedCustomerDTO generatedData) {
    final LocalDate birthDate = (age == null) ? DATE_OF_BIRTH
        : LocalDate.now().minusYears(
            age);
    final UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO();
    updateCustomerDTO.customer = createCustomerDTO(generatedData, birthDate);

    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedData.getEmail();
    updateCustomerDTO.email = emailUpdateDTO;
    final PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();

    updateCustomerDTO.password = passwordUpdateDTO;
    updateCustomerDTO.address = createAddressDTO(generatedData.getAddress());
    return updateCustomerDTO;
  }

  private ProductDTO chooseProductAndSubmitApplication(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params,
      final UserRegistrationInfoDTO registrationInfo) {
    final ESCreditApplicationClient creditApplicationClient =
        provider.provide(ESCreditApplicationClient.class);
    final List<ProductDTO> productsList = creditApplicationClient.getAvailableCreditLineProducts();
    final ProductDTO product = chooseProductByFilters(params, productsList);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);
    final ProductSelectionDTO productSelection =
        buildProductSelectionDTO(product, params.getDrawPercentage());
    creditApplicationClient.selectProduct(productSelection);
    final ESCustomerClient customerClient = provider.provide(ESCustomerClient.class);
    final BankAccountUpdateDTO bankUpdateDTO = new BankAccountUpdateDTO();
    bankUpdateDTO.bankAccount = generatedData.getBankAccount().getNumber();
    customerClient
        .updateBankAccount(bankUpdateDTO);
    submitApplication(creditApplicationClient, generatedData, params, registrationInfo, product);
    return product;
  }

  private Customer updateCustomer(final ProductDTO product, final ESCustomerClient client,
      final String ssn, final String email, final Environment env) {

    final Customer customer = new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName, ssn);
    customer.password = findDefaultUserPass();
    customer.product = product;
    try {
      client.verifyEmail(
          DatabaseQuery.getEmailValidationKey(env.envName, customer.id.toString()),
          "es");
    } catch (final FeignException e) {
      handleExpectedHTTP303Code(e);
    }

    if (DatabaseQuery.checkEmailConfirmation(env.envName, customer.id.toString())) {
      customer.email = email;
    }
    return customer;
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    final boolean isDraw =
        params.getDrawPercentage() > 0 || params.getProductType() == ProductType.IL;
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
    handleIdentification(customer, Country.ES, environment);
    salesforceProvider.waitForBankAccountReplication(customer.id);
    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);
    new MuleDocumentsUploader().upload(customer.id, salesforceProvider, accountId,
        environment, getAttachmentTypesToUpload());
    tryCloseSFTask(salesforceProvider, SFTask.WAIT_UPLOAD_DOCUMENTS, customer.id);
    checkIfBankAccountIsInUseAndCloseTasks(salesforceProvider,
        Arrays.asList(SFTask.ADDRESS_IN_USE, SFTask.BANK_ACCOUNT_IN_USE), accountId,
        customer.id);
    if (isDraw) {
      salesforceProvider.addArchiveBankAccount(customer.id, bankAccount);
      tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
    }
    checkIfContractIsReady(salesforceProvider, accountId);
  }

  private String getBankAccount(final Customer customer) {
    if (customer.bankAccount == null) {
      final GenerateCustomerDTO dto =
          new GenerateCustomerDTO(Country.ES,
              GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
              GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE, RandomUtils.randomBoolean());
      return customerGenerator.generate(dto).getBankAccount().getNumber();
    }
    return customer.bankAccount;
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
  public void setSalary(final String newSalary) {

    netIncome = Integer.parseInt(newSalary);
  }

  @Override
  public Integer getSalary() {
    return netIncome;
  }

  @Override
  public void setMobile(final String mobile) {

    mobileOriginalOperator = SpanishFinancialData.Mobile.valueOf(mobile);
  }

  @Override
  public String getMobile() {
    return mobileOriginalOperator.name();
  }

  @Override
  public void setTimeFromPortability(final String newTimeFromPortability) {

    timeFromPortability = Double.parseDouble(newTimeFromPortability);
  }

  @Override
  public Double getTimeFromPortability() {
    return timeFromPortability;
  }

  @Override
  public void setProbabilityDefault(final String newProbabilityDefault) {

    probabilityOfDefault = Double.parseDouble(newProbabilityDefault);
  }

  @Override
  public Double getProbabilityDefault() {
    return probabilityOfDefault;
  }

  @Override
  public void setSavingsCapability(final String newSavingsCapability) {

    savingsCapability = Integer.parseInt(newSavingsCapability);
  }

  @Override
  public Integer getSavingsCapability() {
    return savingsCapability;
  }

  @Override
  public void setPostCode(final String postCode) {

    communityMedianIncome = CommunityMedianIncome.valueOf(postCode);
  }

  @Override
  public String getPostCode() {
    return communityMedianIncome.name();
  }

  @Override
  public void setNumIndicator(final String newNumIndicator) {

    numIndicator = SpanishFinancialData.NumIndicator.valueOf(newNumIndicator);
  }

  @Override
  public String getNumIndicator() {
    return numIndicator.name();
  }

  @Override
  public void setRiskMicroScore(final String newRiskMicroScore) {

    riskMicroScore = RiskMicroScore.valueOf(newRiskMicroScore);
  }

  @Override
  public String getRiskMicroScore() {
    return riskMicroScore.name();
  }

  @Override
  public Integer getCustomerTotalScore(final boolean isForeign) {
    final Integer ageValue = SpanishFinancialData.getAgeScoreValue(getAge());
    final Integer mobileValue = mobileOriginalOperator.getScoreValue();
    final Integer savingsValue =
        SpanishFinancialData.getSavingsCapabilityScoreValue(savingsCapability);
    final Integer probabilityValue =
        SpanishFinancialData.getProbabilityOfDefaultScoreValue(probabilityOfDefault);
    final Integer portabilityVale =
        SpanishFinancialData.getTimeFromPortabilityScoreValue(timeFromPortability);
    final Integer salaryValue = SpanishFinancialData.getSalaryScoreValue(getSalary());
    final Integer communityValue = communityMedianIncome.getScoreValue();
    final Integer foreignValue = SpanishFinancialData.getIsNieScoreValue(isForeign);
    final Integer hocelotValue =
        numIndicator.getScoreValue() + riskMicroScore.getScoreValue();
    return SpanishFinancialData.FIXED_SCORE + ageValue + mobileValue + savingsValue
        + probabilityValue + portabilityVale
        + salaryValue + communityValue + foreignValue + hocelotValue;
  }

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore, final boolean isNie) {
    final Integer isNieScoreValue = SpanishFinancialData.getIsNieScoreValue(isNie);
    final List<Map.Entry<Integer, String>> scoreMap = SpanishFinancialData.getScoreMap();
    final Integer minScore = scoreMap.get(0).getKey() + isNieScoreValue;
    final Integer maxScore = scoreMap.get(scoreMap.size() - 1).getKey() + isNieScoreValue;
    if (minScore > expectedCustomerScore || maxScore < expectedCustomerScore) {
      final String errorMessage =
          String.format("For Customer ES %s, Total Score Value can be in range <%d,%d>",
              (isNie) ? "NIE" : "DNI", minScore, maxScore);
      throw new IllegalArgumentException(errorMessage);
    }
    final Integer expectedSpanishCustomerScore = expectedCustomerScore - isNieScoreValue;
    findAndSetScoreParameters(expectedSpanishCustomerScore, scoreMap);
  }

  private void findAndSetScoreParameters(final Integer expectedScore,
      final List<Map.Entry<Integer, String>> map) {
    for (final Map.Entry<Integer, String> item : map) {
      if (item.getKey() >= expectedScore) {
        final List<String> list = Arrays.asList(item.getValue().split(","));
        setAge(list.get(0));
        setSalary(list.get(1));
        setPostCode(list.get(2));
        setNumIndicator(list.get(3));
        setRiskMicroScore(list.get(4));
        setSavingsCapability(list.get(5));
        setProbabilityDefault(list.get(6));
        setTimeFromPortability(list.get(7));
        setMobile(list.get(8));
        break;
      }
    }
  }

  private void getScoringParams(final RegistrationParams params) {
    LOG.info("Scoring param: {}", params.getScoring());
    if (params.getScoring() != null) {
      setCustomerWithScore(params.getScoring(), params.getForeignCustomer());
    }
  }

  private void submitApplication(final ESCreditApplicationClient client,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params,
      final UserRegistrationInfoDTO registrationInfo, final ProductDTO product) {
    final ApplicationSubmitDTO applicationSubmit =
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage());
    client.submitInitPin(registrationInfo);
    registrationInfo.otp = OTP;
    registrationInfo.email = generatedData.getEmail();
    client.submitValidatePin(registrationInfo);
    client.submitApplication(applicationSubmit);
  }
}
