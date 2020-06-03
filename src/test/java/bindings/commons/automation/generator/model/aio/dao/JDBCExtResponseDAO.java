package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCExtResponseDAO implements ExtResponseDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCExtResponseDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE ext_response ex " +
      "JOIN Customer cu ON cu.identifier = ex.identifier " +
      "SET ex.created = (DATE_ADD(ex.created, INTERVAL ? DAY)), " +
      "ex.validUntil = (DATE_ADD(ex.validUntil, INTERVAL ? DAY)), " +
      "ex.entityVersion = ex.entityVersion+1 " +
      "WHERE and cu.ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE ext_response ex " +
      "JOIN Customer cu ON cu.identifier = ex.identifier " +
      "SET ex.created = (DATE_SUB(ex.created, INTERVAL ? DAY)), " +
      "ex.validUntil = (DATE_SUB(ex.validUntil, INTERVAL ? DAY)), " +
      "ex.entityVersion = ex.entityVersion+1 " +
      "WHERE cu.ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting ext_response dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting ext_response dates on {} {} days backward for customerID: {}", env, days,
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
          LOGGER.info("Updated {} rows in ext_response table.", statement.executeUpdate());
          return null;
        });
  }
}
