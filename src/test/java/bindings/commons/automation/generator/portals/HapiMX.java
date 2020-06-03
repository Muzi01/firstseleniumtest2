package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountProvisionsDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.LearnedAboutUsFromType;
import com.ipfdigital.automation.aio.rest.dto.Loan;
import com.ipfdigital.automation.aio.rest.dto.LoanPurpose;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.MexicanFinancialData;
import com.ipfdigital.automation.aio.rest.dto.MexicanFinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.PaymentCardDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ReferencePersonDTO;
import com.ipfdigital.automation.aio.rest.dto.Residence;
import com.ipfdigital.automation.aio.rest.dto.TMXInitRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.UpdateCustomerDTO;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.MXCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.MXCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.MXRegistrationClient;
import com.ipfdigital.automation.aio.rest.v2.tmx.TmxClient;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.customer.Bank;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GenerateCustomerDTO;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.generator.utils.Utils;
import com.ipfdigital.automation.generator.utils.db.AddressInfoMX;
import com.ipfdigital.automation.generator.utils.scores.ScoreMX;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.ipfdigital.automation.generators.RandomUtils.randomEnum;
import static com.ipfdigital.automation.generators.RandomUtils.randomFromList;

public class HapiMX extends Portal {

  private static final Logger LOG = LogManager.getLogger(HapiMX.class);
  private static final String SCORING_PATH = "scoringMX.properties";
  private static final String DEFAULT_SCORING_MULE_BDS_DFP = "scoringBDS_DFP.mx";
  private static final String SCORE = "S";
  private static final String TMX_TOP_DOMAIN = "hapicredito.com";
  private static final Integer TMX_SELECTED_AMOUNT = 500000;
  private static final LoanPurpose LOAN_PURPOSE = LoanPurpose.OTHER;
  private static final LearnedAboutUsFromType LEARNED_FROM = LearnedAboutUsFromType.BUS_STOP_AD;
  private static final Properties scoringProperties = new Properties();
  private static final int MIN_AGE = 21;
  private static final int MAX_AGE = 65;
  // Values used in customer registration:
  private final Loan[] loans = new Loan[] {};
  private Integer netIncome = 900000;
  private Occupation occupation = Occupation.FULL_TIME;
  private MaritalStatus maritalStatus = MaritalStatus.MARRIED;
  private Education education = Education.UNIVERSITY;
  private String municipality = null;
  private String province = null;
  private final Residence residence = Residence.OTHER;
  private final List<SFAttachmentType> attachmentsToUploadList = new ArrayList<>();

  // private final MXDebitCardGenerator debitCardGenerator = new MXDebitCardGenerator();

  private HapiMX() {
    try {
      scoringProperties.load(HapiMX.class.getClassLoader().getResourceAsStream(SCORING_PATH));
      LOG.info("Loaded scoring from file: {}", SCORING_PATH);
    } catch (final IOException | NullPointerException e) {
      LOG.error("Unable to read scoring from file: {}", SCORING_PATH);
      throw new IllegalStateException(
          String.format("Unable to read scoring from file: %s", SCORING_PATH), e);
    }
    setAttachmentsToUploadList();
    LOG.info("Instantiated HapiMX portal.");
  }

  private static class SingletonHolder {
    static final HapiMX instance = new HapiMX();

    private SingletonHolder() {
    }
  }

  public static HapiMX getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "MX market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {

    final Environment env = params.getEnvironment();

