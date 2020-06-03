package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Calendar;

public class JDBCUserRegistrationRequestDAO implements UserRegistrationRequestDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCUserRegistrationRequestDAO.class);

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE UserRegistrationRequest urr " +
          "SET urr.closed = (DATE_ADD(urr.closed, INTERVAL ? DAY)), " +
          "urr.created = (DATE_ADD(urr.created, INTERVAL ? DAY)), " +
          "urr.entityVersion = urr.entityVersion+1 " +
          "WHERE urr.customer_ID = ? ";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE UserRegistrationRequest urr " +
      "SET urr.closed = (DATE_SUB(urr.closed, INTERVAL ? DAY)), " +
      "urr.created = (DATE_SUB(urr.created, INTERVAL ? DAY)), " +
      "urr.entityVersion = urr.entityVersion+1 " +
      "WHERE urr.customer_ID = ? ";
  private static final String SQL_VERIFY_IDENTIFICATION_QUERY = "UPDATE UserRegistrationRequest " +
      "SET state = 'VERIFIED', " +
      "closed = ?, " +
      "identificationStatus = 'IDENTIFIED', " +
      "previousIdentificationStatus = 'PENDING_IDENTIFICATION' " +
      "WHERE identifier = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting UserRegistrationRequest dates on {} {} days forward for customerID: {}", env,
        days, customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting UserRegistrationRequest dates on {} {} days backward for customerID: {}",
        env, days, customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(
      final String env, final Integer days, final Long customerId, final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setLong(3, customerId);
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in UserRegistrationRequest table.", rows);
          return rows;
        });
  }

  /**
   * Verifies the identification of UserRegistrationRequest for the chosen ssn.
   *
   * @param env environment name, such as "st01", "uat
   * @param identifier customer ssn
   */
  @Override
  public void verifyIdentification(final String env, final String identifier) {

    DBServiceProvider.aioDBService()
        .callDB(env, SQL_VERIFY_IDENTIFICATION_QUERY, statement -> {
          final Calendar calendar = Calendar.getInstance();
          Calendar.getInstance().add(Calendar.DATE, -1);
          statement.setTimestamp(1, new Timestamp(calendar.getTimeInMillis()));
          statement.setString(2, identifier);
          final int rows = statement.executeUpdate();
          identificationStatementLog(rows, identifier);
          return rows;
        });
  }

  private void identificationStatementLog(final int rows, final String identifier) {
    LOG.info("Updated {} rows in UserRegistrationRequest table.", rows);
    if (rows < 1) {
      LOG.warn("UserRegistrationRequest table was not updated, no records matching identifier: {}",
          identifier);
    }
  }
}
