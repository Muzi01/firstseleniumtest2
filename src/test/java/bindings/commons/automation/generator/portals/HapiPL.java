package bindings.commons.automation.generator.portals;

import com.google.common.base.Strings;
import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountDuration;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.BankDetailsDTO;
import com.ipfdigital.automation.aio.rest.dto.CitizenshipUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.CountryOfBirthUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.LoanPurpose;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.MedicareDTO;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.PolishFinancialData;
import com.ipfdigital.automation.aio.rest.dto.PolishFinancialData.DeltaVista;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.TMXInitRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.AdditionalConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentType;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.citizenship.CitizenshipClient;
import com.ipfdigital.automation.aio.rest.v2.countryofbirth.CountryOfBirthClient;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.PLCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.PLCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.PLRegistrationClient;
import com.ipfdigital.automation.aio.rest.v2.tmx.TmxClient;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GenerateCustomerDTO;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.generator.utils.SOAPUtils;
import com.ipfdigital.automation.generator.utils.scores.CreditBureausPL;
import com.ipfdigital.automation.generator.utils.scores.ScorePL;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import com.ipfdigital.database.connection.DBService.StatementHandler;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ipfdigital.automation.generator.utils.scores.CreditBureausForCompaniesPL.DELTA_VISTA_COMPANY_BASIC;


public class HapiPL extends Portal {

  private static final Logger LOG = LogManager.getLogger(HapiPL.class);
  private static final String BROKER_CHANNEL_NAME = "Broker";

  // Values used in customer registration:
  private MaritalStatus maritalStatus = MaritalStatus.MARRIED;
  private static final String TMX_TOP_DOMAIN = "hapicredito.com";
  private static final Integer TMX_SELECTED_AMOUNT = 450000;
  private static final Boolean POWER_OF_ATTORNEY = true;
  private static final Boolean TERMS_VIA_WEBSITE = true;
  private static final String COMPANY = "ASSECO";
  private Integer netIncome = 850000;
  private Integer maturityPeriods = null;
  private Integer principal = null;
  private BankAccountDuration bankAccountDuration = BankAccountDuration.YEARS_OVER_3;
  private EmploymentDuration employmentDuration = EmploymentDuration.YEARS_OVER_3;
  private ScorePL scorePL = ScorePL.A;
  private DeltaVista deltaVista = DeltaVista.DV7;

  private HapiPL() {
    LOG.info("Instantiated HapiPL portal.");
    Portal.updateMessages = ScorePL.getUpdateMessage();
  }

  private static class SingletonHolder {
    static final HapiPL instance = new HapiPL();

    private SingletonHolder() {
    }
  }

