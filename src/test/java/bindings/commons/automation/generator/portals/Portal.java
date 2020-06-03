package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.SfParamsConstants;
import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.CustomerDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.UserRegistrationInfoDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.AdditionalConsentDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentName;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentType;
import com.ipfdigital.automation.aio.rest.dto.gdpr.ConsentVersion;
import com.ipfdigital.automation.aio.rest.dto.gdpr.MarketingConsentDTO;
import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.AIORestEventListener;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.AutoApprovalStatusDTO;
import com.ipfdigital.automation.aio.rest.v2.creditapplication.EECreditApplicationClient;
import com.ipfdigital.automation.aio.rest.v2.customer.GenericCustomerClient;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.CustomerGenerator;
import com.ipfdigital.automation.api.customer.DebitCardGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.GenerateCustomerDTO;
import com.ipfdigital.automation.customer.GeneratedCustomerDTO;
import com.ipfdigital.automation.customer.address.CustomerAddressDTO;
import com.ipfdigital.automation.customer.ssn.GenerateSsnParamsDTO;
import com.ipfdigital.automation.customer.ssn.SsnType;
import com.ipfdigital.automation.generator.exceptions.NotImplementedException;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.observer.GeneratorRegistrationEventPublisher;
import com.ipfdigital.automation.generator.utils.AIODataUtils;
import com.ipfdigital.automation.generator.utils.CustomerRegistration;
import com.ipfdigital.automation.generator.utils.DatabaseQuery;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductLevel;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import com.ipfdigital.automation.salesforce.SFAttachmentType;
import com.ipfdigital.automation.salesforce.SFTask;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import com.ipfdigital.database.connection.DBServiceProvider;
import com.ipfdigital.exceptions.DatabaseConnectionException;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.ipfdigital.automation.generator.observer.CustomerCreatedEvent.aCustomerCreatedEvent;

public abstract class Portal extends Scoring implements CustomerRegistration {
  private static final Logger LOG = LogManager.getLogger(Portal.class);
  private static final String SSN = "SSN";
  private static final String MSISDN = "MSISDN";
  private static final String CREDIT_LINE = "CREDIT_LINE";
  private static List<ProductDTO> customerProposedProducts = null;
  static Map<String, String> updateMessages = new HashMap<>();
  protected Integer age = null;
  private Optional<GeneratorRegistrationEventPublisher> generatorRegistrationObservable =
      Optional.empty();

  CustomerGenerator customerGenerator;
  private SsnGenerator ssnGenerator;
  CustomerBankAccountGenerator bankAccountGenerator;
  DebitCardGenerator debitCardGenerator;
  private MsisdnGenerator msisdnGenerator;

  protected Optional<AIORestEventListener> restEventListener = Optional.empty();


  public static final String INSERT_SCORE_TO_MULE_QUERY =
      "INSERT INTO cached_messages (SERVICE_ID, REQUEST_ID, CACHED_DATE, MESSAGE) values (?, ?, CURDATE(), ?)";
  private static final String USED_SSN_QUERY =
      "SELECT COUNT(ID) FROM Customer WHERE identifier = ?";
  static final String FIND_CREDIT_APPLICATION_CHANNEL_QUERY =
      "SELECT ca.channel FROM CreditApplication ca LEFT JOIN Customer cus ON ca.customer_ID = cus.ID WHERE cus.ID  = ?";

  private static final String TEST_USER_PASS = "Qwerty123";
  private static final String TASK_ID_NOT_FOUND_MSG_PATTERN =
      "Task %s ID not found for customer %s";
  private static final String TASK_NOT_CLOSED_MSG_PATTERN = "Task %s not closed for customer %s";
  static final String REGISTRATION_COMPLETE_PATTERN =
      "Customer registration on {} complete! Customer ID: {}";

  public static Portal fromParams(final RegistrationParams params) {

    return ofBrandAndCountry(params.getBrand(), params.getCountry());
  }

  public void setGeneratorRegistrationObservable(
      final Optional<GeneratorRegistrationEventPublisher> generatorRegistrationEventPublisher) {

    generatorRegistrationObservable = generatorRegistrationEventPublisher;
  }

  protected void notifyCustomerRegistered(final Customer customer) {

    generatorRegistrationObservable
        .ifPresent(o -> o.notifyObservers(aCustomerCreatedEvent(customer)));
  }

