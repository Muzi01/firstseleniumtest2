package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCCustomerMetadataDAO implements CustomerMetadataDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCCustomerMetadataDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE Customer_metadata cm " +
      "JOIN Customer cu ON cu.ID = cm.customer_ID " +
      "SET cm.metadata = (DATE_ADD(cm.metadata, INTERVAL ? DAY)) " +
      "WHERE cm.metadata_KEY='freezeFormDate' and cu.ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE Customer_metadata cm " +
      "JOIN Customer cu ON cu.ID = cm.customer_ID " +
      "SET cm.metadata = (DATE_SUB(cm.metadata, INTERVAL ? DAY)) " +
      "WHERE cm.metadata_KEY='freezeFormDate' and cu.ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting Customer_metadata dates on {} {} days forward for customerID: {}", env,
        days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting Customer_metadata dates on {} {} days backward for customerID: {}", env,
        days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(
      final String env, final Integer days, final Long customerId, final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setLong(2, customerId);
          LOGGER.info("Updated {} rows in Customer_metadata table.", statement.executeUpdate());
          return null;
        });
  }
}
