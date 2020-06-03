package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.aio.rest.v2.AIORestEventListener;
import com.ipfdigital.automation.api.customer.CustomerBankAccountGenerator;
import com.ipfdigital.automation.api.customer.CustomerGenerator;
import com.ipfdigital.automation.api.customer.DebitCardGenerator;
import com.ipfdigital.automation.api.customer.MsisdnGenerator;
import com.ipfdigital.automation.api.customer.SsnGenerator;
import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.exceptions.CustomerRegistrationException;
import com.ipfdigital.automation.generator.model.aio.BankPayment;
import com.ipfdigital.automation.generator.model.aio.Contract;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.dao.DAOFactory;
import com.ipfdigital.automation.generator.observer.GeneratorRegistrationEventPublisher;
import com.ipfdigital.automation.generator.portals.HapiES;
import com.ipfdigital.automation.generator.portals.HapiMX;
import com.ipfdigital.automation.generator.portals.HapiPL;
import com.ipfdigital.automation.generator.portals.Portal;
import com.ipfdigital.automation.generator.utils.scores.MonitorsPL;
import com.ipfdigital.automation.generator.utils.scores.ScoreES;
import com.ipfdigital.automation.generator.utils.scores.ScoreMX;
import com.ipfdigital.automation.generator.utils.scores.ScorePL;
import com.ipfdigital.automation.salesforce.rest.v2.SalesforceProvider;
import com.ipfdigital.automation.salesforce.rest.v2.exceptions.SalesForceExecutionException;
import com.ipfdigital.database.connection.DBServiceProvider;
import com.ipfdigital.exceptions.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class AIODataUtils {

  private static final Logger LOG = LogManager.getLogger(AIODataUtils.class);
  private static final String EXCEPTION_DURING_CUSTOMER_REGISTRATION_MSG_PATTERN =
      "Exception during Customer Registration (%s)!";
  private static final String EXCEPTION_DURING_CUSTOMER_ACCOUNT_CREATION_MSG_PATTERN =
      "Exception during Customer Account Creation (%s)!";
  private static final String UPDATE_VERSION_SQL =
      "UPDATE Product SET entityVersion = entityVersion + 1 WHERE ID = ?";

  private AIODataUtils() {

  }

  private static final Map<Country, Integer> DAYS_TO_INVOICE;

  static {
    DAYS_TO_INVOICE = new EnumMap<>(Country.class);
    DAYS_TO_INVOICE.put(Country.ES, 7);
    DAYS_TO_INVOICE.put(Country.MX, 5);
    DAYS_TO_INVOICE.put(Country.PL, 14);
    DAYS_TO_INVOICE.put(Country.FI, 14);
    DAYS_TO_INVOICE.put(Country.EE, 14);
    DAYS_TO_INVOICE.put(Country.LT, 14);
    DAYS_TO_INVOICE.put(Country.LV, 14);
    DAYS_TO_INVOICE.put(Country.AU, 2);
    DAYS_TO_INVOICE.put(Country.CZ, 7);
  }

  /**
   * Inserts customer score messages to <code>cached_messages</code> table in mule database.
   *
   * @param env SIT or UAT
   * @param country MX or PL
   * @param score A, B, S etc.
   * @param ssn Customer identifier
   */
  public static void insertScoreToMule(final Environment env, final Country country,
      final String score, final String ssn) {

    switch (country) {
      case MX:
        // Refactoring of the MX insert champion/challenger score needs a separate task, for now we
        // always choose the default Champion score to Mule
        HapiMX.getInstance().insertScore(env, ScoreMX.valueOf("CHAMPION_" + score), ssn);
        break;
      case PL:
        HapiPL.getInstance().insertScore(env, ScorePL.valueOf(score), ssn);
        break;
      case ES:
        HapiES.getInstance().insertScore(env, ScoreES.valueOf(score.toUpperCase()), ssn);
        break;
      default:
        throw new IllegalArgumentException(
            "Score insertion is not needed for " + country + " country.");
    }
  }

  /**
   * Inserts customer company NIP score messages to <code>cached_messages</code> table in mule
   * database.
   *
   * @param env SIT or UAT
   * @param country MX or PL
   * @param score A, B, S etc.
   * @param nip Customer company NIP number
   */
  public static void insertSsnScoreWithNipAndRegonToMule(final Environment env,
      final Country country,
      final String score, final String ssn, final String nip, final String regon) {

    if (country == Country.PL) {
      HapiPL.getInstance().insertSsnScoreWithNipAndRegon(env, ScorePL.valueOf(score), ssn, nip,
          regon);
    } else {
      throw new IllegalArgumentException(
          String.format("Nip score insertion is not needed for %s country.", country));
    }
  }

  /**
   * Inserts customer score messages to <code>cached_messages</code> table in mule database.
   *
   * @param env st01 or uat (lower case)
   * @param country MX or PL
   * @param score A, B, S etc.
   * @param ssn Customer identifier
   */
  public static void insertScoreToMule(final String env, final Country country,
      final String score,
      final String ssn) {
    insertScoreToMule(Environment.forName(env), country, score, ssn);
  }

  /**
   * Inserts customer score updating messages to <code>cached_messages</code> table in mule
   * database.
   *
   * @param env st01 or uat (lower case)
   * @param country PL
   * @param ssn Customer identifier
   */
  public static void updateScoreInMule(final String env, final Country country, final String ssn) {

    if (country == Country.PL) {
      HapiPL.getInstance().updateScore(Environment.forName(env).envName, country, ssn,
          ScorePL.AA.name(), MonitorsPL.class);
    } else {
      throw new IllegalArgumentException("Score update is not needed for " + country + " country!");
    }
  }

  /**
   * Registers a customer according to provided Registration Parameters.
   *
   * @param params registration parameters - environment and country are required
   * @return a registered customer
   */
  public static Customer registerCustomer(final RegistrationParams params,
      final Optional<GeneratorRegistrationEventPublisher> generatorRegistrationEventPublisher,
      final Optional<AIORestEventListener> aioRestEventPublisher,
      final CustomerGenerator customerGenerator,
      final CustomerBankAccountGenerator bankAccountGenerator,
      final SsnGenerator ssnGenerator,
      final DebitCardGenerator debitCardGenerator,
      final MsisdnGenerator msisdnGenerator) {

    try {
      final Portal portal = Portal.fromParams(params);
      portal.setCustomerGenerator(customerGenerator);
      portal.setBankAccountGenerator(bankAccountGenerator);
      portal.setGeneratorRegistrationObservable(generatorRegistrationEventPublisher);
      portal.setSsnGeneratorService(ssnGenerator);
      portal.setDebitCardGenerator(debitCardGenerator);
      portal.setRestEventListener(aioRestEventPublisher);
      portal.setMsisdnGenerator(msisdnGenerator);
      if (Boolean.TRUE.equals(params.getVerification())) {
        portal.checkVerificationImplementation();
      }

      return portal.createCustomerWithApplication(params);
    } catch (final DatabaseConnectionException | IOException
        | SalesForceExecutionException e) {
      LOG.error(String.format(EXCEPTION_DURING_CUSTOMER_REGISTRATION_MSG_PATTERN, params), e);
      throw new CustomerRegistrationException(
          String.format(EXCEPTION_DURING_CUSTOMER_REGISTRATION_MSG_PATTERN, params), e);
    }
  }

  public static Customer registerCustomer(final RegistrationParams params,
      final CustomerGenerator customerGenerator,
      final CustomerBankAccountGenerator bankAccountGenerator,
      final SsnGenerator ssnGenerator,
      final DebitCardGenerator debitCardGenerator,
      final MsisdnGenerator msisdnGenerator) {
    return registerCustomer(params, Optional.empty(), Optional.empty(),
        customerGenerator, bankAccountGenerator, ssnGenerator, debitCardGenerator, msisdnGenerator);
  }

  public static Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider, final CustomerGenerator customerGenerator) {

    try {
      final Portal portal = Portal.fromParams(params);
      portal.setCustomerGenerator(customerGenerator);
      if (Boolean.TRUE.equals(params.getVerification())) {
        portal.checkVerificationImplementation();
      }
      return portal.createCustomerAccount(params, provider);
    } catch (final DatabaseConnectionException | IOException
        | SalesForceExecutionException e) {
      LOG.error(String.format(EXCEPTION_DURING_CUSTOMER_ACCOUNT_CREATION_MSG_PATTERN, params), e);
      throw new CustomerRegistrationException(
          String.format(EXCEPTION_DURING_CUSTOMER_ACCOUNT_CREATION_MSG_PATTERN, params), e);
    }
  }

  /**
   * Finds a contract for the given customer, performs "newManualPayment" and pays its
   * outstandingAmount.
   *
   * @param environment ST01 or UAT
   * @param identifier ssn of chosen customer
   */
  public static void uploadManualBankPayments(final Environment environment,
      final String identifier) {

    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName, identifier);
    final Contract contract =
        new DAOFactory().getContractDAO().findContractInAio(environment, customer.id);

    final SOAPUtils soapUtils = environment.getSoapUtils();

    final Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    final Date tomorrow = calendar.getTime();

    final int amount =
        soapUtils.getTotalOutstandingAmount(contract, tomorrow, customer.country);
    final String externalReference =
        soapUtils.uploadManualBankPayments(customer, amount, new Date());
    soapUtils.allocatePayment(customer.country,
        BankPayment.fromAioByExternalReference(environment.envName, externalReference));
  }

  /**
   * Finds a contract for the given customer and pays its outstandingAmount. Closes the contract
   * afterwards. Waits for the closed contract to replicate.
   *
   * @param environment ST01 or UAT
   * @param identifier ssn of chosen customer
   */
  private static void completeContract(final Environment environment, final String identifier) {

    uploadManualBankPayments(environment, identifier);

    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName, identifier);
    final Contract contract =
        new DAOFactory().getContractDAO().findContractInAio(environment, customer.id);

    environment.getSoapUtils().closeContract(contract, new Date(),
        "PAID");
    final SalesforceProvider salesforceProvider = new SalesforceProvider(environment.envName);
    salesforceProvider.isContractReady(customer.id);
  }

  /**
   * @see #completeContract(Environment, String)
   */
  public static void completeContract(final String env, final String identifier) {
    completeContract(Environment.forName(env), identifier);
  }

  /**
   * Triggers the ContractSendingJob for the given customer
   *
   * @param customer customer whose contract will be sent. Needs to have environment specified.
   */
  public static void sendContract(final Customer customer, final Environment environment) {
    if (environment == null) {
      throw new CustomerRegistrationException(
          "Environment not specified! Make sure the Customer object has environment set.");
    }
    environment.getSoapUtils().triggerContractSendingJob(customer,
        "ContractSendGenerator");
  }

  /**
   * Adjusts the records matching the given identifier (ssn) in the following tables:
   * <ul>
   * <li>CreditApplication</li>
   * <li>Contract</li>
   * <li>Invoice</li>
   * <li>Allocation</li>
   * <li>AbstractCase</li>
   * <li>BankPaymentBatch</li>
   * <li>Payment</li>
   * <li>CollectionOrder</li>
   * <li>ExtraServiceSubscription</li>
   * <li>InvoiceParams</li>
   * <li>CashFlow</li>
   * <li>UserRegistrationRequest</li>
   * </ul>
   * Triggers InvoiceGeneratingJob and InvoiceSendingJob afterwards.
   *
   * @param environment environment name (such as "st01", "uat")
   * @param identifier customer identifier (ssn)
   * @param days days to adjust the dates by. Positive values will be subtracted from the dates
   *        (effectively moving them backwards), negative values will move the dates forwards
   *        (future).
   */
  public static void generateInvoices(final Environment environment, final String identifier,
      final Integer days) {
    final String user = "InvoiceGenerator";
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName, identifier);
    final SOAPUtils client = environment.getSoapUtils();
    verifyMmp(environment, customer);
    client.triggerContractSendingJob(customer, user);
    shiftInvoicingDates(environment, days, customer.id);
    client.triggerInvoiceGeneratingJob(customer, user);
    Utils.safeWait(10000, "Invoice generation job processing");
    if (customer.country != Country.AU) {
      client.triggerInvoiceSendingJob(customer, user, environment);
    }
  }

  private static void verifyMmp(final Environment environment, final Customer customer) {
    final String errorMsg = "<b>Invoice was not generated as MMP in InvoicingParams is 0</b>";
    Utils.waitUntil(() -> isMmpOverZero(environment.envName, customer.id), 5000, 60000, errorMsg);
  }

  private static boolean isMmpOverZero(final String env, final Long customerId) {
    return new DAOFactory().getInvoicingParamsDAO().getLatestMmp(env, customerId) > 0;
  }

  public static void jobIdRequestWithParams(
      final Environment env, final String country, final String jobName,
      final String customerIdsRange, final String user) {
    env.getSoapUtils().triggerJobWithParams(Country.valueOf(country), jobName, customerIdsRange,
        user);
  }

  /**
   * @see #generateInvoices(Environment, String, Integer)
   */
  public static void generateInvoices(final String env, final String identifier,
      final Integer days) {
    generateInvoices(Environment.forName(env), identifier, days);
  }

  /**
   * Generates invoices for customer. Requires a proper {@link #DAYS_TO_INVOICE} entry.
   *
   * @param environment env
   * @param identifier ssn
   */
  private static void generateInvoices(final Environment environment, final String identifier) {

    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName, identifier);
    generateInvoices(environment, identifier, daysToInvoice(customer, environment));
  }

  /**
   * @see #generateInvoices(Environment, String)
   */
  public static void generateInvoices(final String env, final String identifier) {
    generateInvoices(Environment.forName(env), identifier);
  }

  /**
   * Generates invoices for customer, shifting dates accordingly.
   *
   * @param environment env
   * @param identifier ssn
   * @param days due date will be set to this many days ahead
   */
  public static void generateInvoicesSettingDays(final Environment environment,
      final String identifier,
      final Integer days) {
    final Customer customer =
        new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName, identifier);
    if (days == null) {
      generateInvoices(environment, identifier);
    } else {
      generateInvoices(environment, identifier, daysToInvoice(customer, days, environment));
    }
  }

  /**
   * @see #generateInvoicesSettingDays(Environment, String, Integer)
   */
  public static void generateInvoicesSettingDays(
      final String env, final String identifier, final Integer days) {
    generateInvoicesSettingDays(Environment.forName(env), identifier, days);
  }

  /**
   * Adjusts the records matching the given identifier (ssn) in the following tables:
   * <ul>
   * <li>CreditApplication</li>
   * <li>Contract</li>
   * <li>Invoice</li>
   * <li>Allocation</li>
   * <li>AbstractCase</li>
   * <li>BankPaymentBatch</li>
   * <li>Payment</li>
   * <li>CollectionOrder</li>
   * <li>ExtraServiceSubscription</li>
   * <li>InvoiceParams</li>
   * <li>CashFlow</li>
   * <li>UserRegistrationRequest</li>
   * </ul>
   *
   * @param env environment name (such as "st01", "uat")
   * @param days days to adjust the dates by. Positive values will be subtracted from the dates
   *        (effectively moving them backwards), negative values will move the dates forwards
   *        (future).
   * @param customerId ID of the customer whose account will be affected
   */
  public static void shiftInvoicingDates(final Environment env, final int days,
      final Long customerId) {
    LOG.info("Shifting invoicing dates on {} {} days backward for customerID: {}", env.envName,
        days, customerId);
    final DAOFactory daoFactory = new DAOFactory();
    daoFactory.getCreditApplicationDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getContractDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getInvoiceDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getAllocationDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getAbstractCaseDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getBankPaymentBatchDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getPaymentDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getCollectionOrderDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getExtraServiceSubscriptionDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getInvoicingParamsDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getCashFlowDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getUserRegistrationRequestDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getContractTerminationNoticeDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getDocumentDeliveryDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getCustomerMetadataDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getCollectionOrderUpdateDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getContractDrawCampaignDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getExtResponseDAO().shiftDates(env.envName, days, customerId);
    daoFactory.getScoringResultContainerDAO().shiftDates(env.envName, days, customerId);
  }

  /**
   * Executes a payment on behalf of the chosen customer.
   *
   * @param env environment name (such as "st01", "uat")
   * @param ssn identifier of the chosen customer
   * @param amount the amount to pay (in cents)
   */
  public static void executePayment(final Environment env, final String ssn, final int amount) {
    final Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, 1); // makes the date appear as today in SF
    executePayment(env, ssn, amount, calendar.getTime());
  }

  /**
   * Executes a payment on behalf of the chosen customer.
   *
   * @param env environment name (such as "st01", "uat")
   * @param ssn identifier of the chosen customer
   * @param amount the amount to pay (in cents)
   * @param date payment date
   */
  private static void executePayment(final Environment env, final String ssn, final int amount,
      final Date date) {
    final Customer customer = new DAOFactory().getCustomerDAO().getCustomerBySsn(env.envName, ssn);
    final SOAPUtils client = env.getSoapUtils();
    final String externalReference = client.uploadManualBankPayments(customer, amount, date);
    Utils.safeWait(5000, "Payment replication");
    client.allocatePayment(customer.country,
        BankPayment.fromAioByExternalReference(env.envName, externalReference));
  }

  /**
   * Insert into mule cache bank scraping report
   *
   * @param env environment name (such as "st01", "uat")
   * @param applicationReference frm CreditApplicationAuResponse
   * @param bankScrapingProvider BankScrapingProviderTypeEnum
   * @param bankAccount bank account
   * @param bankScrapingResponse resources scoringAU.properties
   */
  public static void executeUpdateBankScraping(final Environment env,
      final String applicationReference,
      final String bankScrapingProvider,
      final String bankAccount, final String bankScrapingResponse) {
    env.getSoapUtils().updateBankScraping(applicationReference, bankScrapingProvider, bankAccount,
        bankScrapingResponse);

  }

  /**
   * Executes in collection jobs for the chosen customer.
   *
   * @param env environment name (such as "st01", "uat")
   * @param identifier identifier of the chosen customer
   */
  public static void customerInCollection(final String env, final String identifier) {
    final Customer customer = new DAOFactory().getCustomerDAO().getCustomerBySsn(env, identifier);
    final SOAPUtils client = Environment.forName(env).getSoapUtils();
    final String user = "CollectionGenerator";
    client.triggerCollectionProcessingJob(customer, user);
    Utils.safeWait(10000);
    client.triggerCollectionUploadingJob(customer, user);
  }

  /**
   * Checks if the product is present in SF. If not, updates its entityVersion to trigger
   * replication and waits for it to finish. It can be call a dirty hack.
   *
   * @param env environment (ST01 or UAT)
   * @param productId product ID in AIO database
   */

  public static void replicateProductIfNotAlreadyInSF(final Environment env, final long productId) {
    final SalesforceProvider salesforceProvider = new SalesforceProvider(env.envName);
    if (salesforceProvider.isProductReplicated(productId)) {
      LOG.info("Product {} is already replicated!", productId);
      return;
    }
    triggerProductReplication(env, productId);
    LOG.info("Waiting for product {} to replicate...", productId);
    salesforceProvider.isProductReplicated(productId);
  }

  /**
   * Checks if the product is present in SF. If not, updates its entityVersion to trigger
   * replication and waits for it to finish.
   *
   * @param env environment name
   * @param productId product ID in AIO database
   */

  public static void replicateProductIfNotAlreadyInSF(final String env, final long productId) {
    replicateProductIfNotAlreadyInSF(Environment.forName(env), productId);
  }


  /**
   * Execute update query on entityVersion column in Product table to trigger replication
   *
   * @param env
   * @param productId
   */
  private static void triggerProductReplication(final Environment env, final long productId) {

    DBServiceProvider.aioDBService()
        .callDB(env, UPDATE_VERSION_SQL, statement -> {

          statement.setLong(1, productId);
          LOG.info("Triggering product {} to replicate...", productId);
          final int updatedRows = statement.executeUpdate();
          if (updatedRows < 1) {
            throw new IllegalStateException(
                "Product with ID: " + productId + " was not found in AIO DB!");
          }
          return updatedRows;
        });
  }

  /**
   * Calculates the amount of days to subtract from customer invoicing dates to trigger invoice
   * generation.
   *
   * @param customer verified customer
   * @return the amount of days to subtract
   */
  public static int daysToInvoice(final Customer customer, final Environment environment) {
    final LocalDate nextDueDate =
        new DAOFactory().getInvoicingParamsDAO().findNextDueDate(environment, customer);
    final Integer daysToInvoice = DAYS_TO_INVOICE.get(customer.country);
    if (daysToInvoice == null) {
      throw new IllegalArgumentException(
          "DAYS_TO_INVOICE not defined for country: " + customer.country);
    }
    return calculateDaysToInvoice(customer, nextDueDate, daysToInvoice);
  }

  private static int calculateDaysToInvoice(final Customer customer, final LocalDate nextDueDate,
      final Integer daysToInvoice) {
    final int days = (int) (ChronoUnit.DAYS.between(LocalDate.now(), nextDueDate) - daysToInvoice); // TODO:
    // AT-1411
    LOG.debug("Customer: {}, nextDueDate: {}, daysToInvoice: {}, shift dates by: {}",
        customer.identifier, nextDueDate, daysToInvoice, days);
    return days;
  }

  /**
   * Calculates the amount of days to subtract from customer invoicing dates to trigger invoice
   * generation.
   *
   * @param customer verified customer
   * @param daysToInvoice days before due date
   * @return the amount of days to subtract, use {@link #daysToInvoice(Customer, Environment)} for
   *         preset values
   */
  private static int daysToInvoice(
      final Customer customer, final int daysToInvoice, final Environment environment) {
    final LocalDate nextDueDate =
        new DAOFactory().getInvoicingParamsDAO().findNextDueDate(environment, customer);
    return calculateDaysToInvoice(customer, nextDueDate, daysToInvoice);
  }

  public static Long getCustomerId(final Environment environment, final String ssn) {
    return new DAOFactory().getCustomerDAO().getCustomerBySsn(environment.envName, ssn).id;
  }

}