  /**
   * Returns the default portal for the given country. Excludes Sving.
   */
  public static Portal forCountry(final Country country) {
    switch (country) {
      case FI:
        return Credit24FI.getInstance();
      case PL:
        return HapiPL.getInstance();
      case LT:
        return Credit24LT.getInstance();
      case LV:
        return Credit24LV.getInstance();
      case EE:
        return Credit24EE.getInstance();
      case ES:
        return HapiES.getInstance();
      case MX:
        return HapiMX.getInstance();
      case AU:
        return Credit24AU.getInstance();
      case CZ:
        return CrediteaCZ.getInstance();
      default:
        throw new IllegalArgumentException("No default portal for country: " + country);
    }
  }

  public static Portal ofBrandAndCountry(final Brand brand, final Country country) {

    switch (brand) {
      case HAPI:
        return getHapiPortal(country);
      case CREDIT24:
        return getC24Portal(country);
      case SVING:
        return getSvingPortal(country);
      case CREDITEA:
        return getCrediteaPortal(country);
      default:
        throw new IllegalStateException("Brand not supported: " + brand);
    }
  }

  private static Portal getCrediteaPortal(final Country country) {
    return forCountry(country);
  }

  private static Portal getHapiPortal(final Country country) {
    switch (country) {
      case PL:
        return HapiPL.getInstance();
      case MX:
        return HapiMX.getInstance();
      case ES:
        return HapiES.getInstance();
      default:
        throw new IllegalStateException(
            "Hapi brand is not supported for country: " + country.getCountryName());
    }
  }

  void checkProductReplication(final Environment env, final GeneratedCustomerDTO generatedData,
      final ProductDTO product) {
    try {
      AIODataUtils.replicateProductIfNotAlreadyInSF(env, product.id);
    } catch (final Exception e) {
      throw new ProductNotReplicatedException(generatedData.getIdentifier(), e);
    }
  }

  private static Portal getC24Portal(final Country country) {
    switch (country) {
      case AU:
        return Credit24AU.getInstance();
      case EE:
        return Credit24EE.getInstance();
      case FI:
        return Credit24FI.getInstance();
      case LT:
        return Credit24LT.getInstance();
      case LV:
        return Credit24LV.getInstance();

      default:
        throw new IllegalStateException(
            "Credit24 brand is not supported for country: " + country.getCountryName());
    }
  }

  private static Portal getSvingPortal(final Country country) {
    switch (country) {
      case EE:
        return SvingEE.getInstance();
      case LT:
        return SvingLT.getInstance();
      default:
        throw new IllegalStateException(
            "Sving brand is not supported for country: " + country.getCountryName());
    }
  }

  /**
   * Enables to throw an error when user chooses verify = true for a country where it's not
   * implemented. Without this, we'd first register the customer, and then tell the user we can't
   * verify it yet (takes a while). Should be removed when all countries are fully implemented.
   */
  public abstract void checkVerificationImplementation();

  ProductDTO chooseProduct(final List<ProductDTO> productsList, final ProductLevel productLevel) {
    if (productsList.isEmpty()) {
      throw new IllegalArgumentException(
          "Products list is empty! Unable to choose " + productLevel + " product.");
    }
    final List<ProductDTO> products = new ArrayList<>(productsList);
    products.sort(Comparator.comparing(x -> x.principal));
    switch (productLevel) {
      case TOP:
        return products.get(products.size() - 1);
      case MID:
        return products.get(products.size() / 2);
      case MIN:
        return products.get(0);

      default:
        throw new IllegalArgumentException("Undefined productLevel: " + productLevel);
    }
  }

  private ProductDTO chooseSpecificProduct(final List<ProductDTO> productsList,
      final RegistrationParams params) {

    final List<ProductDTO> listFiltered = productsList.stream()
        .filter(getProductFilterPredicate(params, "principal"))
        .filter(getProductFilterPredicate(params, "maturity"))
        .filter(getProductFilterPredicate(params, "name"))
        .filter(getProductFilterPredicate(params, "groupName"))
        .collect(Collectors.toList());
    if (listFiltered.isEmpty()) {
      throw new IllegalArgumentException(getErrorMessageForAllProductFilters(params,
          new StringBuilder("No product found for parameters: ")));
    } else {
      return listFiltered.get(0);
    }
  }

  ProductDTO chooseProductByFilters(final RegistrationParams params,
      final List<ProductDTO> productsList) {
    Portal.setProducts(productsList);
    final boolean productParamsDefined = isSpecificProductParamsDefined(params);
    if (productParamsDefined) {
      return chooseProduct(productsList, params.getProductLevel());
    } else {
      return chooseSpecificProduct(productsList, params);
    }
  }