  public static HapiPL getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    return registerCustomerUsingGeneratedData(params, provider,
        getCustomerData(params, Country.PL, age == null ? 50 : age));
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {

    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.HAPI.name().toLowerCase(), Country.PL, restEventListener);
    return params.getSkipInsertToMule() ? registerCustomerSkipInsertToMule(provider, params)
        : registerCustomerProcess(provider, params);
  }

  private Customer registerCustomerUsingGeneratedData(final RegistrationParams params,
      final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO customerDTO) {
    verifyRegistrationParams(params);
    getScoringParams(params);
    insertScore(params.getEnvironment(), scorePL, customerDTO.getIdentifier());
    startCustomerRegistration(provider, customerDTO);
    final Customer customer = new DAOFactory().getCustomerDAO()
        .getCustomerBySsn(params.getEnvironment().envName,
            customerDTO.getIdentifier());
    notifyCustomerRegistered(customer);
    return customer;

  }

  private Customer registerCustomerProcess(final AIOBackendRestClientProvider provider,
      final RegistrationParams params) {

    final GeneratedCustomerDTO generatedData =
        getCustomerData(params, Country.PL, age == null ? 50 : age);
    registerCustomerUsingGeneratedData(params, provider, generatedData);
    updateCustomerData(provider, generatedData);
    final ProductDTO product =
        chooseProductAndSubmitApplication(provider, generatedData, params);
    final Customer customer = updateCustomer(generatedData, product, params.getEnvironment());
    verification(params, customer);
    LOG.info("Customer registration on {} complete! Customer ID: {}", params.getEnvironment(),
        customer.id);
    return customer;
  }

  private Customer registerCustomerSkipInsertToMule(final AIOBackendRestClientProvider provider,
      final RegistrationParams params) {
    verifyRegistrationParams(params);
    final GeneratedCustomerDTO generatedCustomerDTO = getCustomerData(params, Country.PL,
        age == null ? 50 : age);
    startCustomerRegistration(provider, generatedCustomerDTO);
    updateCustomerData(provider, generatedCustomerDTO);
    return updateCustomer(generatedCustomerDTO, null, params.getEnvironment());
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.PL || params.getBrand() != Brand.HAPI) {
      final String msg =
          String.format("HapiPL can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
    if (params.getProductType() == ProductType.CL) {
      LOG.warn("CL product type is not available in HapiPL. Applying for IL instead.");
    }
  }

  public void insertScore(final Environment env, final ScorePL score, final String ssn) {

    LOG.info("Inserting polish score: {} for ssn: {} on environment: {}", score, ssn, env);
    DBServiceProvider.muleDBService()
        .callDB(env, Portal.INSERT_SCORE_TO_MULE_QUERY, (StatementHandler<Void>) statement -> {
          for (final CreditBureausPL cbPL : CreditBureausPL.values()) {
            statement.setString(1, cbPL.getName());
            statement.setString(2, ssn);
            statement.setString(3, score.getMessage(cbPL));
            checkUpdateSuccess(statement, Country.PL, ssn, score.name(), cbPL.getName(),
                env.envName);
          }
          LOG.info("Successfully inserted polish score for ssn: {} on environment: {}", ssn, env);
          return null;
        });
  }

  public void insertSsnScoreWithNipAndRegon(final Environment env, final ScorePL score,
      final String ssn, final String nip,
      final String regon) {

    LOG.info("Inserting polish NIP score: {} for ssn: {} on environment: {}", score, nip, env);
    insertScore(env, score, ssn);

    DBServiceProvider.muleDBService()
        .callDB(env, Portal.INSERT_SCORE_TO_MULE_QUERY, (StatementHandler<Void>) statement -> {
          statement.setString(1, DELTA_VISTA_COMPANY_BASIC.getName());
          statement.setString(2, ssn);
          statement.setString(3,
              score.getMessageWithReplacedNipAndRegon(DELTA_VISTA_COMPANY_BASIC, nip, regon));
          checkUpdateSuccess(statement, Country.PL, nip, score.name(),
              DELTA_VISTA_COMPANY_BASIC.getName(), env.envName);
          return null;
        });
    LOG.info(
        "Successfully inserted polish SSN with NIP and REGON score for ssn: {} on environment: {}",
        nip, env);
  }

  private void startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData) {
    final PLRegistrationClient registrationClient = provider.provide(PLRegistrationClient.class);
    registrationClient.registerInit(buildUserRegistrationInfo(generatedData));
    final PLCustomerClient customerClient = provider.provide(PLCustomerClient.class);

    final PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();
    customerClient.updatePassword(passwordUpdateDTO);
    registrationClient.authenticate();
    final PLCreditApplicationClient creditApplicationClient =
        provider.provide(PLCreditApplicationClient.class);
    creditApplicationClient.startNewApplication();
    final TmxClient tmxClient = provider.provide(TmxClient.class);
    tmxClient.tmxInitSession(new TMXInitRequestDTO(TMX_TOP_DOMAIN, TMX_SELECTED_AMOUNT));
  }

  private void updateCustomerData(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData) {
    final PLCustomerClient customerClient = provider.provide(PLCustomerClient.class);
    final CommunicationSettingsDTO communicationSettingsDTO = new CommunicationSettingsDTO();
    communicationSettingsDTO.customerFlowStep = "REGISTRATION"; // FIXME Move these to enum
    customerClient.updateCommunicationSettings(communicationSettingsDTO);
    customerClient.updateMarketingConsent(getMarketingConsentList());
    customerClient.updateAdditionalConsent(getAdditionalConsentList());
    customerClient.updateAddress(
        mapToDTO(generatedData.getAddress(), generatedData.getCountry()));

    updateCitizenship(provider);
    updateCountryOfBirth(provider);

    final PLCreditApplicationClient creditApplicationClient =
        provider.provide(PLCreditApplicationClient.class);

    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.maritalStatus = maritalStatus;
    financialDataDTO.personalIDNumber = generatedData.getPersonalDocumentNumber();
    creditApplicationClient.storeFinancialDataStep2(financialDataDTO);

    final PLRegistrationClient registrationClient = provider.provide(PLRegistrationClient.class);

    final BankDetailsDTO bankDetailsDTO = new BankDetailsDTO();
    bankDetailsDTO.bankIban = generatedData.getBankAccount().getNumber();
    registrationClient
        .registerRequestBank(bankDetailsDTO);

    updateBankAccount(generatedData, customerClient);
    updateCommunicationSettings(customerClient);
    creditApplicationClient.storeFinancialData(prepareFinancialData());
  }

  private void updateCitizenship(final AIOBackendRestClientProvider provider) {
    final CitizenshipClient citizenshipClient = provider.provide(CitizenshipClient.class);
    final CitizenshipUpdateDTO citizenshipUpdateDTO = new CitizenshipUpdateDTO();
    citizenshipUpdateDTO.firstCitizenship = "POL";
    citizenshipClient.updateCitizenships(citizenshipUpdateDTO);
  }

  private void updateCountryOfBirth(final AIOBackendRestClientProvider provider) {
    final CountryOfBirthClient countryOfBirthClient = provider.provide(CountryOfBirthClient.class);
    final CountryOfBirthUpdateDTO countryOfBirthUpdateDTO = new CountryOfBirthUpdateDTO();
    countryOfBirthUpdateDTO.countryOfBirth = "POL";
    countryOfBirthClient.storeCountryOfBirth(countryOfBirthUpdateDTO);
  }

  private void updateBankAccount(final GeneratedCustomerDTO generatedData,
      final PLCustomerClient customerClient) {
    final BankAccountUpdateDTO bankAccountUpdateDTO = new BankAccountUpdateDTO();
    bankAccountUpdateDTO.bankAccount = generatedData.getBankAccount().getNumber();
    customerClient.updateBankAccount(bankAccountUpdateDTO);
  }

  private void updateCommunicationSettings(final PLCustomerClient customerClient) {
    final CommunicationSettingsDTO communicationSettingsUpdate = new CommunicationSettingsDTO();
    communicationSettingsUpdate.customerFlowStep = "FINANCIAL_DATA";
    communicationSettingsUpdate.powerOfAttorney = POWER_OF_ATTORNEY;
    communicationSettingsUpdate.termsOfServiceViaWebsite = TERMS_VIA_WEBSITE;
    customerClient.updateCommunicationSettings(communicationSettingsUpdate);
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents.add(
        buildConsent(ConsentName.MARKETING_DATA_TRANSFER, ConsentVersion.PL_DATA_TRANSFER, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.PL_EMAIL_SMS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.PL_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.PL_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.PL_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.PL_PHONE_ROBO, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.PL_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.PL_PHONE_ROBO, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.PL_EMAIL_SMS, true));

    return marketingConsents;
  }

  private List<AdditionalConsentDTO> getAdditionalConsentList() {
    final List<AdditionalConsentDTO> additionalConsents = new ArrayList<>();
    additionalConsents.add(buildConsent(ConsentType.CREDIT_RISK_DELTA_VISTA_PROCESSING,
        ConsentName.DELTA_VISTA_PROCESSING, true));
    additionalConsents.add(buildConsent(ConsentType.CREDIT_RISK_DELTA_VISTA_VERIFICATION,
        ConsentName.DELTA_VISTA_VERIFICATION, true));
    return additionalConsents;
  }


  private FinancialDataDTO prepareFinancialData() {
    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.bankAccountDuration = bankAccountDuration;
    financialDataDTO.company = COMPANY;
    financialDataDTO.employmentDuration = employmentDuration;
    financialDataDTO.loanPurpose = LoanPurpose.TRAVELING_VACATION;
    financialDataDTO.netIncome = netIncome;
    financialDataDTO.occupation = Occupation.FULL_TIME;
    return financialDataDTO;
  }

  private ProductDTO chooseProductAndSubmitApplication(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params) {
    final PLCreditApplicationClient creditApplicationClient =
        provider.provide(PLCreditApplicationClient.class);
    final List<ProductDTO> productsList = creditApplicationClient.getAvailableInstallmentProducts();
    return getProduct(creditApplicationClient, generatedData, params, productsList);
  }

  private ProductDTO getProduct(final PLCreditApplicationClient client,
      final GeneratedCustomerDTO generatedData,
      final RegistrationParams params, final List<ProductDTO> productsList) {
    final ProductDTO product = chooseProductByFilters(params, productsList);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);
    final ProductSelectionDTO productSelection =
        buildProductSelectionDTO(product, params.getDrawPercentage());
    client.selectProduct(productSelection);

    final MedicareDTO medicareDTO = new MedicareDTO();
    medicareDTO.enabled = false;
    client.putMedicare(medicareDTO);

    final ApplicationSubmitDTO applicationSubmitDTO =
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage());
    client.submitApplication(applicationSubmitDTO);
    return product;
  }

  private Customer updateCustomer(final GeneratedCustomerDTO generatedData,
      final ProductDTO product, final Environment env) {
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName,
            generatedData.getIdentifier());
    customer.password = findDefaultUserPass();
    customer.product = product;
    customer.email = generatedData.getEmail();
    return customer;
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    final boolean isDraw = true; // as PL got only IL
    if (params.getVerification()) {
      verifyCustomer(customer, isDraw, params.getEnvironment());
    }
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {
    if (hasBrokerApplication(customer, environment)) {
      verifyBrokerCustomer(customer, environment);
    } else {
      verifyOnlineCustomer(customer, isDraw, environment);
    }
  }

  private void verifyOnlineCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {

    final String bankAccount;
    if (customer.bankAccount == null) {
      final GenerateCustomerDTO dto = new GenerateCustomerDTO(Country.PL,
          GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
          GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE);
      bankAccount = customerGenerator.generate(dto).getBankAccount().getNumber();
    } else {
      bankAccount = customer.bankAccount;
    }
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final SOAPUtils soapUtils =
        environment.getSoapUtils();
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    salesforceProvider.verifyPhone(customer.id);
    soapUtils.verifyBankAccount(Country.PL, String.valueOf(customer.id), bankAccount);
    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);
    soapUtils.handleIdentificationV2(Country.PL, customer.id,
        SfParamsConstants.IDENTIFICATION_PARAM_EXPECTED_VALUE, bankAccount);
    salesforceProvider.identifyAccountWithBankAccount(accountId);
    final String checkIdentificationTaskId =
        salesforceProvider.getTaskId(customer.id, SFTask.CHECK_IDENTIFICATION);
    if (Strings.isNullOrEmpty(checkIdentificationTaskId)) {
      throw new SalesForceExecutionException(
          buildTaskIdNotFoundMessage(SFTask.CHECK_IDENTIFICATION, customer.id));
    }
    if (!salesforceProvider.isTaskClosed(checkIdentificationTaskId, SFTask.CHECK_IDENTIFICATION)) {
      throw new SalesForceExecutionException(
          buildTaskNotClosedMessage(SFTask.CHECK_IDENTIFICATION, customer.id));
    }
    if (salesforceProvider.isIncreasedRisk(accountId)) {
      tryCloseSFTask(salesforceProvider, SFTask.AML_RISK, customer.id);
    }
    if (isDraw) {
      tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
      checkIfContractIsReady(salesforceProvider, accountId);
    }
  }

  private boolean hasBrokerApplication(final Customer customer, final Environment environment) {

    return findCreditApplicationChannel(customer, environment)
        .filter(BROKER_CHANNEL_NAME::equals)
        .isPresent();
  }

  private Optional<String> findCreditApplicationChannel(final Customer customer,
      final Environment environment) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, FIND_CREDIT_APPLICATION_CHANNEL_QUERY, statement -> {
          statement.setLong(1, customer.id);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return Optional.ofNullable(resultSet.getString(1));
            }
            return Optional.empty();
          }
        });
  }

  private void verifyBrokerCustomer(final Customer customer, final Environment environment) {

    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    tryCloseSFTask(salesforceProvider, SFTask.DOCUMENTS_VERIFICATION, customer.id);

    environment.getSoapUtils()
        .handleIdentificationV2(Country.PL, customer.id, "IDENTIFIED", customer.bankAccount);

    salesforceProvider.verifyPhone(customer.id);
    tryCloseSFTask(salesforceProvider, SFTask.PHONE_VERIFICATION, customer.id);

    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    if (salesforceProvider.isIncreasedRisk(accountId)) {
      tryCloseSFTask(salesforceProvider, SFTask.AML_RISK, customer.id);
    }

    tryCloseSFTask(salesforceProvider, SFTask.PAY_MONEY, customer.id);
    checkIfContractIsReady(salesforceProvider, accountId);
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
  public void setMaritalStatus(final String newMaritalStatus) {

    maritalStatus = MaritalStatus.valueOf(newMaritalStatus);
  }

  @Override
  public String getMaritalStatus() {

    return maritalStatus.name();
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
  public void setMaturityPeriods(final String newMaturityPeriods) {

    maturityPeriods = Integer.parseInt(newMaturityPeriods);
  }

  @Override
  public Integer getMaturityPeriods() {

    return maturityPeriods;
  }

  @Override
  public void setPrincipal(final String newPrincipal) {

    principal = Integer.parseInt(newPrincipal);
  }

  @Override
  public Integer getPrincipal() {

    return principal;
  }

  @Override
  public void setBankAccountDuration(final String newBankAccountDuration) {

    bankAccountDuration = BankAccountDuration.valueOf(newBankAccountDuration);
  }

  @Override
  public String getBankAccountDuration() {

    return bankAccountDuration.name();
  }

  @Override
  public void setEmploymentDuration(final String newBankAccountDuration) {

    employmentDuration = EmploymentDuration.valueOf(newBankAccountDuration);
  }

  @Override
  public String getEmploymentDuration() {

    return employmentDuration.name();
  }

  @Override
  public void setDeltaVista(final String deltaVistaValue) {
    final String deltaVistaName =
        DeltaVista.getNameForGivenValue(Integer.parseInt(deltaVistaValue));
    deltaVista = DeltaVista.valueOf(deltaVistaName);
  }

  @Override
  public String getDeltaVista() {

    return deltaVista.name();
  }

  @Override
  public Integer getCustomerTotalScore(final boolean behavioralScore) {
    final int ageValue = PolishFinancialData.getAgeScoreValue(getAge(), behavioralScore);
    if (behavioralScore) {
      return ageValue + PolishFinancialData.FIXED_BSC_SCORE + scorePL.scoreValue
          + deltaVista.getScoreValue();
    } else {
      return ageValue + PolishFinancialData.FIXED_ASC_SCORE
          + PolishFinancialData.getMaritalStatusValue(getMaritalStatus())
          + PolishFinancialData.getBankAccountDurationValue(getBankAccountDuration())
          + PolishFinancialData.getEmploymentDurationValue(getEmploymentDuration());
    }
  }

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore,
      final boolean behavioralScore) {
    final ScorePL chosenTier = ScorePL.getTierByScore(expectedCustomerScore);
    final List<Map.Entry<Integer, String>> scoreMap =
        setupScoreMap(behavioralScore, expectedCustomerScore);
    final Integer minScore = chosenTier.minimalScoreValue;
    final Integer maxScore = chosenTier.maximumScoreValue;
    if (minScore > expectedCustomerScore || maxScore < expectedCustomerScore) {
      final String errorMessage =
          String.format("For Customer PL, %s Total Score Value can be in range <%d,%d>",
              behavioralScore ? "BSC" : "ASC", minScore, maxScore);
      throw new IllegalArgumentException(errorMessage);
    }
    final List<String> values = findParameters(expectedCustomerScore, scoreMap);
    setParameters(values, behavioralScore);
  }

  private List<Map.Entry<Integer, String>> setupScoreMap(final boolean behavioralScore,
      final int expectedCustomerScore) {
    final List<Map.Entry<Integer, String>> scoreMap;
    final ScorePL chosenTier = ScorePL.getTierByScore(expectedCustomerScore);
    if (behavioralScore) {
      final Map<String, Integer> scorePLmap = new HashMap<>();

      scorePLmap.put(chosenTier.name(), chosenTier.scoreValue);
      scoreMap = PolishFinancialData.getBSCScoreMap(scorePLmap);
    } else {
      scoreMap = PolishFinancialData.getASCScoreMap();
    }
    return scoreMap.stream()
        .filter(maxScoreValue -> maxScoreValue.getKey() <= chosenTier.maximumScoreValue)
        .filter(minScoreValue -> minScoreValue.getKey() >= chosenTier.minimalScoreValue)
        .collect(Collectors.toList());
  }


  private List<String> findParameters(final Integer expectedScore,
      final List<Map.Entry<Integer, String>> map) {
    List<String> valuesToSet = new ArrayList<>();
    for (final Map.Entry<Integer, String> item : map) {
      if (item.getKey() >= expectedScore) {
        valuesToSet = Arrays.asList(item.getValue().split(","));
        break;
      }
    }
    if (valuesToSet.isEmpty()) {
      throw new IllegalStateException("No parameters found for score " + expectedScore);
    }
    return valuesToSet;
  }

  private void setParameters(final List<String> values, final boolean behavioralScore) {
    setAge(values.get(0));
    if (behavioralScore) {
      scorePL = ScorePL.valueOf(values.get(1));
      deltaVista = DeltaVista.valueOf(values.get(2));
    } else {
      setMaritalStatus(values.get(1));
      setBankAccountDuration(values.get(2));
      setEmploymentDuration(values.get(3));
    }
  }

  private void getScoringParams(final RegistrationParams params) {
    LOG.info("Scoring param: {}", params.getScoring());
    if (params.getScoring() != null) {
      setCustomerWithScore(params.getScoring(), true);
    }
  }
}
