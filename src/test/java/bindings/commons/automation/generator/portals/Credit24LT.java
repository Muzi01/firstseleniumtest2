package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.LithuanianFinancialData;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.OccupationType;
import com.ipfdigital.automation.aio.rest.dto.PersonalInformationDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.Residence;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.CustomerFlowStep;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.PermissionsDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.banks.LTBanksClient;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.LTCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.GenericCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.LTRegistrationClient;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.ipfdigital.automation.generator.utils.Utils.waitUntil;

public class Credit24LT extends Portal {

  private static final Logger LOG = LogManager.getLogger(Credit24LT.class);

  // Values used in customer registration:
  private static final String OTP = "1234";
  private static final String BANK_NAME = "swed";
  private static final boolean CONSENT = true;
  private static final boolean DATA_PROCESSING_CONSENT = true;
  private static final boolean POLITICALLY_EXPOSED = false;
  private Education education = Education.UNIVERSITY;
  private EmploymentDuration employmentDuration = EmploymentDuration.YEARS_OVER_10;
  private OccupationType occupationType = OccupationType.EMPLOYEE;
  private Integer netIncome = 500000;
  private Residence residence = Residence.OWNERAPARTMENT;
  private static final Integer MONTHLY_OBLIGATIONS = 10000;
  private MaritalStatus maritalStatus = null;
  private static final String DEFAULT_MARTIAL_STATUS = MaritalStatus.DIVORCED.name();

  private static final String BKA_QUERY =
      "SELECT status FROM CreditApplicationExternalData WHERE creditApplication_ID = ? AND dataType = 'BKA_LT'";

  private Credit24LT() {
    LOG.info("Instantiated Credit24LT portal.");
  }

  private static class SingletonHolder {
    static final Credit24LT instance = new Credit24LT();

    private SingletonHolder() {
    }
  }

  public static Credit24LT getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "LT market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {
    final Environment env = params.getEnvironment();