  private boolean isSpecificProductParamsDefined(final RegistrationParams params) {
    return params.getPrincipal() == 0 && params.getMaturiyPeriods() == 0
        && params.getProductName() == null && params.getProductGroupName() == null;
  }

  private Predicate<ProductDTO> getProductFilterPredicate(final RegistrationParams params,
      final String filter) {
    switch (filter) {
      case "principal":
        return (params.getPrincipal() > 0) ? p -> (p.principal == params.getPrincipal().intValue())
            : p -> true;
      case "maturity":
        return (params.getMaturiyPeriods() > 0)
            ? p -> (p.maturityPeriods == params.getMaturiyPeriods())
            : p -> true;
      case "name":
        return (params.getProductName() != null) ? p -> p.name.equals(params.getProductName())
            : p -> true;
      case "groupName":
        return (params.getProductGroupName() != null)
            ? p -> p.groupName.equals(params.getProductGroupName())
            : p -> true;
      default:
        throw new IllegalArgumentException("Incorrect filter name.");
    }
  }

  private String getErrorMessageForAllProductFilters(final RegistrationParams params,
      final StringBuilder error) {
    if (params.getMaturiyPeriods() > 0) {
      error.append("Maturity=").append(params.getMaturiyPeriods()).append(", ");
    }
    if (params.getPrincipal() > 0) {
      error.append("Principal=").append(params.getPrincipal()).append(", ");
    }
    if (params.getProductName() != null) {
      error.append("Name=").append(params.getProductName()).append(", ");
    }
    if (params.getProductGroupName() != null) {
      error.append("GroupName=").append(params.getProductGroupName()).append(", ");
    }
    return error.substring(0, error.lastIndexOf(", ")) + ".";
  }

  private boolean isUsedMsisdn(final Environment env, final String msisdn) {
    return new DAOFactory().getCustomerDAO().isUsedMsisdn(env, msisdn);
  }

  private boolean isUsedSsn(final Environment environment, final String ssn) {

    return !DBServiceProvider.aioDBService()
        .getQueryResult(environment, USED_SSN_QUERY, "COUNT(ID)", ssn)
        .equals("0");
  }

  public boolean isCustomerDrawing(final Environment environment, final Customer customer) {
    return new DAOFactory().getCustomerDAO().findCustomerFirstDrawAmount(environment,
        customer.id.toString()) > 0;
  }

  public static List<ProductDTO> getProducts() {
    return customerProposedProducts;
  }

  public static void setProducts(final List<ProductDTO> newProducts) {
    customerProposedProducts = newProducts;
  }

  public GeneratedCustomerDTO getCustomerData(final RegistrationParams params,
      final Country country) {
    final int maxTries = 5;
    final GenerateCustomerDTO dto = new GenerateCustomerDTO(country,
        GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
        GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE, params.getForeignCustomer());
    final GeneratedCustomerDTO generatedData = customerGenerator.generate(dto);
    return getAndSetCustomerData(params, maxTries, generatedData);
  }

  private GeneratedCustomerDTO getAndSetCustomerData(final RegistrationParams params,
      final int maxTries,
      final GeneratedCustomerDTO generatedData) {
    if (params.getCustomEmail()) {
      generatedData.setEmail(params.getCustomEmailValue());
    }
    if (params.getCustomSsn()) {
      generatedData.setIdentifier(params.getCustomSsnValue());
    } else {
      generateNewSsnIfActualUsed(params, maxTries, generatedData);
    }
    generateNewMsisdnIfActualUsed(params, maxTries, generatedData);
    return generatedData;
  }

  public GeneratedCustomerDTO getCustomerData(
      final RegistrationParams params, final Country country, final Integer age) {
    final int maxTries = 5;
    final GenerateCustomerDTO dto;
    if (age == null) {
      dto = new GenerateCustomerDTO(country, GenerateCustomerDTO.MIN_AGE_DEFAULT_VALUE,
          GenerateCustomerDTO.MAX_AGE_DEFAULT_VALUE);
    } else {
      dto = new GenerateCustomerDTO(country, age, age);
    }
    final GeneratedCustomerDTO generatedData = customerGenerator.generate(dto);
    return getAndSetCustomerData(params, maxTries, generatedData);
  }

