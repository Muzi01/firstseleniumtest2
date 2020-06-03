package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.aio.rest.dto.AustralianFinancialData;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.Residence;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.dto.au.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.au.BankScrapingInitStatusDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ConsentLegalPageDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ConsentsDTO;
import com.ipfdigital.automation.aio.rest.dto.au.CreditApplicationDTO;
import com.ipfdigital.automation.aio.rest.dto.au.CustomerAUDTO;
import com.ipfdigital.automation.aio.rest.dto.au.CustomerAUWrapperDTO;
import com.ipfdigital.automation.aio.rest.dto.au.EmployerDTO;
import com.ipfdigital.automation.aio.rest.dto.au.IncomeDTO;
import com.ipfdigital.automation.aio.rest.dto.au.LivingExpensesDTO;
import com.ipfdigital.automation.aio.rest.dto.au.Passport;
import com.ipfdigital.automation.aio.rest.dto.au.ProductCreditLineAU;
import com.ipfdigital.automation.aio.rest.dto.au.ProductInstallmentAUDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ScoringAUDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.AUCreditApplication;
import com.ipfdigital.automation.aio.rest.v2.registration.AURegistrationClient;
import com.ipfdigital.automation.aio.rest.v2.scoring.AUScoringClient;
import com.ipfdigital.automation.aio.soap.bankscraping.BankScrapingProviderTypeEnum;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.customer.GenerateCustomerDTO;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.customer.employment.EmploymentType;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.AIODataUtils;
import com.ipfdigital.automation.generator.utils.DatabaseQuery;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductLevel;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.generator.utils.UUIDCompressor;
import com.ipfdigital.automation.generator.utils.scores.CreditBureauAU;
import com.ipfdigital.automation.generator.utils.scores.ScoreAU;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.database.connection.DBServiceProvider;
import com.ipfdigital.database.connection.MuleDBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.ipfdigital.automation.generator.utils.Utils.safeWait;
import static com.ipfdigital.automation.generator.utils.messages.ApplicationScoringStateAU.*;

public class Credit24AU extends Portal {

  private static final Logger LOG = LogManager.getLogger(Credit24AU.class);
  private static final String OTP = "1234";
  private static final String PRODUCT_TYPE_ON_CREATION = "CREDIT_LINE";
  private static final String PROVISO = "Proviso";
  private static final String BANK_SCRAPING_REPORT_PREFIX = "scoring.au.";
  private static final String IS_USED_LAST_NAME_AND_DOB_IN_CUSTOMER_TABLE_QUERY =
      "SELECT COUNT(ID) FROM Customer where lastName = ? and dateOfBirth = ?";
  private static final Integer DEFS_PROCESSING_MAX_TIME = 80;
  private static final String GEN_UI_NEXT_LINE = "<br>";
  private int minAge = 20;
  private int maxAge = 64;
  private static final Residence RESIDENCE = Residence.OWNER;
  private static final MaritalStatus MARITAL_STATUS_SINGLE = MaritalStatus.SINGLE;
  private static final EmploymentType EMPLOYMENT_TYPE = EmploymentType.FULL_TIME;
  private static final AustralianFinancialData.ResidencyStatus RESIDENCY_STATUS =
      AustralianFinancialData.ResidencyStatus.AU_CITIZEN;
  private static final AustralianFinancialData.EducationLevel EDUCATION_LEVEL =
      AustralianFinancialData.EducationLevel.SCHOOL_TRADE;
  private static final Gender GENDER_MALE = Gender.MALE;
  private static final AustralianFinancialData.Period PERIOD =
      AustralianFinancialData.Period.MONTHLY;
  private String auBankScrapingResponse;
  private static final ScoreAU SCORE_255 = ScoreAU.SCORE_255;
  private Environment environment;

  private Credit24AU() {
    LOG.info("Instantiated Credit24AU portal.");
  }

  private static class SingletonHolder {
    private SingletonHolder() {
    }

