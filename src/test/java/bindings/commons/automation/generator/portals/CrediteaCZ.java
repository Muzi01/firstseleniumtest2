package bindings.commons.automation.generator.portals;

import com.google.common.collect.Lists;
import com.ipfdigital.automation.aio.rest.dto.CzechFinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.CzechGeneralCustomerDataDTO;
import com.ipfdigital.automation.aio.rest.dto.CzechStartCreditApplicationDTO;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.TMXInitRequestDTO;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.CZCreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.CZCustomerClient;
import com.ipfdigital.automation.aio.rest.v2.registration.CZRegistrationClient;
import com.ipfdigital.automation.aio.rest.v2.tmx.TmxClient;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.ExternalVerificationReportService;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceApplicationDataProvider;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrediteaCZ extends Portal {

  private static final Logger LOG = LogManager.getLogger(CrediteaCZ.class);
  private static final String TMX_TOP_DOMAIN = "creditea.cz";
  private static final Integer TMX_SELECTED_AMOUNT = 500000;
  private static final String CHANNEL = "loan_app";
  private static final String WRONG_COUNTRY_OR_BRAND_ERROR =
      "CrediteaCZ can't register customers for Country: %s, Brand: %s";
  private static final String WRONG_PRODUCT_TYPE_ERROR =
      "IL product type is not available in CrediteaCZ. Applying for CL instead.";
  private static final String OTP = "1234";
  private static final int MONTHLY_PERSONAL_EXPENSES = 1000;
  private static final int OTHER_MONTHLY_OBLIGATIONS = 1000;
  private static final String NUMBER_OF_DEPENDENTS = "0";
  private Integer netIncome = 900000;

  private CrediteaCZ() {
    LOG.info("Instantiated CrediteaCZ portal.");
  }

  private static class SingletonHolder {
    static final CrediteaCZ instance = new CrediteaCZ();
  }

  public static CrediteaCZ getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "CZ market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {
    verifyRegistrationParams(params);
    setScoringParams(params);
    final GeneratedCustomerDTO generatedData = getCustomerData(params, Country.CZ, age);
    final AIOBackendRestClientProvider provider = getProvider(params.getEnvironment());
    final Customer customer =
        startCustomerRegistration(provider, generatedData, params.getEnvironment());
    chooseProductAndSubmitApplication(provider, generatedData, params, customer);
    CZCustomerClient customerClient = provider.provide(CZCustomerClient.class);
    verifyEmail(customerClient, generatedData, params.getEnvironment());
    if (params.getVerification()) {
      updateBankAccount(params, generatedData, customer);
      verification(params, customer);
    }
    LOG.info(Portal.REGISTRATION_COMPLETE_PATTERN, params.getEnvironment(), customer.id);
    return customer;
  }

  private void updateBankAccount(RegistrationParams params, GeneratedCustomerDTO generatedData,
      Customer customer) {
    buildSalesforceProvider(params.getEnvironment()).updateBankAccount(customer.id,
        generatedData.getBankAccount().getNumber());
    customer.bankAccount = generatedData.getBankAccount().getNumber();
  }


  private AIOBackendRestClientProvider getProvider(Environment env) {
    return new AIOBackendRestClientProvider(env.envName, Brand.CREDITEA.name().toLowerCase(),
        Country.CZ, restEventListener);
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.CZ || params.getBrand() != Brand.CREDITEA) {
      final String msg =
          String.format(WRONG_COUNTRY_OR_BRAND_ERROR, params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }
    if (params.getProductType() == ProductType.IL) {
      throw new IllegalStateException(WRONG_PRODUCT_TYPE_ERROR);
    }
  }

  private Customer startCustomerRegistration(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData, final Environment env) {

    final CZRegistrationClient registrationClient = provider.provide(CZRegistrationClient.class);
    registrationClient.registerUser(buildUserRegistrationInfo(generatedData));
    registrationClient.authenticate();

    final CZCreditApplicationClient creditApplicationClient =
        provider.provide(CZCreditApplicationClient.class);
    creditApplicationClient.putCreditApplication(new CzechStartCreditApplicationDTO(CHANNEL));

    final TmxClient tmxClient = provider.provide(TmxClient.class);
    tmxClient.tmxInitSession(new TMXInitRequestDTO(TMX_TOP_DOMAIN, TMX_SELECTED_AMOUNT));

    final CZCustomerClient customerClient = provider.provide(CZCustomerClient.class);
    customerClient.updateMarketingConsent(getMarketingConsentList());

    customerClient.updateGeneralCustomerData(getCzechGeneralCustomerData(generatedData));

    customerClient.updateCustomerAddress(mapToDTO(generatedData.getAddress(),
        generatedData.getCountry()));

    creditApplicationClient.storeFinancialData(prepareFinancialDataDTO());

    final Customer customer = new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName,
        generatedData.getIdentifier());

    setCustomerPassword(customerClient, customer);
    notifyCustomerRegistered(customer);
    return customer;
  }

  private void setCustomerPassword(CZCustomerClient customerClient, Customer customer) {
    PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();
    customerClient.updatePassword(passwordUpdateDTO);

    customer.password = passwordUpdateDTO.password;
  }

  private CzechGeneralCustomerDataDTO getCzechGeneralCustomerData(
      GeneratedCustomerDTO generatedData) {
    CzechGeneralCustomerDataDTO generalCustomerData = new CzechGeneralCustomerDataDTO();
    generalCustomerData.citizenship = generatedData.getCountry();
    generalCustomerData.countryOfBirth = generatedData.getCountry();
    generalCustomerData.cityOfBirth = generatedData.getAddress().getCity();
    generalCustomerData.documentNumber = generatedData.getPersonalDocumentNumber();
    return generalCustomerData;
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
    final List<MarketingConsentDTO> marketingConsents = new ArrayList<>();
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_3RD_PARTY, ConsentVersion.CZ_CONSENT, true));
    marketingConsents.add(buildConsent(ConsentName.MC_BLACKLIST, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PUSH, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_DATA_TRANSFER, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IPFD, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_EMAIL, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PAPER_MAIL, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_SMS, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_FROM_LEADS, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_ROBO, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_PHONE, ConsentVersion.CZ_CONSENT, true));
    marketingConsents
        .add(buildConsent(ConsentName.MARKETING_IM, ConsentVersion.CZ_CONSENT, true));
    return marketingConsents;
  }

  private CzechFinancialDataDTO prepareFinancialDataDTO() {

    final CzechFinancialDataDTO financialDataDTO = new CzechFinancialDataDTO();
    financialDataDTO.monthlyPersonalExpenses = MONTHLY_PERSONAL_EXPENSES;
    financialDataDTO.channel = CHANNEL;
    financialDataDTO.netIncome = getSalary();
    financialDataDTO.otherMonthlyObligations = OTHER_MONTHLY_OBLIGATIONS;
    financialDataDTO.numberOfDependents = NUMBER_OF_DEPENDENTS;

    return financialDataDTO;
  }

  private void chooseProductAndSubmitApplication(final AIOBackendRestClientProvider provider,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params,
      final Customer customer) {
    final CZCreditApplicationClient client = provider.provide(CZCreditApplicationClient.class);
    final List<ProductDTO> productsList = client.getAvailableCreditLineProducts();

    final ProductDTO product = chooseProductByFilters(params, productsList);
    LOG.debug("Chosen product for customer {}: {}", generatedData.getIdentifier(), product);
    checkProductReplication(params.getEnvironment(), generatedData, product);
    client.selectProduct(buildProductSelectionDTO(product, params.getDrawPercentage()));

    final CZRegistrationClient registrationClient = provider.provide(CZRegistrationClient.class);
    registrationClient.sendOtp(true);
    registrationClient.verifyOtp(getUserRegistrationInfo());

    client.submitApplication(
        buildApplicationSubmitDTO(product.principal, params.getDrawPercentage()));
    customer.product = product;
  }

  private UserRegistrationInfoDTO getUserRegistrationInfo() {
    UserRegistrationInfoDTO userRegistrationInfo = new UserRegistrationInfoDTO();
    userRegistrationInfo.otp = OTP;
    return userRegistrationInfo;
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    final boolean isDraw = params.getDrawPercentage() > 0;
    verifyCustomer(customer, isDraw, params.getEnvironment());
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {
    final SalesforceProvider salesforceProvider = buildSalesforceProvider(environment);
    final ExternalVerificationReportService externalVerificationReportService =
        new ExternalVerificationReportService(salesforceProvider);
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);

    environment.getSoapUtils().verifyBankAccount(Country.CZ, String.valueOf(customer.id),
        customer.bankAccount);
    externalVerificationReportService.addPennyTransfer(customer, accountId);

    handleIdentification(customer, Country.CZ, environment);

    addAttachments(salesforceProvider, accountId);

    updateIdentificationDocumentInfo(customer, salesforceProvider);

    tryCloseSFTask(salesforceProvider, SFTask.CHECK_NEW_CUSTOMER, customer.id);

    checkIfBankAccountIsInUseAndCloseTasks(salesforceProvider,
        Arrays.asList(SFTask.ADDRESS_IN_USE, SFTask.BANK_ACCOUNT_IN_USE), accountId, customer.id);
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
  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    switch (expectedCustomerScore) {
      case 300:
        netIncome = 90000;
        age = 50;
        break;
      case 200:
        netIncome = 9000;
        age = 25;
        break;
      default:
        netIncome = 40000;
        age = 30;
    }
  }

  private void setScoringParams(final RegistrationParams params) {
    LOG.info("Scoring param: {}", params.getScoring());
    if (params.getScoring() != null) {
      setCustomerWithScore(params.getScoring());
    }
  }

  private void updateIdentificationDocumentInfo(final Customer customer,
      final SalesforceProvider salesforceProvider) {
    salesforceProvider.updateAccountIdentificationDocumentType(customer.id);
    salesforceProvider.updateAccountIdDocumentSerialNumber(customer.id);
    salesforceProvider.identifyAccountWithBankAccount(customer.id);
    try {
      String appId = new SalesforceApplicationDataProvider(salesforceProvider)
          .getApplicationID(String.valueOf(customer.id));
      salesforceProvider.manualDTICalculationDone(appId);
    } catch (SalesForceExecutionException e) {
      LOG.info("manualDTICalculationDone not set. Check SF permissions");
    }
  }

  private void addAttachments(final SalesforceProvider salesforceProvider, final String accountId) {
    List<SFAttachmentType> attachmentsToUploadList = getAttachmentsToUploadList();

    attachmentsToUploadList.forEach(
        sfAttachmentType -> salesforceProvider.uploadAttachment(accountId, sfAttachmentType));
  }

  private List<SFAttachmentType> getAttachmentsToUploadList() {
    List<SFAttachmentType> attachmentsToUploadList = Lists.newArrayList();
    attachmentsToUploadList.add(SFAttachmentType.INCOME_PROOF);
    attachmentsToUploadList.add(SFAttachmentType.ID_CARD_A);
    attachmentsToUploadList.add(SFAttachmentType.ID_CARD_B);
    attachmentsToUploadList.add(SFAttachmentType.SELFIE);

    return attachmentsToUploadList;
  }
}