    verifyRegistrationParams(params);
    getScoringParams(params);
    final GeneratedCustomerDTO generatedData = getCustomerData(params, Country.LT, age);
    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.CREDIT24.name().toLowerCase(), Country.LT, restEventListener);
    setRegistrationInfo(provider, generatedData);
    Customer customer = authenticateCustomer(provider.provide(LTRegistrationClient.class),
        generatedData.getIdentifier(), env);
    notifyCustomerRegistered(customer);
    startCustomerRegistration(provider, generatedData, customer.id);
    customer.bankAccount = generatedData.getBankAccount().getNumber();
    oneCentVerification(customer, env);
    finishCustomerRegistration(provider.provide(LTCreditApplicationClient.class));
    final ProductDTO product = chooseProductAndSubmitApplication(
        provider.provide(LTCreditApplicationClient.class), generatedData, params);
    customer = fetchCustomerWithAdditionalData(customer, product, generatedData.getEmail(), env);
    verification(params, customer);
    LOG.info(Portal.REGISTRATION_COMPLETE_PATTERN, env, customer.id);
    return customer;
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.LT || params.getBrand() != Brand.CREDIT24) {
      final String msg =
          String.format("Credit24LT can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
  }

  private void setRegistrationInfo(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData) {
    final LTRegistrationClient client = provider.provide(LTRegistrationClient.class);
    final UserRegistrationInfoDTO registrationInfo = new UserRegistrationInfoDTO();
    registrationInfo.ssn = generatedData.getIdentifier();
    registrationInfo.msisdn = generatedData.getPrefixedMsisdn();
    client.registerInit(registrationInfo);
    client.registerConfirm(prepareRegistrationData(generatedData, provider.getBasicAddress()));
  }

  private Customer authenticateCustomer(final LTRegistrationClient client, final String identifier,
      final Environment env) {
    final AuthenticationResponseDTO authenticationResponse = client.authenticate();
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName, identifier);
    LOG.debug("GDPR EE Customer ID from REST: {}, from DB: {}",
        authenticationResponse.customer.id, customer.id);
    new DAOFactory().getUserRegistrationRequestDAO().verifyIdentification(env.envName,
        customer.identifier);
    return customer;
  }

  private void startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData,
      final Long customerId) {
    final LTCreditApplicationClient creditApplicationClient =
        provider.provide(LTCreditApplicationClient.class);
    creditApplicationClient.startNewApplication();

    final PermissionsDTO permissions =
        buildPermissionsDTO(CustomerFlowStep.CUSTOMER_INFORMATION, null, true);
    creditApplicationClient.updatePermissions(permissions);
    creditApplicationClient.updatePersonalInformation(preparePersonalInformation(generatedData));

    final LTBanksClient banksClient = provider.provide(LTBanksClient.class);
    banksClient.initOneCentVerification(customerId);

    final GenericCustomerClient customerClient = provider.provide(GenericCustomerClient.class);
    customerClient.updateMarketingConsent(getMarketingConsentList());
  }

  private void oneCentVerification(final Customer customer, final Environment environment) {
    environment.getSoapUtils().handleIdentificationV2(Country.LT, customer.id,
        SfParamsConstants.IDENTIFICATION_PARAM_EXPECTED_VALUE, customer.bankAccount);
  }

  private void finishCustomerRegistration(final LTCreditApplicationClient client) {
    client.fetchExternalRegistries();

    final PermissionsDTO permissions =
        buildPermissionsDTO(CustomerFlowStep.FINANCIAL_DATA, true, null);
    client.updatePermissions(permissions);

    client.storeFinancialData(prepareFinancialData());
  }

  private UserRegistrationInfoDTO prepareRegistrationData(
      final GeneratedCustomerDTO generatedData, final String host) {
    final UserRegistrationInfoDTO userRegistrationInfoDTO = new UserRegistrationInfoDTO();
    userRegistrationInfoDTO.ssn = generatedData.getIdentifier();
    userRegistrationInfoDTO.msisdn = generatedData.getPrefixedMsisdn();
    userRegistrationInfoDTO.lastName = generatedData.getLastName();
    userRegistrationInfoDTO.firstName = generatedData.getFirstName();
    userRegistrationInfoDTO.password = findDefaultUserPass();
    userRegistrationInfoDTO.otp = OTP;
    userRegistrationInfoDTO.bankName = BANK_NAME;
    userRegistrationInfoDTO.consent = CONSENT;
    userRegistrationInfoDTO.dataProcessingConsent = DATA_PROCESSING_CONSENT;
    userRegistrationInfoDTO.politicallyExposedPerson = POLITICALLY_EXPOSED;
    userRegistrationInfoDTO.email = generatedData.getEmail();
    userRegistrationInfoDTO.bankReturnUrl = host;
    return userRegistrationInfoDTO;
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.LT_IPFD_CHANNELS, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.LT_IPFD_CHANNELS, true));
    return marketingConsents;
  }


  private FinancialDataDTO prepareFinancialData() {
    final FinancialDataDTO financialDataDTO = new FinancialDataDTO();
    financialDataDTO.education = education;
    financialDataDTO.residence = residence;
    financialDataDTO.occupationType = occupationType;
    financialDataDTO.employmentDuration = employmentDuration;
    financialDataDTO.netIncome = netIncome;
    financialDataDTO.monthlyObligations = MONTHLY_OBLIGATIONS;
    financialDataDTO.maritalStatus = maritalStatus;
    financialDataDTO.receiveRefinance = true;
    return financialDataDTO;
  }

  private PersonalInformationDTO preparePersonalInformation(
      final GeneratedCustomerDTO generatedDataDTO) {
    final PersonalInformationDTO dto = new PersonalInformationDTO();
    dto.livingAddress = generatedDataDTO.getAddress().getStreet();
    dto.education = education.name();
    dto.residence = residence.name();
    return dto;
  }

  private ProductDTO chooseProductAndSubmitApplication(final LTCreditApplicationClient client,
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
    waitForBKA(customer, params.getEnvironment());
    final boolean isDraw =
        params.getDrawPercentage() > 0 || params.getProductType() == ProductType.IL;
    if (params.getVerification()) {
      verifyCustomer(customer, isDraw, params.getEnvironment());
    }
  }

  private void waitForBKA(final Customer customer, final Environment env) {

    final Long creditApplicationId =
        new DAOFactory().getCreditApplicationDAO().findCreditApplicationId(env,
            customer.id);
    LOG.info("Waiting for BKA status for CreditApplication {}...", creditApplicationId);
    waitUntil(
        () -> checkIsStatusDone(creditApplicationId, env),
        10000, 1200000);
  }

  private boolean checkIsStatusDone(final Long creditApplicationId,
      final Environment environment) {

    return DBServiceProvider.aioDBService()
        .getQueryResult(environment, BKA_QUERY, "status", creditApplicationId)
        .equals("DONE");
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {

    waitForBKA(customer, environment);
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);
    salesforceProvider.uploadAttachment(accountId, SFAttachmentType.BANK_STATEMENT);
    tryCloseSFTask(salesforceProvider, SFTask.WAIT_UPLOAD_DOCUMENTS, customer.id);
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

  @Override
  public void checkVerificationImplementation() {
    // Implemented
  }

  @Override
  public void setEducation(final String newEducation) {

    education = (newEducation.isEmpty()) ? null : Education.valueOf(newEducation);
  }

  @Override
  public String getEducation() {
    return (education == null) ? Education.MISSING.name() : education.name();
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
    return (residence == null) ? Residence.OTHER.name() : residence.name();
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
  public void setMaritalStatus(final String newMaritalStatus) {
    if (newMaritalStatus.isEmpty()) {
      LOG.info("Marital Status is empty. Use the default value: {}", DEFAULT_MARTIAL_STATUS);
      maritalStatus = MaritalStatus.valueOf(DEFAULT_MARTIAL_STATUS);
    } else {
      maritalStatus = MaritalStatus.valueOf(newMaritalStatus);
    }
  }

  @Override
  public String getMaritalStatus() {
    return maritalStatus.name();
  }


  @Override
  public Integer getCustomerTotalScore() {
    final int ageValue = LithuanianFinancialData.getAgeScoreValue(getAge());
    final int educationValue = LithuanianFinancialData.getEducationScoreValue(getEducation());
    final int residenceValue = LithuanianFinancialData.getResidenceScoreValue(getResidence());
    final int maritalValue = LithuanianFinancialData.getMaritalStatusValue(getMaritalStatus());
    return LithuanianFinancialData.FIXED_SCORE + ageValue + educationValue + residenceValue
        + maritalValue;
  }

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    final List<Map.Entry<Integer, String>> scoreMap = LithuanianFinancialData.getScoreMap();
    final Integer minScore = scoreMap.get(0).getKey();
    final Integer maxScore = scoreMap.get(scoreMap.size() - 1).getKey();
    if (minScore > expectedCustomerScore || maxScore < expectedCustomerScore) {
      final String errorMessage = String
          .format("For Customer LT, Total Score Value can be in range <%d,%d>", minScore, maxScore);
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
        setEducation(list.get(1));
        setResidence(list.get(2));
        setMaritalStatus(list.get(3));
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

  private PermissionsDTO buildPermissionsDTO(final CustomerFlowStep flowStep,
      final Boolean refinanceRequest,
      final Boolean dataProcessing) {
    final PermissionsDTO permissionsDTO = new PermissionsDTO();
    permissionsDTO.customerFlowStep = flowStep;
    permissionsDTO.refinanceRequest = refinanceRequest;
    permissionsDTO.dataProcessing = dataProcessing;
    return permissionsDTO;
  }
}