  void generateNewSsnIfActualUsed(final RegistrationParams params, final int maxTries,
      final GeneratedCustomerDTO generatedData) {

    int numberOfTry = 0;
    while (isUsedSsn(params.getEnvironment(), generatedData.getIdentifier())) {
      checkNumberOfTry(SSN, maxTries, numberOfTry);
      final SsnType ssnType = generatedData.isForeign() ? SsnType.NIE : SsnType.DNI;
      final GenerateSsnParamsDTO dto = new GenerateSsnParamsDTO(generatedData.getGender(),
          generatedData.getDateOfBirth(), ssnType, generatedData.getCountry());
      generatedData.setIdentifier(ssnGenerator.generate(dto));
      numberOfTry++;
    }
  }

  private void generateNewMsisdnIfActualUsed(final RegistrationParams params, final int maxTries,
      final GeneratedCustomerDTO generatedData) {
    int numberOfTry = 0;
    while (isUsedMsisdn(params.getEnvironment(), generatedData.getMsisdn())) {
      checkNumberOfTry(MSISDN, maxTries, numberOfTry);
      generatedData.setMsisdn(msisdnGenerator.generate(params.getCountry()));
      numberOfTry++;
    }
  }

  private void checkNumberOfTry(final String field, final int maxTries, final int numberOfTry) {
    if (numberOfTry >= maxTries) {
      throw new IllegalArgumentException(
          String.format("Did not generated free %s after %s retries", field, numberOfTry));
    }
  }

  void sendVerificationRequest(final Environment env, final AIOBackendRestClientProvider provider,
      final Customer customer, final String country) {

    try {
      provider.provide(GenericCustomerClient.class).verifyEmail(
          DatabaseQuery.getEmailValidationKey(env.envName, customer.id.toString()), country);
    } catch (final FeignException e) {
      handleExpectedHTTP303Code(e);
    }
  }

  void emailVerification(final Environment env, final String email, final Customer customer) {

    try {
      DatabaseQuery.updateEmailVerification(env.envName, email);
      DatabaseQuery.updateCustomerEmail(env.envName, email, customer.id.toString());
    } catch (final DatabaseConnectionException e) {
      emailVerificationLogError(env, email);
    }
  }

  void emailVerification(final Environment env, final String email) {

    try {
      DatabaseQuery.updateEmailVerification(env.envName, email);
    } catch (final DatabaseConnectionException e) {
      emailVerificationLogError(env, email);
    }
  }

  private void emailVerificationLogError(final Environment env, final String email) {
    final String eMessage =
        String.format("Email %s verification on environment %s fail", email, env.envName);
    LOG.error(eMessage);
    throw new IllegalStateException(eMessage);
  }

  protected SalesforceProvider buildSalesforceProvider(final Environment environment) {
    return new SalesforceProvider(environment.envName);
  }

  protected String getAndCheckAccountId(final SalesforceProvider provider, final long customerId) {
    final String accountId = provider.getAccountId(customerId);
    if (StringUtils.isBlank(accountId)) {
      throw new SalesForceExecutionException("Account " + customerId + " not found");
    }
    return accountId;
  }

  protected void tryCloseSFTask(final SalesforceProvider provider, final SFTask task,
      final long customerId) {
    if (!provider.closeTask(customerId, task)) {
      throw new SalesForceExecutionException(buildTaskNotClosedMessage(task, customerId));
    }
  }

  protected void checkIfContractIsReady(final SalesforceProvider provider, final String accountId) {
    if (!provider.isContractReady(accountId)) {
      throw new SalesForceExecutionException("Contract is not ready for account " + accountId);
    }
  }

  void checkIfBankAccountIsInUseAndCloseTasks(final SalesforceProvider provider,
      final List<SFTask> tasks, final String accountId, final long customerId) {

    if (provider.isBankAccountIsUse(accountId)) {
      tasks.forEach(task -> tryCloseSFTask(provider, task, customerId));
    }
  }

  String buildTaskNotClosedMessage(final SFTask task, final long id) {

    return String.format(TASK_NOT_CLOSED_MSG_PATTERN, task, id);
  }

  String buildTaskIdNotFoundMessage(final SFTask task, final long id) {

    return String.format(TASK_ID_NOT_FOUND_MSG_PATTERN, task, id);
  }

