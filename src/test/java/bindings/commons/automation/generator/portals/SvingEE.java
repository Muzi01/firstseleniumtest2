package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.AuthenticationResponseDTO;
import com.ipfdigital.automation.aio.rest.dto.BankAccountUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.Education;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.EmploymentDuration;
import com.ipfdigital.automation.aio.rest.dto.EstonianFinancialData.WeekdayHourOfApplication;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.Loan;
import com.ipfdigital.automation.aio.rest.dto.MaritalStatus;
import com.ipfdigital.automation.aio.rest.dto.MsisdnUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.MsisdnVerificationDTO;
import com.ipfdigital.automation.aio.rest.dto.Occupation;
import com.ipfdigital.automation.aio.rest.dto.OccupationType;
import com.ipfdigital.automation.aio.rest.dto.PasswordUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
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
import com.ipfdigital.automation.generator.exceptions.CustomerRegistrationException;
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


public class SvingEE extends Portal {

  private static final String PENDING = "PENDING";
  private static final Logger LOG = LogManager.getLogger(SvingEE.class);

  // Values used in customer registration:
  private static final String AUTH_POST_OFFICE_ID = "47";
  private static final CommunicationSettingsDTO.DeliveryMethod CONTRACT_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final CommunicationSettingsDTO.DeliveryMethod INVOICE_DELIVERY_METHOD =
      CommunicationSettingsDTO.DeliveryMethod.EMAIL;
  private static final Boolean SMS_REMINDER = true;
  private static final String OTP = "1234";
  private String preferredLanguage = "et";
  private static final String COMPANY = "Automated Test";
  private final ArrayList<Loan> debtTypesExperienced =
      new ArrayList<>(Arrays.asList(Loan.HOME_LOAN));
  private Education education = Education.UNIVERSITY;
  private EmploymentDuration employmentDuration = EmploymentDuration.YEARS_3_5;
  private Integer householdChildren = 0;
  private Integer livingCosts = 50000;
  private MaritalStatus maritalStatus = MaritalStatus.MARRIED;
  private Integer netIncome = 900000;
  private Occupation occupation = Occupation.FULL_TIME;
  private OccupationType occupationType = OccupationType.MIDDLE_MANAGER;
  private Residence residence = Residence.OWNER;
  private final WeekdayHourOfApplication weekdayHourOfApplication =
      WeekdayHourOfApplication.EVENING;

  private static final String UPDATED_CREDIT_APLICATION_DATES = "UPDATE CreditApplication ca " +
      "SET ca.created = DATE_FORMAT(ca.created, REPLACE('%Y-%m-%d %H:%i:%s', '%H', ?)), " +
      "ca.entityVersion = ca.entityVersion+1 " +
      "WHERE ca.customer_ID = ?";

  private SvingEE() {
    LOG.info("Instantiated SvingEE portal.");
  }

  private static class SingletonHolder {
    static final SvingEE instance = new SvingEE();

    private SingletonHolder() {
    }
  }

  public static SvingEE getInstance() {
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

    verifyRegistrationParams(params);
    preferredLanguage = params.getCommunicationLanguage();
    final GeneratedCustomerDTO generatedData = getCustomerData(params, Country.EE);
    final AIOBackendRestClientProvider provider =
        new AIOBackendRestClientProvider(params.getEnvironment().envName,
            Brand.SVING.name().toLowerCase(), Country.EE, restEventListener);
    mobileIdRequest(provider.provide(EEBanksClient.class), generatedData.getIdentifier(),
        generatedData.getMsisdn());
    Customer customer =
        authenticateCustomer(provider.provide(GenericRegistrationClient.class),
            generatedData.getIdentifier(), params.getEnvironment());
    notifyCustomerRegistered(customer);
    startCustomerRegistration(provider, generatedData, customer, params.getEnvironment());
    final ProductDTO product = chooseProductAndSubmitApplication(
        provider.provide(EECreditApplicationClient.class), generatedData, params);
    customer = fetchCustomerWithAdditionalData(customer, product, generatedData.getEmail(),
        params.getEnvironment());
    if (params.getVerification()) {
      verification(params, customer);
    }
    sendVerificationRequest(params.getEnvironment(), provider, customer, "ee");
    LOG.info(REGISTRATION_COMPLETE_PATTERN, params.getEnvironment(), customer.id);
    return customer;
  }

