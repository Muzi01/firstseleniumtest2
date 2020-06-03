package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.CashFlow;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCCashFlowDAO implements CashFlowDAO {
  private static final Logger LOG = LogManager.getLogger(CashFlow.class);
  private static final String BASE_UPDATE_SQL =
      "UPDATE CashFlow f JOIN Contract c ON f.contract_ID = c.ID ";

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = BASE_UPDATE_SQL +
      "SET f.valueDate = (DATE_ADD(f.valueDate, INTERVAL ? DAY)), " +
      "f.created = (DATE_ADD(f.created, INTERVAL ? DAY)), " +
      "f.entityVersion = f.entityVersion+1 WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = BASE_UPDATE_SQL +
      "SET f.valueDate = (DATE_SUB(f.valueDate, INTERVAL ? DAY)), " +
      "f.created = (DATE_SUB(f.created, INTERVAL ? DAY)), " +
      "f.entityVersion = f.entityVersion+1 WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_VALUE_DATE_BACK_QUERY = BASE_UPDATE_SQL +
      "SET f.valueDate = (DATE_SUB(f.valueDate, INTERVAL ? DAY)) WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting CashFlow dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting CashFlow dates on {} {} days backward for customerID: {}", env, days,
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
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in CashFlow table.", rows);
          return rows;
        });

  }

  @Override
  public void updateCashFlowValueDateBack(final String env, final Integer days,
      final Long customerId) {

    DBServiceProvider.aioDBService()
        .callDB(env, SQL_SHIFT_VALUE_DATE_BACK_QUERY, statement -> {
          statement.setInt(1, days);
          statement.setLong(2, customerId);
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in CashFlow table.", rows);
          return rows;
        });
  }

}
