package bindings.commons.automation.generator.utils;

import com.ipfdigital.database.connection.DBService.StatementHandler;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseQuery {

  private static final Logger LOG = LogManager.getLogger(DatabaseQuery.class);

  private DatabaseQuery() {
    throw new IllegalStateException("DatabaseQuery class, do not instantiate this!");
  }

  private static final String EMAIL_VALIDATION_KEY_QUERY =
      "SELECT validationKey FROM EmailChangeRequest where customer_ID = ?";
  private static final String EMAIL_VALIDATION_STATE_QUERY =
      "SELECT state FROM EmailChangeRequest where customer_ID = ?";
  private static final String EMAIL_VERIFICATION_STSTE_UPDATE =
      "UPDATE EmailChangeRequest SET state = 'VERIFIED' WHERE email = ?";
  private static final String EMAIL_VERIFICATION_CUSTOMER_UPDATE =
      "UPDATE Customer SET email = ?, entityVersion = entityVersion + 1 where id = ?";
  private static final String IDENTIFICATION_STATUS_UPDATE =
      "UPDATE UserRegistrationRequest SET identificationStatus = 'IDENTIFIED' WHERE customer_ID = ?;";
  private static final String USED_IDENTIFIER_QUERY =
      "SELECT COUNT(ID) FROM Customer WHERE identifier = ?";
  private static final String USED_EMAIL_QUERY =
      "SELECT COUNT(ID) FROM EmailChangeRequest WHERE email = ?";
  private static final String INVOICE_AMOUNT_QUERY = "SELECT inv.amount FROM Customer cu\n" +
      "join Contract co on cu.ID = co.customer_ID\n" +
      "join Invoice inv on inv.contract_ID = co.ID\n" +
      "where\n" +
      "identifier = ? \n" +
      "and inv.stateType is null order by inv.ID asc limit 1";
  private static final String SCORING_STATE_QUERY_AU =
      "SELECT scoringState FROM ApplicationScoringState WHERE creditApplication_ID = (SELECT id FROM CreditApplication WHERE customer_ID = ?)";
  private static final String DEFS_NEGATIVE_SCORE_MESSAGE_AU =
      "SELECT message FROM DecisionEngineData WHERE creditApplication_ID = (SELECT id FROM CreditApplication WHERE customer_ID = ?)";
  private static final String LIMIT_INCREASE_QUERY =
      "SELECT COUNT(ID) FROM ScoringResultContainer WHERE customer_ID = ? AND limitIncrease = 1";
  private static final String APPLICATION_BANK_SCRAPING_QUERY =
      "SELECT bankScrapingReference FROM CreditApplication ca INNER JOIN Customer c ON ca.customer_ID = c.id WHERE c.uuid = ? AND ca.state='OPEN' ORDER BY ca.created DESC";
  private static final String CUSTOMER_UUID_QUERY =
      "SELECT uuid FROM Customer WHERE ID = ?";
  private static final String REPLICATION_QUEUE_QUERY =
      "SELECT 'Customers', COUNT(*) FROM Customer obj  LEFT JOIN sf_replication_info sf ON obj.ID=sf.ID AND sf.type=0 WHERE obj.updated > (CURRENT_DATE() - INTERVAL 15 DAY) AND (sf.ID IS NULL OR (obj.entityVersion>sf.version AND sf.errorCount<9 AND (sf.errorCount=0 OR sf.lastAttemptTime IS NULL)))"
          +
          "UNION SELECT 'Contracts', COUNT(*) FROM Contract obj JOIN sf_replication_info sf2 ON sf2.ID=obj.creditApplication_ID and sf2.type=2 AND sf2.errorCount=0 LEFT JOIN sf_replication_info sf ON obj.ID=sf.ID AND sf.type=1 WHERE obj.updated > (CURRENT_DATE() - INTERVAL 15 DAY) AND (sf.ID IS NULL OR (obj.entityVersion>sf.version AND sf.errorCount<9 AND (sf.errorCount=0 OR sf.lastAttemptTime IS NULL)))"
          +
          "UNION SELECT 'Credit Applications', COUNT(*) FROM CreditApplication obj JOIN sf_replication_info sf0 ON sf0.ID=obj.customer_ID and sf0.type=0 AND sf0.errorCount=0 LEFT JOIN sf_replication_info sf4 ON sf4.ID=obj.product_ID AND sf4.type=4 AND sf4.errorCount=0  LEFT JOIN sf_replication_info sf ON obj.ID=sf.ID AND sf.type=2 WHERE obj.updated > (CURRENT_DATE() - INTERVAL 15 DAY) AND (sf.ID IS NULL OR (obj.entityVersion>sf.version AND sf.errorCount<9 AND (sf.errorCount=0 OR sf.lastAttemptTime IS NULL)))  AND (obj.product_ID is null OR sf4.ID is not null)"
          +
          "UNION SELECT 'Invoices', COUNT(*) FROM Invoice obj JOIN sf_replication_info sf1 ON sf1.ID=obj.contract_ID and sf1.type=1 AND sf1.errorCount=0 LEFT JOIN sf_replication_info sf ON obj.ID=sf.ID AND sf.type=3 WHERE obj.updated > (CURRENT_DATE() - INTERVAL 15 DAY) AND (sf.ID IS NULL OR (obj.entityVersion>sf.version AND sf.errorCount<9 AND (sf.errorCount=0 OR sf.lastAttemptTime IS NULL)))"
          +
          "UNION SELECT 'CreditApplicationCase', COUNT(*) From AbstractCase obj JOIN CreditApplicationCase c ON c.ID=obj.ID JOIN sf_replication_info sf2 ON sf2.ID=c.creditApplication_ID AND sf2.type=2 AND sf2.errorCount=0 AND obj.openStamp>=adddate(curdate(), interval -60 day)  LEFT JOIN sf_replication_info sf ON obj.ID=sf.ID AND sf.type=5 WHERE (sf.ID IS NULL OR (obj.entityVersion>sf.version AND sf.errorCount<1))"
          +
          "UNION SELECT 'NewDrawdownCases', COUNT(*) FROM AbstractCase obj JOIN NewDrawdownCase c ON c.ID=obj.ID LEFT JOIN RefinanceDrawdownCase rdc ON rdc.creditApplication_ID = c.creditApplication_ID  LEFT JOIN AbstractCase rac ON rdc.ID = rac.ID LEFT JOIN sf_replication_info sf1 ON sf1.ID=c.contract_ID and sf1.type=1 AND sf1.errorCount=0 LEFT JOIN sf_replication_info sf2 ON sf2.ID=c.creditApplication_ID AND sf2.type=2 AND sf2.errorCount=0 LEFT JOIN sf_replication_info sf ON obj.ID=sf.ID AND sf.type=6 WHERE obj.updated > (CURRENT_DATE() - INTERVAL 15 DAY) AND (sf.ID IS NULL OR (obj.entityVersion>sf.version AND sf.errorCount<9 AND (sf.errorCount=0 OR sf.lastAttemptTime IS NULL))) AND (c.contract_ID is null or sf1.ID is not null) AND (c.creditApplication_ID = sf2.ID or sf2.ID is null)  AND (rdc.ID IS NULL OR rac.state = 'ACCEPTED')";

  private static final String FOUND = "Found {} ";

  private static final int COLUMN_NAME_INDEX = 1;

  private static final int COLUMN_COUNT_INDEX = 2;

  // TODO task AT-1913
  public static String getEmailValidationKey(final String env, final String customerID) {

    LOG.debug("Select validationKey for customer_ID: {} on environment: {}", customerID, env);

    return DBServiceProvider.aioDBService()
        .callDB(env, EMAIL_VALIDATION_KEY_QUERY, statement -> {
          statement.setString(1, customerID);
          return tryGetResult(env, customerID, statement, "validationKey",
              "Successfully selected validationKey: {} for customer_ID: {} on environment: {}",
              "Select failed! no validationKey for customer_ID: ");
        });

  }

  public static String getCustomerApplicationBankScrapingReference(final String env,
      final String customerUUID) {

    LOG.debug("Select bankScrapingReference for customer_ID: {} on environment: {}", customerUUID,
        env);

    return DBServiceProvider.aioDBService()
        .callDB(env, APPLICATION_BANK_SCRAPING_QUERY, statement -> {
          statement.setString(1, customerUUID);
          return tryGetResult(env, customerUUID, statement, "bankScrapingReference",
              "Successfully selected bankScrapingReference : {} for customer_ID: {} on environment: {}",
              "Select failed! no bankScrapingReference for customer_ID: ");
        });
  }

  public static String getCustomerUUID(final String env,
      final String customerID) {

    LOG.debug("Select UUID for customer_ID: {} on environment: {}", customerID, env);

    return DBServiceProvider.aioDBService()
        .callDB(env, CUSTOMER_UUID_QUERY, statement -> {
          statement.setString(1, customerID);
          return tryGetResult(env, customerID, statement, "UUID",
              "Successfully selected UUID : {} for customer_ID: {} on environment: {}",
              "Select failed! no UUID for customer_ID: ");
        });
  }

  public static String getScoringState(final String env, final String customerID) {

    LOG.debug("Select ScoringState for customer_ID: {} on environment: {}", customerID, env);
    return DBServiceProvider.aioDBService()
        .callDB(env, SCORING_STATE_QUERY_AU, statement -> {
          statement.setString(1, customerID);
          return tryGetResult(env, customerID, statement, "ScoringState",
              "Successfully selected ScoringState: {} for customer_ID: {} on environment: {}",
              "Select failed! no ScoringState for customer_ID: ");
        });
  }

  public static String getNegativeScoreMessage(final String env, final String customerID) {

    LOG.debug("Select DEFS negative reason message for customer_ID: {} on environment: {}",
        customerID, env);
    return DBServiceProvider.aioDBService()
        .callDB(env, DEFS_NEGATIVE_SCORE_MESSAGE_AU, statement -> {
          statement.setString(1, customerID);
          return tryGetResult(env, customerID, statement, "message",
              "Successfully selected message: {} for customer_ID: {} on environment: {}",
              "Select failed! no message for customer_ID: ");
        });
  }

  public static boolean checkEmailConfirmation(final String env, final String customerID) {

    LOG.debug("Check email confirmation for customer_ID: {} on environment: {}", customerID, env);

    return DBServiceProvider.aioDBService()
        .callDB(env, EMAIL_VALIDATION_STATE_QUERY, statement -> {
          statement.setString(1, customerID);
          return checkEmailConfirmationResult(env, customerID, statement);
        });
  }

  public static boolean updateEmailVerification(final String env, final String email) {

    LOG.debug(
        "Update EmailVerification state to 'VERIFIED' for Customer with e-mail: {} on environment: {}",
        email, env);
    return DBServiceProvider.aioDBService()
        .callDB(env, EMAIL_VERIFICATION_STSTE_UPDATE, statement -> {
          statement.setString(1, email);
          if (statement.executeUpdate() != 0) {
            return true;
          } else {
            throw new IllegalStateException(
                "Update e-mail (" + email + ") verification state failed!");
          }
        });
  }

  public static boolean updateIdentificationStatus(final String env, final String customerID) {

    LOG.debug("Update identificationStatus to 'IDENTIFIED' for customer_ID: {} on environment: {}",
        customerID, env);

    return DBServiceProvider.aioDBService()
        .callDB(env, IDENTIFICATION_STATUS_UPDATE, statement -> {
          statement.setString(1, customerID);
          if (statement.executeUpdate() != 0) {
            return true;
          }
          throw new IllegalStateException(
              "Update identificationStatus failed! for Customer_ID:" + customerID);

        });
  }

  public static void updateCustomerEmail(final String env, final String email,
      final String customerID) {

    LOG.debug("Set Email {} for Customer with ID: {} on environment: {}", email, customerID, env);

    DBServiceProvider.aioDBService()
        .callDB(env, EMAIL_VERIFICATION_CUSTOMER_UPDATE, (StatementHandler<Void>) statement -> {
          statement.setString(1, email);
          statement.setString(2, customerID);
          if (statement.executeUpdate() != 1) {
            throw new IllegalStateException(
                "Set e-mail (" + email + ") for Customer " + customerID + " failed!");
          }
          return null;
        });
  }

  public static boolean checkFreeEmail(final String env, final String email) {

    LOG.debug("Check the availability of the email: {} on environment: {}", email, env);


    return DBServiceProvider.aioDBService()
        .callDB(env, USED_EMAIL_QUERY, statement -> {
          statement.setString(1, email);
          return checkFreeEmailResult(statement);
        });
  }

  public static boolean checkFreeSsn(final String env, final String ssn) {

    LOG.debug("Check the availability of the SSN: {} on environment: {}", ssn, env);

    return DBServiceProvider.aioDBService()
        .callDB(env, USED_IDENTIFIER_QUERY, statement -> {
          statement.setString(1, ssn);
          return checkFreeEmailResult(statement);
        });
  }


  public static Integer getInvoiceAmount(final String env, final String ssn) {

    LOG.debug("Get invoice amount for Customer ssn: {} on environment: {}", ssn, env);
    return DBServiceProvider.aioDBService()
        .callDB(env, INVOICE_AMOUNT_QUERY, statement -> {
          statement.setString(1, ssn);
          return getInvoiceAmountResult(ssn, statement);
        });
  }

  // start - Temporary solution until the BEE becomes available
  private static boolean getFreePassportNumber(final String env, final String identifier) {

    LOG.debug("Check the availability of the passport number: {} on environment: {}", identifier,
        env);

    return DBServiceProvider.aioDBService()
        .callDB(env, USED_IDENTIFIER_QUERY, statement -> {
          statement.setString(1, identifier);
          return getFreePassportNumberResult(statement);
        });
  }

  // TODO: delete me - it's not used along projects (checked at and generator)
  public static String setPassportAU(final String env, Long passportMinRange,
      final Long passportMaxRange,
      final String passportSeriesLetter) {

    String fullPassportNumber = passportSeriesLetter + passportMinRange;
    while (getFreePassportNumber(env, fullPassportNumber)) {
      LOG.warn("PASSPORT {} is present in Customer table! Generating a new data set...",
          fullPassportNumber);
      if (passportMinRange < passportMaxRange) {
        passportMinRange++;
        fullPassportNumber = passportSeriesLetter + passportMinRange;
      } else {
        throw new IllegalStateException(
            "No free passport for range: " + passportMinRange + " - " + passportMaxRange);
      }
    }
    return fullPassportNumber;
  }
  // end - Temporary solution until the BEE becomes available

  private static String tryGetResult(
      final String env, final String customerID, final PreparedStatement statement,
      final String columnName, final String successText, final String exceptionText)
      throws SQLException {
    try (final ResultSet result = statement.executeQuery()) {
      if (result.next()) {
        final String searchedValue = result.getString(columnName);
        LOG.debug(successText, searchedValue, customerID, env);
        return searchedValue;
      } else {
        throw new IllegalStateException(exceptionText + customerID);
      }
    }
  }

  public static boolean checkUpgradeAvailable(final String env, final Long customerId) {

    LOG.debug("Check Upgrade/Upsell available for Customer ID: {} on environment: {}", customerId,
        env);

    return DBServiceProvider.aioDBService()
        .callDB(env, LIMIT_INCREASE_QUERY, statement -> {
          statement.setLong(1, customerId);
          return checkUpgradeAvailableResult(statement);
        });
  }

  private static boolean checkUpgradeAvailableResult(final PreparedStatement statement)
      throws SQLException {
    try (final ResultSet result = statement.executeQuery()) {
      if (result.next()) {
        final long count = result.getLong(1);
        LOG.debug(FOUND, count);
        return count == 1;
      } else {
        throw new IllegalStateException("Can't check ScoringResultContainer");
      }
    }
  }

  private static boolean getFreePassportNumberResult(final PreparedStatement statement)
      throws SQLException {
    try (final ResultSet result = statement.executeQuery()) {
      if (result.next()) {
        final int count = result.getInt(1);
        LOG.debug(FOUND, count);
        return count != 0;
      } else {
        throw new IllegalStateException("There are no more numbers to check");
      }
    }
  }

  private static Integer getInvoiceAmountResult(final String ssn, final PreparedStatement statement)
      throws SQLException {
    try (final ResultSet result = statement.executeQuery()) {
      if (result.next()) {
        final Integer amount = result.getInt(1);
        LOG.debug("Found amount {} for Customer ssn {}", amount, ssn);
        return amount;
      } else {
        throw new IllegalStateException(
            String.format("There are no unpaid invoices for Customer ssn %s", ssn));
      }
    }
  }

  private static boolean checkEmailConfirmationResult(final String env, final String customerID,
      final PreparedStatement statement) throws SQLException {
    try (final ResultSet result = statement.executeQuery()) {
      if (result.next()) {
        final String state = result.getString("state");
        LOG.debug("Successfully selected state: {} for customer_ID: {} on environment: {}", state,
            customerID, env);
        return state.equals("VERIFIED");
      } else {
        throw new IllegalStateException("Select failed! no data for customer_ID: " + customerID);
      }
    }
  }

  private static boolean checkFreeEmailResult(final PreparedStatement statement)
      throws SQLException {
    try (final ResultSet result = statement.executeQuery()) {
      if (result.next()) {
        final int count = result.getInt(1);
        LOG.debug(FOUND, count);
        return count == 0;
      } else {
        throw new IllegalStateException("Can't check existing email addresses");
      }
    }
  }

  public static Map<String, Integer> getReplicationQueue(final String env) {

    LOG.debug("Find replication queue on environment: {}", env);

    return DBServiceProvider.aioDBService()
        .callDB(env, REPLICATION_QUEUE_QUERY, DatabaseQuery::buildReplicationQueueMap);
  }

  private static Map<String, Integer> buildReplicationQueueMap(final PreparedStatement statement)
      throws SQLException {
    final Map<String, Integer> resultMap = new HashMap<>();
    try (final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        resultMap.put(resultSet.getString(COLUMN_NAME_INDEX), resultSet.getInt(COLUMN_COUNT_INDEX));
      }
    }
    return resultMap;
  }
}