  // TODO Use Mappers like Orika
  AddressDTO mapToDTO(final CustomerAddressDTO customerAddressDTO, final Country country) {
    final AddressDTO addressDTO = new AddressDTO();
    addressDTO.city = customerAddressDTO.getCity();
    addressDTO.street = customerAddressDTO.getStreet();
    addressDTO.postcode = customerAddressDTO.getPostcode();
    if (country != Country.EE) {
      addressDTO.door = customerAddressDTO.getDoor();
    }
    if (country == Country.MX) {
      addressDTO.municipality = customerAddressDTO.getCity();
      addressDTO.neighbourhood = customerAddressDTO.getCity();
      addressDTO.province = "EM";
    }
    if (country == Country.PL) {
      addressDTO.city2 = customerAddressDTO.getCity();
      addressDTO.street = customerAddressDTO.getStreet() + " " + customerAddressDTO.getHome();
      addressDTO.street2 = customerAddressDTO.getStreet() + " " + customerAddressDTO.getHome();
      addressDTO.postcode2 = customerAddressDTO.getPostcode();
      addressDTO.door2 = customerAddressDTO.getDoor();
    }
    if (country == Country.CZ) {
      addressDTO.street = customerAddressDTO.getStreet() + " " + customerAddressDTO.getHome();
    }
    return addressDTO;
  }

  static CustomerDTO buildCustomer(final GeneratedCustomerDTO data) {

    final CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.firstName = data.getFirstName();
    customerDTO.lastName = data.getLastName();
    customerDTO.secondLastName = data.getSecondLastName();
    return customerDTO;
  }


  UserRegistrationInfoDTO buildUserRegistrationInfo(final GeneratedCustomerDTO data) {

    final UserRegistrationInfoDTO userRegistrationInfoDTO = new UserRegistrationInfoDTO();
    if (data.getCountry() == Country.MX) {
      userRegistrationInfoDTO.msisdn = data.getMsisdn();
      userRegistrationInfoDTO.msisdn2 = data.getMsisdn2();
      userRegistrationInfoDTO.ssn = data.getIdentifier();
    } else if (data.getCountry() == Country.PL) {
      userRegistrationInfoDTO.msisdn = data.getPrefixedMsisdn();
      userRegistrationInfoDTO.firstName = data.getFirstName();
      userRegistrationInfoDTO.lastName = data.getLastName();
      userRegistrationInfoDTO.email = data.getEmail();
      userRegistrationInfoDTO.ssn = data.getIdentifier();
    } else if (data.getCountry() == Country.CZ) {
      userRegistrationInfoDTO.msisdn = data.getPrefixedMsisdn();
      userRegistrationInfoDTO.firstName = data.getFirstName();
      userRegistrationInfoDTO.lastName = data.getLastName();
      userRegistrationInfoDTO.email = data.getEmail();
      userRegistrationInfoDTO.ssn = data.getIdentifier().replaceAll("[^\\d]", "");
    } else {
      throw new IllegalStateException("Not supported for country");
    }
    return userRegistrationInfoDTO;
  }

  public <E extends Enum<E>> void updateScore(final String environment, final Country country,
      final String identifier, final String scoreName, final Class<E> creditBureaus) {

    LOG.info("Updating {} scoring {} for identifier: {} on environment: {}", country,
        creditBureaus.getName(), identifier, environment);

    DBServiceProvider.muleDBService()
        .callDB(environment, INSERT_SCORE_TO_MULE_QUERY, statement -> {

          for (final Enum<E> creditBureau : creditBureaus.getEnumConstants()) {
            statement.setString(1, creditBureau.toString());
            statement.setString(2, identifier);
            statement.setString(3, updateMessages.get(creditBureau.toString()));
            checkUpdateSuccess(statement, country, identifier, creditBureau.toString(), scoreName,
                environment);
          }
          return null;
        });
  }

  void checkUpdateSuccess(
      final PreparedStatement statement, final Country country, final String identifier,
      final String creditBureau, final String scoreName, final String environment)
      throws SQLException {

    final int affectedRows = statement.executeUpdate();
    if (affectedRows < 1) {
      final String msg = "Insert failed! identifier: " + identifier + ", score: " + scoreName;
      LOG.error(msg);
      throw new IllegalStateException(msg);
    }
    LOG.info("Successfully inserted {} score: {} {} for identifier: {} on environment: {}", country,
        creditBureau, scoreName, identifier, environment);
  }

  void handleIdentification(final Customer customer, final Country country,
      final Environment environment) {

    environment.getSoapUtils().handleIdentificationV2(country, customer.id,
        SfParamsConstants.IDENTIFICATION_PARAM_EXPECTED_VALUE);
  }

  void handleExpectedHTTP303Code(final FeignException e) {

    if (e.status() != 303) {
      LOG.error(e);
      throw e;
    }
  }