  private void verifyRegistrationParams(final RegistrationParams params) {
    if (params.getCountry() != Country.EE || params.getBrand() != Brand.SVING) {
      final String msg =
          String.format("SvingEE can't register customers for Country: %s, Brand: %s",
              params.getCountry(), params.getBrand());
      LOG.error(msg);
      throw new IllegalArgumentException(msg);
    }

    if (params.getProductType() == ProductType.IL)
      throw new CustomerRegistrationException("IL product is unavailable for Sving!");
  }

  private void mobileIdRequest(
      final EEBanksClient client, final String identifier, final String msisdn) {
    final MobileIdAuthRequestDTO mobileIdAuthRequest =
        new MobileIdAuthRequestDTO(identifier, msisdn);
    final MobileIdAuthResponseDTO mobileIdResponse = client.mobileIdAuth(mobileIdAuthRequest);
    try {
      client.mobileIdVerify(mobileIdResponse);
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
      final Customer customer, final Environment env) {
    final RegistrationDataDTO registrationData = prepareRegistrationData(generatedData);
    final GenericCreditApplicationClient creditApplicationClient =
        provider.provide(GenericCreditApplicationClient.class);
    creditApplicationClient.startNewApplication(registrationData);
    updateApplicationHour(customer.id, env);

    final GenericCustomerClient customerClient = provider.provide(GenericCustomerClient.class);

    final MsisdnUpdateDTO msisdnUpdateDTO = new MsisdnUpdateDTO();
    msisdnUpdateDTO.msisdn = generatedData.getMsisdn();
    customerClient.updateMsisdn(msisdnUpdateDTO);

    customerClient.updateMarketingConsent(getMarketingConsentList());
    customerClient.register(registrationData);
    final AuthpostofficeDTO authpostoffice = new AuthpostofficeDTO();
    authpostoffice.authenticationPostOfficeId = AUTH_POST_OFFICE_ID;
    creditApplicationClient.updateAuthPostOffice(authpostoffice);

    creditApplicationClient.storeFinancialData(prepareFinancialDataDTO());
  }

  private RegistrationDataDTO prepareRegistrationData(final GeneratedCustomerDTO generatedData) {
    final RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
    registrationDataDTO.address = mapToDTO(generatedData.getAddress(), generatedData.getCountry());

    final BankAccountUpdateDTO bankAccountUpdateDTO = new BankAccountUpdateDTO();
    bankAccountUpdateDTO.bankAccount = generatedData.getBankAccount().getNumber();
    registrationDataDTO.bankAccount = bankAccountUpdateDTO;

    final CommunicationSettingsDTO communicationSettingsDTO = new CommunicationSettingsDTO();
    communicationSettingsDTO.contractDeliveryMethod = CONTRACT_DELIVERY_METHOD;
    communicationSettingsDTO.invoiceDeliveryMethod = INVOICE_DELIVERY_METHOD;
    communicationSettingsDTO.preDueDateReminderSMS = SMS_REMINDER;
    registrationDataDTO.communicationSettings = communicationSettingsDTO;

    final EmailUpdateDTO emailUpdateDTO = new EmailUpdateDTO();
    emailUpdateDTO.email = generatedData.getEmail();
    registrationDataDTO.email = emailUpdateDTO;

    final MsisdnVerificationDTO msisdnVerificationDTO = new MsisdnVerificationDTO();
    msisdnVerificationDTO.msisdn = generatedData.getMsisdn();
    msisdnVerificationDTO.otp = OTP;
    registrationDataDTO.msisdnVerification = msisdnVerificationDTO;

    final PasswordUpdateDTO passwordUpdateDTO = new PasswordUpdateDTO();
    passwordUpdateDTO.password = findDefaultUserPass();
    registrationDataDTO.password = passwordUpdateDTO;

    registrationDataDTO.preferredLanguage = preferredLanguage;

    return registrationDataDTO;
  }

  private List<MarketingConsentDTO> getMarketingConsentList() {
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
    return financialDataDTO;
  }

  private ProductDTO chooseProductAndSubmitApplication(final EECreditApplicationClient client,
      final GeneratedCustomerDTO generatedData, final RegistrationParams params) {
    final List<ProductDTO> productsList =
        params.getProductType() == ProductType.CL ? client.getAvailableCreditLineProducts()
            : client.getAvailableInstallmentProducts();
    ProductDTO product = chooseProduct(productsList, params.getProductLevel());
    product = getMaxProductFromAutoApprove(client, product);
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

    final boolean isDraw = params.getDrawPercentage() > 0 ||
        params.getProductType() == ProductType.IL;

    final SalesforceProvider salesforceProvider = buildSalesforceProvider(params.getEnvironment());
    final String accountId = getAndCheckAccountId(salesforceProvider, customer.id);
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
}
