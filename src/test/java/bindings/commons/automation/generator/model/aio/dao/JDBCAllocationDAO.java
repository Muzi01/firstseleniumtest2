package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class JDBCAllocationDAO implements AllocationDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCAllocationDAO.class);

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE Allocation a " +
      "JOIN CashFlow cf ON cf.ID = a.cashFlow_ID " +
      "JOIN Contract c ON c.ID = cf.contract_ID " +
      "SET a.created = (DATE_ADD(a.created, INTERVAL ? DAY)), " +
      "a.entityVersion = a.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE Allocation a " +
      "JOIN CashFlow cf ON cf.ID = a.cashFlow_ID " +
      "JOIN Contract c ON c.ID = cf.contract_ID " +
      "SET a.created = (DATE_SUB(a.created, INTERVAL ? DAY)), " +
      "a.entityVersion = a.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOGGER.info("Shifting Allocation dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOGGER.info("Shifting Allocation dates on {} {} days backward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(
      final String env, final Integer days, final Long customerId, final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setLong(2, customerId);
          final int rows = statement.executeUpdate();
          LOGGER.info("Updated {} rows in Allocation table.", rows);
          return rows;
        });
  }
}
