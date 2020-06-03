package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCCreditApplicationExternalDataDAO implements CreditApplicationExternalDataDAO {
  private static final Logger LOG =
      LogManager.getLogger(JDBCCreditApplicationExternalDataDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE CreditApplicationExternalData caed "
          + "JOIN CreditApplication ca ON ca.ID = caed.ID "
          + "SET caed.created = (DATE_ADD(caed.created, INTERVAL ? DAY)), "
          + "caed.update = (DATE_ADD(caed.update, INTERVAL ? DAY)), "
          + "caed.entityVersion = caed.entityVersion+1 "
          + "WHERE ca.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY =
      "UPDATE CreditApplicationExternalData caed "
          + "JOIN CreditApplication ca ON ca.ID = caed.ID "
          + "SET caed.created = (DATE_SUB(caed.created, INTERVAL ? DAY)), "
          + "caed.update = (DATE_SUB(caed.update, INTERVAL ? DAY)), "
          + "caed.entityVersion = caed.entityVersion+1 "
          + "WHERE ca.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info(
        "Shifting CreditApplicationExternalData dates on {} {} days forward for customerID: {}",
        env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info(
        "Shifting CreditApplicationExternalData dates on {} {} days backward for customerID: {}",
        env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(
      final String env, final Integer days, final Long customerId, final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setLong(3, customerId);
          LOG.info("Updated {} rows in CreditApplicationExternalData table.",
              statement.executeUpdate());
          return null;
        });
  }
}
