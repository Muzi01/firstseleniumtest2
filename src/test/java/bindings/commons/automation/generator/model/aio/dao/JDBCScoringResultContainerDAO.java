package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCScoringResultContainerDAO implements ScoringResultContainerDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCScoringResultContainerDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE ScoringResultContainer src " +
      "JOIN Customer cust ON cust.ID=src.customer_ID " +
      "SET src.created = (DATE_ADD(src.created, INTERVAL ? DAY)), " +
      "src.lastScored = (DATE_ADD(src.lastScored, INTERVAL ? DAY)), " +
      "src.updated = (DATE_ADD(src.updated, INTERVAL ? DAY)), " +
      "src.entityVersion = src.entityVersion+1 " +
      "WHERE cust.ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE ScoringResultContainer src " +
      "JOIN Customer cust ON cust.ID=src.customer_ID " +
      "SET src.created = (DATE_SUB(src.created, INTERVAL ? DAY)), " +
      "src.lastScored = (DATE_SUB(src.lastScored, INTERVAL ? DAY)), " +
      "src.updated = (DATE_SUB(src.updated, INTERVAL ? DAY)), " +
      "src.entityVersion = src.entityVersion+1 " +
      "WHERE cust.ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting ScoringResultContainer dates on {} {} days forward for customerID: {}",
        env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting ScoringResultContainer dates on {} {} days backward for customerID: {}",
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
          statement.setInt(3, days);
          statement.setLong(4, customerId);
          LOGGER.info("Updated {} rows in ScoringResultContainer table.",
              statement.executeUpdate());
          return null;
        });
  }
}