    verifyRegistrationParams(params);
    getScoringParams(params);
    final GeneratedCustomerDTO generatedData = getCustomerData(params, Country.MX,
        age == null ? 50 : age);
    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.HAPI.name().toLowerCase(), Country.MX, restEventListener);
    Customer customer = startCustomerRegistration(provider, generatedData, env);
    chooseProductAndSubmitApplication(provider, generatedData, params,
        customer);
    Utils.safeWait(500); // required for aio to keep up - otherwise 500 error is returned
    customer = verifyEmail(provider.provide(MXCustomerClient.class), generatedData, env);
    verification(params, customer);
    LOG.info(Portal.REGISTRATION_COMPLETE_PATTERN, env, customer.id);
    return customer;
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.MX || params.getBrand() != Brand.HAPI) {
      final String msg =
          String.format("HapiMX can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
    if (params.getProductType() == ProductType.IL)
      LOG.warn("IL product type is not available in HapiMX. Applying for CL instead.");
  }

  private Customer startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData,
      final Environment env) {
    final MXRegistrationClient registrationClient = provider.provide(MXRegistrationClient.class);
    registrationClient.registerUser(buildUserRegistrationInfo(generatedData));
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName,
            generatedData.getIdentifier());
    notifyCustomerRegistered(customer);
    insertScore(env, chooseMXScore(SCORE), customer.identifier);
    registrationClient.authenticate();
    final MXCreditApplicationClient creditApplicationClient =
        provider.provide(MXCreditApplicationClient.class);
    creditApplicationClient.putCreditApplication();
    final TmxClient tmxClient = provider.provide(TmxClient.class);
    tmxClient.tmxInitSession(new TMXInitRequestDTO(TMX_TOP_DOMAIN, TMX_SELECTED_AMOUNT));
    final MXCustomerClient customerClient = provider.provide(MXCustomerClient.class);
    customerClient.updateMarketingConsent(getMarketingConsentList());
    putCustomerData(provider, env, generatedData);
    return customer;
  }

  private ScoreMX chooseMXScore(final String score) {
    return ScoreMX.valueOf(ScoreMX.CHALLENGER + "_" + score);
  }

  private ScoreMX chooseMXScore(final AIOBackendRestClientProvider provider,
      final MsisdnGenerator msisdnGeneratorService, final String score,
      final String ssn,
      final String msisdn) {
    final UserRegistrationInfoDTO userRegistrationInfoDTO = new UserRegistrationInfoDTO();
    userRegistrationInfoDTO.ssn = ssn;
    userRegistrationInfoDTO.msisdn = msisdn;
    userRegistrationInfoDTO.msisdn2 = msisdnGeneratorService.generate(Country.MX);

    provider.provide(MXRegistrationClient.class)
        .registerUser(userRegistrationInfoDTO);
    return chooseMXScore(score);
  }

  public void insertScore(
      final AIOBackendRestClientProvider provider, final MsisdnGenerator generator,
      final Environment env, final String score,
      final String ssn,
      final String msisdn) {

    final ScoreMX scoreMX = chooseMXScore(provider, generator, score, ssn, msisdn);
    insertScore(env, scoreMX, ssn);
  }

  public void insertScore(final Environment env, final ScoreMX score, final String ssn) {

    LOG.info("Inserting mexican score: {} for ssn: {} on environment: {}", score, ssn, env);
    insertToMule(env, score, ssn, "BDC", score.getFullBDCMessage());
    insertToMule(env, score, ssn, "BDC_MICROFINANCE", score.getMicrofinanceMessage());
    insertToMule(env, score, ssn, "BDC_CREDIT_REPORT", score.getCreditReportMessage());
    insertToMule(env, score, ssn, "BDS_DFP",
        scoringProperties.getProperty(DEFAULT_SCORING_MULE_BDS_DFP));
  }

  private void insertToMule(final Environment env, final ScoreMX score, final String requestID,
      final String serviceId,
      final String message) {

    DBServiceProvider.muleDBService()
        .callDB(env, INSERT_SCORE_TO_MULE_QUERY, statement -> {
          statement.setString(1, serviceId);
          statement.setString(2, requestID);
          statement.setString(3, message);
          final int update = statement.executeUpdate();
          if (update < 1) {
            throw new IllegalStateException(
                "Insert failed! SSN: " + requestID + ", score: " + score);
          }
          LOG.info("Successfully inserted mexican score for ssn: {} on environment: {}", requestID,
              env);

          return update;
        });
  }

  private void putCustomerData(final AIOBackendRestClientProvider provider,
      final Environment environment,
      final GeneratedCustomerDTO generatedData) {
    final AddressDTO addressDTO =
        mapToDTO(generatedData.getAddress(), generatedData.getCountry());
    updateAddress(environment, addressDTO, generatedData.getAddress().getPostcode());
    addressDTO.municipality =
        municipality != null ? getMunicipality() : generatedData.getAddress().getCity();
    addressDTO.province = province != null ? getProvince() : "EM";
    final MXCustomerClient customerClient = provider.provide(MXCustomerClient.class);
    updateCustomer(customerClient, buildFirstCustomerUpdate(generatedData));
    final MXCreditApplicationClient creditApplicationClient =
        provider.provide(MXCreditApplicationClient.class);
    final BankAccountProvisionsDTO provisionsDTO = new BankAccountProvisionsDTO();
    provisionsDTO.bankAccountProvision = "BANK_ACCOUNT_PROVIDED";
    updateCustomer(customerClient, buildSecondCustomerUpdate(addressDTO, generatedData));
    updateCustomer(customerClient, buildThirdCustomerUpdate(addressDTO, generatedData));
    creditApplicationClient.storeReferencePerson(randomReferencePerson());
    creditApplicationClient.storeFinancialData(prepareFinancialDataDTO());
  }

  private void updateAddress(final Environment environment, final AddressDTO addressDTO,
      final String postcode) {
    final String randomNeighbourhood =
        randomFromList(AddressInfoMX.neighbourhoodsByPostcode(environment, postcode));
    addressDTO.neighbourhood = randomNeighbourhood;
    addressDTO.municipality =
        AddressInfoMX.municipalityByNeighbourhood(environment, randomNeighbourhood);
  }

  private static void updateCustomer(final MXCustomerClient customerClient,
      final UpdateCustomerDTO updateCustomerDTO) {
    customerClient.updateCustomer(updateCustomerDTO);
  }

  private UpdateCustomerDTO buildFirstCustomerUpdate(final GeneratedCustomerDTO generatedData) {
    final UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO();
    updateCustomerDTO.customer = buildCustomer(generatedData);

    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedData.getEmail();
    updateCustomerDTO.email = emailUpdateDTO;

    final PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();
    updateCustomerDTO.password = passwordUpdateDTO;

    return updateCustomerDTO;
  }

  private static UpdateCustomerDTO buildSecondCustomerUpdate(final AddressDTO addressDTO,
      final GeneratedCustomerDTO generatedData) {
    final UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO();
    updateCustomerDTO.customer = buildCustomer(generatedData);
    updateCustomerDTO.address = addressDTO;
    return updateCustomerDTO;
  }

  private static UpdateCustomerDTO buildThirdCustomerUpdate(final AddressDTO addressDTO,
      final GeneratedCustomerDTO generatedData) {
    final UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO();
    updateCustomerDTO.customer = buildCustomer(generatedData);
    updateCustomerDTO.address = addressDTO;
    updateCustomerDTO.taxIdentificationNumber = generatedData.getIdentifier().substring(0, 10);
    return updateCustomerDTO;
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.MX_CONSENT, true));
    marketingConsents.add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.MX_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.MX_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_3RD_PARTY, ConsentVersion.MX_CONSENT, false));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_DATA_TRANSFER, ConsentVersion.MX_CONSENT, false));
    marketingConsents.add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.MX_CONSENT, false));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.MX_CONSENT, false));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.MX_CONSENT, false));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.MX_CONSENT, false));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.MX_CONSENT, false));

    return marketingConsents;
  }

  private MexicanFinancialDataDTO prepareFinancialDataDTO() {

    final MexicanFinancialDataDTO financialDataDTO = new MexicanFinancialDataDTO();
    financialDataDTO.company = "Automation Company";
    financialDataDTO.creditBureauQuestion1Answer = false;
    financialDataDTO.education = education;
    financialDataDTO.learnedAboutUsFrom = LEARNED_FROM;
    financialDataDTO.loanPurpose = LOAN_PURPOSE;
    financialDataDTO.loans = loans;
    financialDataDTO.maritalStatus = maritalStatus;
    financialDataDTO.netIncome = netIncome;
    financialDataDTO.occupation = occupation;
    financialDataDTO.residence = residence;
    return financialDataDTO;
  }

  private void chooseProductAndSubmitApplication(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData,
      final RegistrationParams params, final Customer customer) {
    final MXCreditApplicationClient client = provider.provide(MXCreditApplicationClient.class);
    final List<ProductDTO> productsList = client.getAvailableCreditLineProducts();
    final ProductDTO product = chooseProductByFilters(params, productsList);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);
    client.selectProduct(buildProductSelectionDTO(product, params.getDrawPercentage()));
    checkIfDebitCardRegistration(params.getDebitCard(), provider.provide(MXCustomerClient.class),
        customer);
    Utils.safeWait(5000);
    client.submitApplication(
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage()));
    customer.product = product;
  }

  private void checkIfDebitCardRegistration(final boolean isDebitCard,
      final MXCustomerClient client,
      final Customer customer) {
    if (isDebitCard) {
      final String debitCard = debitCardGenerator.generate();
      final PaymentCardDTO paymentCardDTO = new PaymentCardDTO();
      paymentCardDTO.cardNumber = debitCard;
      paymentCardDTO.bankCode = randomEnum(Bank.class).bankCode;
      client.updatePaymentCard(paymentCardDTO);
      customer.debitCardNumber = debitCard;
    } else {
      final String bankAccount = bankAccountGenerator.generate(Country.MX).getNumber();
      final BankAccountUpdateDTO bankAccountUpdateDTO = new BankAccountUpdateDTO();
      bankAccountUpdateDTO.bankAccount = bankAccount;
      client.updateBankAccount(bankAccountUpdateDTO);
      customer.bankAccount = bankAccount;
    }
  }

  private ReferencePersonDTO randomReferencePerson() {
    final GenerateCustomerDTO parametersDTO = new GenerateCustomerDTO(Country.MX, MIN_AGE, MAX_AGE);
    final GeneratedCustomerDTO randomPerson = customerGenerator.generate(parametersDTO);
    return new ReferencePersonDTO(
        randomPerson.getFirstName() + " " + randomPerson.getLastName(),
        String.valueOf(randomEnum(Relations.class)),
        randomPerson.getMsisdn() // front-end uses "(70) 2736-1431" format
    );
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
    handleIdentification(customer, Country.MX, environment);
    addAttachments(salesforceProvider, accountId);
    updateIdentificationDocumentInfo(customer, salesforceProvider);
    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);

    checkIfBankAccountIsInUseAndCloseTasks(salesforceProvider,
        Arrays.asList(SFTask.ADDRESS_IN_USE, SFTask.BANK_ACCOUNT_IN_USE), accountId,
        customer.id);
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

    education = Education.valueOf(newEducation);
  }

  @Override
  public String getEducation() {

    return education.name();
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
  public void setAge(final String newAge) {

    age = Integer.parseInt(newAge);
  }

  @Override
  public Integer getAge() {

    return age;
  }

  @Override
  public String getMunicipality() {

    return municipality;
  }

  @Override
  public void setMunicipality(final String municipality) {
    this.municipality = municipality;
  }

  @Override
  public String getProvince() {

    return province;
  }

  @Override
  public void setProvince(final String province) {
    this.province = province;
  }

  @Override
  public Integer getCustomerTotalScore() {
    final int ageValue = MexicanFinancialData.getAgeScoreValue(getAge());
    final int eduValue = MexicanFinancialData.getEducationScoreValue(getEducation());
    final int provinceValue = MexicanFinancialData.getProvinceGDPScoreValue(getProvince());
    final int municipalityValue = MexicanFinancialData
        .getMunicipalityAnnualIncomeScoreValue(getMunicipality(), getProvince());
    return MexicanFinancialData.FIXED_SCORE + ageValue + eduValue + provinceValue
        + municipalityValue;
  }

  @Override
  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    final List<Map.Entry<Integer, String>> scoreMap = MexicanFinancialData.getScoreMap();
    final Integer minScore = scoreMap.get(0).getKey();
    final Integer maxScore = scoreMap.get(scoreMap.size() - 1).getKey();
    if (minScore > expectedCustomerScore || maxScore < expectedCustomerScore) {
      final String errorMessage = String
          .format("For Customer MX, Total Score Value can be in range <%d,%d>", minScore, maxScore);
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
        setProvince(list.get(2));
        setMunicipality(list.get(3));
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

  private void updateIdentificationDocumentInfo(final Customer customer,
      final SalesforceProvider salesforceProvider) {
    salesforceProvider.updateAccountIdentificationDocumentType(customer.id);
    salesforceProvider.updateAccountIdDocumentSerialNumber(customer.id);
  }

  private void addAttachments(final SalesforceProvider salesforceProvider, final String accountId) {

    attachmentsToUploadList.forEach(
        sfAttachmentType -> salesforceProvider.uploadAttachment(accountId, sfAttachmentType));
  }

  private void setAttachmentsToUploadList() {

    attachmentsToUploadList.add(SFAttachmentType.BANK_STATEMENT);
    attachmentsToUploadList.add(SFAttachmentType.ID_CARD_A);
    attachmentsToUploadList.add(SFAttachmentType.ID_CARD_B);
    attachmentsToUploadList.add(SFAttachmentType.SELFIE);
  }
}