  MarketingConsentDTO buildConsent(final ConsentName consentName,
      final ConsentVersion version,
      final boolean value) {

    final MarketingConsentDTO marketingConsentDTO = new MarketingConsentDTO();
    marketingConsentDTO.consentName = consentName;
    marketingConsentDTO.value = value;
    marketingConsentDTO.version = version.toString();
    marketingConsentDTO.validUntil = "2999-12-31";
    return marketingConsentDTO;
  }

  AdditionalConsentDTO buildConsent(final ConsentType consentType, final ConsentName name,
      final boolean value) {

    final AdditionalConsentDTO additionalConsentDTO = new AdditionalConsentDTO();
    additionalConsentDTO.consentType = consentType;
    additionalConsentDTO.name = name;
    additionalConsentDTO.value = value;
    return additionalConsentDTO;
  }

  ApplicationSubmitDTO buildApplicationSubmitDTO(final int principal,
      final int drawPercentage) {

    final ApplicationSubmitDTO applicationSubmitDTO = new ApplicationSubmitDTO();
    applicationSubmitDTO.firstDrawAmount = principal * drawPercentage / 100;
    return applicationSubmitDTO;
  }

  ProductSelectionDTO buildProductSelectionDTO(final ProductDTO productDTO,
      final int drawPercentage) {

    final ProductSelectionDTO productSelectionDTO = new ProductSelectionDTO();
    productSelectionDTO.firstDrawAmount = productDTO.principal * drawPercentage / 100;
    productSelectionDTO.id = productDTO.id;
    return productSelectionDTO;
  }

  Customer fetchCustomerWithAdditionalData(Customer customer, final ProductDTO product,
      final String email,
      final Environment environment) {

    customer = new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName,
        customer.identifier);
    customer.password = findDefaultUserPass();
    customer.product = product;
    customer.email = email;
    return customer;
  }

  protected String findDefaultUserPass() {
    // FIXME Security issue. We should get password from ConfigServer
    return TEST_USER_PASS;
  }

  public void setCustomerGenerator(
      final CustomerGenerator customerGenerator) {
    this.customerGenerator = customerGenerator;
  }

  public void setRestEventListener(final Optional<AIORestEventListener> restEventListener) {
    this.restEventListener = restEventListener;
  }

  ProductDTO getMaxProductFromAutoApprove(final EECreditApplicationClient client,
      ProductDTO product) {
    final AutoApprovalStatusDTO autoApprove = client.autoApprovalStatus();
    if (shouldReplaceProduct(product, autoApprove)) {
      product = autoApprove.maxAutoApprovalProduct;
    }
    return product;
  }

  protected Customer verifyEmail(final GenericCustomerClient client,
      final GeneratedCustomerDTO generatedData, final Environment env) {
    final Customer customer = new DAOFactory().getCustomerDAO()
        .getCustomerBySsn(env.envName, generatedData.getIdentifier());
    try {
      client.verifyEmail(
          DatabaseQuery.getEmailValidationKey(env.envName, customer.id.toString()),
          customer.country.name());
    } catch (final FeignException e) {
      handleExpectedHTTP303Code(e);
    }
    if (DatabaseQuery.checkEmailConfirmation(env.envName, customer.id.toString())) {
      customer.email = generatedData.getEmail();
    }
    customer.password = findDefaultUserPass();
    return customer;
  }

  private boolean shouldReplaceProduct(final ProductDTO product,
      final AutoApprovalStatusDTO autoApprove) {
    return autoApprove.appPassedAutoApproveConditions
        && autoApprove.maxAutoApprovalProduct.principal < product.principal
        && product.type.equals(CREDIT_LINE);
  }

  public void setSsnGeneratorService(final SsnGenerator ssnGenerator) {
    this.ssnGenerator = ssnGenerator;
  }

  public void setBankAccountGenerator(final CustomerBankAccountGenerator bankAccountGenerator) {
    this.bankAccountGenerator = bankAccountGenerator;
  }

  public void setDebitCardGenerator(final DebitCardGenerator debitCardGenerator) {
    this.debitCardGenerator = debitCardGenerator;
  }

  public void setMsisdnGenerator(final MsisdnGenerator msisdnGenerator) {
    this.msisdnGenerator = msisdnGenerator;
  }

  public List<SFAttachmentType> getAttachmentTypesToUpload() {
    throw new NotImplementedException("Not implemented");
  }
}