    static final Credit24AU instance = new Credit24AU();
  }

  public static Credit24AU getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    return registerCustomerUsingGeneratedData(params, provider, getCustomerData(params));
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {

    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.CREDIT24.name().toLowerCase(), params.getCountry(), restEventListener);
    final GeneratedCustomerDTO generatedData = getCustomerData(params);
    registerCustomerUsingGeneratedData(params, provider, generatedData);

    final CustomerAUWrapperDTO customerAUWrapper = new CustomerAUWrapperDTO();
    customerAUWrapper.customer = prepareCustomerAU(generatedData, params);
    provider.provide(AUCreditApplication.class).updateCustomerInfo(customerAUWrapper);
    final Passport passportNumber = getPassportNumberAU(generatedData);
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName,
            passportNumber.number);
    notifyCustomerRegistered(customer);
    verifyCustomerEmail(generatedData, customer, environment);
    startCustomerApplicationProcess(params, provider, customerAUWrapper, customer);
    verification(params, customer);
    return customer;
  }

  private Customer registerCustomerUsingGeneratedData(final RegistrationParams params,
      final AIOBackendRestClientProvider provider, final GeneratedCustomerDTO customerDTO) {
    verifyRegistrationAndScoringParams(params);
    environment = params.getEnvironment();

    final UserRegistrationInfoDTO dto =
        new UserRegistrationInfoDTO();
    dto.msisdn = customerDTO.getPrefixedMsisdn();
    startCustomerRegistration(params, customerDTO, provider, dto);
    final Customer customer =
        new DAOFactory().getCustomerDAO().fromAIObyEmail(environment.envName,
            customerDTO.getEmail());
    notifyCustomerRegistered(customer);
    return customer;
  }

  private void verifyCustomerEmail(final GeneratedCustomerDTO generatedData,
      final Customer customer,
      final Environment env) {

    DatabaseQuery.updateEmailVerification(env.envName, generatedData.getEmail());
    DatabaseQuery.updateIdentificationStatus(env.envName, customer.id.toString());
  }

  private void verifyRegistrationAndScoringParams(final RegistrationParams params) {
    if (params.getCountry() != Country.AU)
      throw new IllegalArgumentException("Can't register customers for " + params.getCountry());
    if (params.getBrand() != Brand.CREDIT24)
      throw new IllegalArgumentException("Can't register customers for " + params.getBrand());
    if (params.getScoring() != null) {
      getScoringParams(params);
    }
  }

  private void getScoringParams(final RegistrationParams params) {
    LOG.info("Scoring param: {}", params.getScoring());
    setCustomerWithScore(params.getScoring());
  }

  /**
   * setCustomerWithScore set scoring based on requested value on AUS most of scoring calculation
   * parameters is took from Proviso report and Veda/DnB calculation which are external and we have
   * no impact for them int this method only age and income value from Proviso report is changed
   * also there are switch of Proviso reports given from iFrame if more scoring options are needed
   * the other parameters in Proviso report need to be changed before generating a Customer
   */

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    final ScoreAU scoring = ScoreAU.getScoreAUForScoreValue(expectedCustomerScore);
    ScoreAU.loadScoringProperties();
    setUpdateMessage(scoring.getScoringProperties());
    auBankScrapingResponse = getUpdateMessage(scoring.name(), scoring.baseIncomeValue);
    minAge = scoring.minAge;
    maxAge = scoring.maxAge;
  }

  private void setUpdateMessage(final Properties scoringProperties) {
    for (final CreditBureauAU creditBureau : CreditBureauAU.values()) {
      Portal.updateMessages.put(creditBureau.toString(),
          scoringProperties.getProperty(BANK_SCRAPING_REPORT_PREFIX + creditBureau.toString()));
    }
    for (final ScoreAU scoreAU : ScoreAU.values()) {
      Portal.updateMessages.put(scoreAU.name(),
          scoringProperties.getProperty(scoreAU.reportName));
    }
  }

  private String getUpdateMessage(final String reportName, final double baseIncomeValue) {
    return Portal.updateMessages.get(reportName)
        .replace("%baseIncomeValue%", Double.toString(baseIncomeValue));
  }

  private GeneratedCustomerDTO getCustomerData(final RegistrationParams params) {
    final GenerateCustomerDTO dto =
        new GenerateCustomerDTO(Country.AU, minAge, maxAge);
    GeneratedCustomerDTO generatedData = customerGenerator.generate(dto);
    generatedData = generateNewDataSetIfActualUsed(generatedData, 5, params.getEnvironment());
    if (params.getCustomEmail())
      generatedData.setEmail(params.getCustomEmailValue());
    if (params.getCustomSsn()) {
      generatedData.setPassport(params.getCustomSsnValue());
    } else {
      generateNewSsnIfActualUsed(params, 5, generatedData);
    }
    return generatedData;
  }

  private GeneratedCustomerDTO generateNewDataSetIfActualUsed(GeneratedCustomerDTO generatedData,
      final int maxTries,
      final Environment environment) {
    final int numberOfTry = 0;
    while (isAlreadyRegistered(generatedData, environment) && numberOfTry <= maxTries) {
      final GenerateCustomerDTO dto =
          new GenerateCustomerDTO(Country.AU, minAge, maxAge);
      generatedData = customerGenerator.generate(dto);
    }
    if (numberOfTry > maxTries) {
      throw new IllegalArgumentException(
          String.format("Did not generated free data after %s retries", numberOfTry));
    }
    return generatedData;
  }

  private boolean isAlreadyRegistered(final GeneratedCustomerDTO generatedData,
      final Environment env) {

    return getCustomerWithLastNameAndDoB(generatedData, env).equals("1");
  }

  private String getCustomerWithLastNameAndDoB(final GeneratedCustomerDTO generatedData,
      final Environment environment) {

    return DBServiceProvider.aioDBService()
        .getQueryResult(environment, IS_USED_LAST_NAME_AND_DOB_IN_CUSTOMER_TABLE_QUERY, "COUNT(ID)",
            generatedData.getLastName(), generatedData.getDateOfBirth());
  }

  private Passport getPassportNumberAU(final GeneratedCustomerDTO generatedData) {

    final Passport passport = new Passport();
    passport.number = generatedData.getPassport();
    return passport;
  }

  private CustomerAUDTO prepareCustomerAU(
      final GeneratedCustomerDTO generatedData, final RegistrationParams params) {
    final AddressDTO address = prepareAddress(generatedData);
    final CustomerAUDTO customerAU = new CustomerAUDTO();
    customerAU.address = address;
    customerAU.passport = getPassportNumberAU(generatedData);
    customerAU.gender = GENDER_MALE.name();
    customerAU.creditApplication = prepareCreditApplication(params);
    return customerAU;
  }

  private CreditApplicationDTO prepareCreditApplication(final RegistrationParams params) {
    final CreditApplicationDTO creditApplicationDto = new CreditApplicationDTO();
    final String incomeFrequency =
        params.getIncomeFrequency() == null ? PERIOD.name() : params.getIncomeFrequency();
    addCustomerInfo(creditApplicationDto);
    addFinancialData(creditApplicationDto, incomeFrequency);
    return creditApplicationDto;
  }

  private void addCustomerInfo(final CreditApplicationDTO creditApplication) {
    final EmployerDTO employer = new EmployerDTO();
    employer.type = EMPLOYMENT_TYPE.name();

    creditApplication.maritalStatus = MARITAL_STATUS_SINGLE.name();
    creditApplication.residencyStatus = RESIDENCY_STATUS.name();
    creditApplication.soleEarnerIndicator = true;
    creditApplication.numberOfDependants = 0;
    creditApplication.employer = employer;
    creditApplication.educationLevel = EDUCATION_LEVEL.name();
  }

  private void addFinancialData(final CreditApplicationDTO creditApplication,
      final String incomeFrequency) {
    final IncomeDTO income = new IncomeDTO();
    income.amount = 2_400_000;
    income.frequency = incomeFrequency;
    income.nextPayDay = setNextPayDayList();

    final LivingExpensesDTO livingExpenses = new LivingExpensesDTO();
    livingExpenses.housingCosts = 10_000;
    livingExpenses.housingCostsFrequency = incomeFrequency;
    livingExpenses.foodGroceries = 1_000;
    livingExpenses.transport = 5_000;
    livingExpenses.healthMedical = 7_000;
    livingExpenses.insurance = 1024;
    livingExpenses.utilities = 2_000;
    livingExpenses.phoneInternet = 2_000;
    livingExpenses.other = 50_000;
    livingExpenses.totalMonthlyLivingExpenses = 2_000;

    creditApplication.income = income;
    creditApplication.livingExpenses = livingExpenses;
  }

  private List<Integer> setNextPayDayList() {
    final List<Integer> nextPayDay = new ArrayList<>();
    final LocalDate localDate = LocalDate.now();
    nextPayDay.add(localDate.getYear());
    nextPayDay.add(localDate.getMonthValue());
    nextPayDay.add(localDate.getDayOfMonth());
    return nextPayDay;
  }

  private AddressDTO prepareAddress(final GeneratedCustomerDTO generatedData) {
    final AddressDTO addressDTO = new AddressDTO();
    addressDTO.residentialStatus = RESIDENCE.name();
    addressDTO.streetNumber = generatedData.getAddress().getHome();
    addressDTO.street = generatedData.getAddress().getStreet();
    addressDTO.state = generatedData.getState().name();
    addressDTO.postCode = generatedData.getAddress().getPostcode();
    addressDTO.city = generatedData.getAddress().getCity();
    addressDTO.unitNumber = generatedData.getAddress().getHome();
    return addressDTO;
  }

  private void startCustomerRegistration(final RegistrationParams params,
      final GeneratedCustomerDTO generatedData,
      final AIOBackendRestClientProvider provider, final UserRegistrationInfoDTO registrationInfo) {
    final AURegistrationClient registrationClient = provider.provide(AURegistrationClient.class);
    final AUCreditApplication creditApplicationClient = provider.provide(AUCreditApplication.class);
    final AUScoringClient scoringClient = provider.provide(AUScoringClient.class);

    registrationClient.registerInit(registrationInfo);
    registrationInfo.otp = OTP;
    registrationClient.registerConfirm(registrationInfo);
    updateRegistrationInfo(generatedData, registrationInfo);
    checkAndSetCustomEmail(params, generatedData, registrationInfo);
    registrationInfo.productTypeOnCreation = PRODUCT_TYPE_ON_CREATION;
    registrationClient.registerComplete(registrationInfo);
    creditApplicationClient.initialiseProviso("test");

    final ScoringAUDTO scoringAUDTO = new ScoringAUDTO();
    scoringAUDTO.bankScrapingInit = BankScrapingInitStatusDTO.SUCCESS;
    scoringClient.scoringInit(scoringAUDTO);
  }

  private void updateRegistrationInfo(final GeneratedCustomerDTO generatedData,
      final UserRegistrationInfoDTO registrationInfo) {
    registrationInfo.firstName = generatedData.getFirstName();
    registrationInfo.lastName = generatedData.getLastName();
    registrationInfo.email = generatedData.getEmail();
    registrationInfo.instantMessenger = true;
    registrationInfo.dateOfBirth = new int[] {generatedData.getDateOfBirth().getYear(),
        generatedData.getDateOfBirth().getMonthValue(),
        generatedData.getDateOfBirth().getDayOfMonth()};

    final ConsentsDTO consentsDTO = new ConsentsDTO();
    consentsDTO.employedAdult = true;
    consentsDTO.identityCheck = true;
    consentsDTO.privacyPolicy = true;
    registrationInfo.consents = consentsDTO;
    registrationInfo.password = findDefaultUserPass();
  }

  private void checkAndSetCustomEmail(final RegistrationParams params,
      final GeneratedCustomerDTO generatedData,
      final UserRegistrationInfoDTO registrationInfo) {
    if (params.getCustomEmail()) {
      generatedData.setEmail(params.getCustomEmailValue());
      registrationInfo.email = generatedData.getEmail();
    } else {
      LOG.warn("Custom email is not found. Using generator email!");
    }
  }

  private void startCustomerApplicationProcess(final RegistrationParams params,
      final AIOBackendRestClientProvider provider,
      final CustomerAUWrapperDTO customerAUWrapper, final Customer customer) {

    updateCustomerBankAccountAndBankScraping(customer,
        getCompressedCustomerUuid(params.getEnvironment(), customer.id),
        params.getEnvironment());
    updateScore(params.getEnvironment().envName, params.getCountry(),
        customer.id.toString(), null, CreditBureauAU.class);
    final AUCreditApplication creditApplicationClient = provider.provide(AUCreditApplication.class);
    creditApplicationClient.updateCustomerInfoFinal(customerAUWrapper);
    waitForDefsResponse(params.getEnvironment().envName, customer.id.toString(),
        DEFS_PROCESSING_MAX_TIME);
    selectProduct(params, creditApplicationClient, customer);
    acceptLegalPage(creditApplicationClient);
  }

  private void updateCustomerBankAccountAndBankScraping(final Customer customer,
      final String compressedCustomerUUID, final Environment env) {

    auBankScrapingResponse =
        auBankScrapingResponse == null
            ? getUpdateMessage(SCORE_255.reportName, SCORE_255.baseIncomeValue)
            : auBankScrapingResponse;

    insertBankScrapingIntoMule(env, customer.country, compressedCustomerUUID,
        auBankScrapingResponse);

    AIODataUtils.executeUpdateBankScraping(env, compressedCustomerUUID,
        BankScrapingProviderTypeEnum.PROVISO.value(),
        customer.bankAccount, auBankScrapingResponse);
  }

  public void insertBankScrapingIntoMule(final Environment env, final Country country,
      final String compressedCustomerUUID,
      final String bankScrapingResponse) {

    insertIntoMuleCache(env, country, PROVISO, compressedCustomerUUID,
        replaceReferenceToProper(bankScrapingResponse, compressedCustomerUUID));
    insertIntoMuleCache(env, country, "BankScrapingReference", compressedCustomerUUID,
        DatabaseQuery.getCustomerApplicationBankScrapingReference(env.envName,
            UUIDCompressor.deCompressUUID(compressedCustomerUUID)));
  }

  private void insertIntoMuleCache(final Environment env, final Country country,
      final String serviceId, final String requestId, final String message) {
    LOG.info(
        "Inserting {} reportId, {}, requestId: {} on environment: {}",
        country, serviceId, requestId, env);
    final MuleDBService muleDBService = DBServiceProvider.muleDBService();
    muleDBService.callDB(env, Portal.INSERT_SCORE_TO_MULE_QUERY, statement -> {
      statement.setString(1, serviceId);
      statement.setString(2, requestId);
      statement.setString(3, message);
      final double updated = statement.executeUpdate();
      if (updated < 1) {
        throw new IllegalStateException(
            String.format("Insert failed! : requestId %s, message: %s",
                requestId, message));
      }
      LOG.info("Successfully inserted {} reportId: {}, requestid: {} on environment: {}",
          country, serviceId, requestId, env);
      return updated;
    });
  }

  public static String waitForDefsResponse(final String envName, final String customerId,
      final int maxWaitingTime) {

    final String startScoringState = DatabaseQuery.getScoringState(envName, customerId);
    final Long maxTimeOut = System.currentTimeMillis() + (maxWaitingTime * 1000);
    checkScoringStateReadyForProcessing(startScoringState);
    final String finalScoringState = verifyDefsResponse(envName, customerId, maxTimeOut);
    checkIfNegativeState(finalScoringState, envName, customerId);
    return finalScoringState;
  }

  private static void checkScoringStateReadyForProcessing(final String scoringState) {
    if (scoringState.equals(DECISION_ENGINE_FINANCIAL_DATA_STATUS.getState())
        || scoringState.equals(BANK_SCRAPING_DONE_STATUS.getState())
        || scoringState.equals(SCORING_PROCESS_STARTED_STATUS.getState())) {
      throw new IllegalArgumentException(String.format(
          "Scoring state is blocked on %s, check if Proviso is working properly ", scoringState));
    }
  }

  private static String verifyDefsResponse(final String envName, final String customerId,
      final Long maxTimeOut) {

    String scoringState = DatabaseQuery.getScoringState(envName, customerId);

    while (isScoringStateNotFinalState(scoringState) && System.currentTimeMillis() < maxTimeOut) {
      LOG.info(String.format("Waiting - %s seconds left...",
          (maxTimeOut - System.currentTimeMillis()) / 1000));
      scoringState = DatabaseQuery.getScoringState(envName, customerId);
      safeWait(1000L);
    }
    return scoringState;
  }

  private static boolean isScoringStateNotFinalState(final String scoringState) {
    boolean state = false;
    if (!scoringState.equals(DECISION_ENGINE_SUCCESS_STATUS.getState())
        && !scoringState.equals(DECISION_ENGINE_NEGATIVE_STATUS.getState())) {
      state = true;
    }
    return state;
  }

  private static void checkIfNegativeState(final String scoringState, final String envName,
      final String customerId) {

    if (scoringState.equals(DECISION_ENGINE_NEGATIVE_STATUS.getState())) {
      final String negativeStatusMessage =
          DatabaseQuery.getNegativeScoreMessage(envName, customerId);
      if (negativeStatusMessage.equalsIgnoreCase(VEDA_ERROR.getState())) {
        throw new IllegalArgumentException(String.format(
            "Veda error please contact veda support %s CustomerId: %s", GEN_UI_NEXT_LINE,
            customerId));
      } else if (negativeStatusMessage.equalsIgnoreCase(DB_ERROR.getState())) {
        throw new IllegalArgumentException(String.format(
            "Database error - please check AIO db %s CustomerId: %s", GEN_UI_NEXT_LINE,
            customerId));
      } else {
        throw new IllegalArgumentException(String.format(
            "Application was rejected by DEFS! %s %s Error message from DecisionEngineData table: %s CustomerId: %s",
            negativeStatusMessage, GEN_UI_NEXT_LINE, customerId, GEN_UI_NEXT_LINE));
      }
    }
    if (scoringState.equals(DECISION_ENGINE_REQUEST_SENT_STATUS.getState())) {
      throw new IllegalArgumentException(String.format(
          "Scoring state blocked on DECISION_ENGINE_REQUEST_SENT, check is DEFS working properly"
              + GEN_UI_NEXT_LINE + "CustomerId: %s",
          customerId));
    }
  }

  private void selectProduct(final RegistrationParams params, final AUCreditApplication client,
      final Customer customer) {
    final List<ProductSelectionDTO> products = params.getProductType() == ProductType.CL
        ? transformCreditLintProduct(client.getAvailableCreditLineProductsAU().products)
        : transformInstallmentProductList(client.getAvailableInstallmentProductsAU().products);

    final ProductSelectionDTO productSelection =
        chooseProductAU(products, params.getProductLevel());

    productSelection.firstDrawAmount =
        getDrawAmount(params.getDrawPercentage(), productSelection.firstDrawAmount);

    LOG.debug("Chosen {} product {} for customer id {}, Amount : {}", params.getProductLevel(),
        params.getProductType(), customer.id, productSelection.firstDrawAmount);

    client.selectProduct(productSelection);
    LOG.debug("Chosen product for customer id {} ProductID : {}", customer.id,
        productSelection.id);
  }

  private ProductSelectionDTO chooseProductAU(final List<ProductSelectionDTO> productsList,
      final ProductLevel productLevel) {
    if (productsList.isEmpty()) {
      throw new IllegalArgumentException(String
          .format("%n%nProducts list is empty! Unable to choose %s product. %n", productLevel));
    }
    final List<ProductSelectionDTO> products = new ArrayList<>(productsList);

    switch (productLevel) {
      case TOP:
        return products.get(products.size() - 1);
      case MID:
        return products.get(products.size() / 2);
      case MIN:
        return products.get(0);
      default:
        throw new IllegalArgumentException(
            String.format("%n%nUndefined productLevel: %s %n", productLevel));
    }
  }

  private int getDrawAmount(final Integer drawPercentage, final int creditAmount) {
    if (drawPercentage != null) {
      return creditAmount * drawPercentage / 100;
    } else {
      return creditAmount;
    }
  }

  private void acceptLegalPage(final AUCreditApplication client) {
    final ConsentDTO consent = new ConsentDTO();
    consent.communication = true;
    consent.generalTerms = true;
    consent.directDebit = true;

    final ConsentLegalPageDTO consentLegalPage = new ConsentLegalPageDTO();
    consentLegalPage.consent = consent;

    client.updateLegalPage(consentLegalPage);
  }

  private List<ProductSelectionDTO> transformCreditLintProduct(
      final List<ProductCreditLineAU> products) {
    return products.stream().map(productCreditLineAU -> {
      ProductSelectionDTO productSelectionDTO = new ProductSelectionDTO();
      productSelectionDTO.id = productCreditLineAU.productId;
      productSelectionDTO.firstDrawAmount = productCreditLineAU.creditAmount;
      return productSelectionDTO;
    }).collect(Collectors.toList());
  }

  private List<ProductSelectionDTO> transformInstallmentProductList(
      final List<ProductInstallmentAUDTO> productList) {
    final List<ProductSelectionDTO> productSelectionDTOList = new ArrayList<>();
    productList.forEach(productInstallmentAUDTO -> productInstallmentAUDTO.productOffers
        .forEach(productOffersAUDTO -> {
          final ProductSelectionDTO productSelectionDTO = new ProductSelectionDTO();
          productSelectionDTO.id = productOffersAUDTO.productId;
          productSelectionDTO.firstDrawAmount = productInstallmentAUDTO.loanAmount;
          productSelectionDTOList.add(productSelectionDTO);
        }));
    return productSelectionDTOList;
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

    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);
    if (isDraw) {
      checkIfBankAccountIsInUseAndCloseTasks(salesforceProvider,
          Collections.singletonList(SFTask.BANK_ACCOUNT_IN_USE), accountId, customer.id);
      tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
    }
    checkIfContractIsReady(salesforceProvider, accountId);
  }

  @Override
  public void verifyDrawCase(final Customer customer, final Environment environment) {
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
  }

  public Integer getMinAge() {
    return minAge;
  }

  public Integer getMaxAge() {
    return maxAge;
  }

  private String getCompressedCustomerUuid(final Environment environment,
      final Long customerId) {

    return UUIDCompressor.compressUUID(DatabaseQuery.getCustomerUUID(
        environment.envName, customerId.toString()));
  }

  private String replaceReferenceToProper(final String provisoMessage,
      final String customerCompressedUUID) {
    final JSONObject provisoObj = new JSONObject (provisoMessage);
    final JSONObject customer = provisoObj.getJSONObject("customer");
    customer.put("reference", customerCompressedUUID);
    return provisoObj.toString();
  }

  @Override
  public void checkVerificationImplementation() {
    // verification is implemented
  }
}
