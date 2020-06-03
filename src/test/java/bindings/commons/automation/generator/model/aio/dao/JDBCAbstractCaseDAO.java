package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class JDBCAbstractCaseDAO implements AbstractCaseDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCAbstractCaseDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY_CREDIT_APP_CASE =
      "UPDATE AbstractCase ac " +
          "JOIN CreditApplicationCase cac ON cac.ID=ac.ID " +
          "JOIN Contract c ON c.creditApplication_ID=cac.creditApplication_ID " +
          "SET ac.openStamp = (DATE_ADD(ac.openStamp, INTERVAL ? DAY)), " +
          "ac.closeStamp = (DATE_ADD(ac.closeStamp, INTERVAL ? DAY)), " +
          "ac.entityVersion = ac.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY_CREDIT_APP_CASE =
      "UPDATE AbstractCase ac " +
          "JOIN CreditApplicationCase cac ON cac.ID=ac.ID " +
          "JOIN Contract c ON c.creditApplication_ID=cac.creditApplication_ID " +
          "SET ac.openStamp = (DATE_SUB(ac.openStamp, INTERVAL ? DAY)), " +
          "ac.closeStamp = (DATE_SUB(ac.closeStamp, INTERVAL ? DAY)), " +
          "ac.entityVersion = ac.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY_REFINANCE_DRAWDOWN_CASE =
      "UPDATE AbstractCase ac " +
          "JOIN RefinanceDrawdownCase rdc ON rdc.ID = ac.ID " +
          "JOIN Contract c ON c.ID = rdc.contract_ID " +
          "SET ac.openStamp = (DATE_ADD(ac.openStamp, INTERVAL ? DAY)), " +
          "ac.closeStamp = (DATE_ADD(ac.closeStamp, INTERVAL ? DAY)), " +
          "ac.entityVersion = ac.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY_REFINANCE_DRAWDOWN_CASE =
      "UPDATE AbstractCase ac " +
          "JOIN RefinanceDrawdownCase rdc ON rdc.ID = ac.ID " +
          "JOIN Contract c ON c.ID = rdc.contract_ID " +
          "SET ac.openStamp = (DATE_SUB(ac.openStamp, INTERVAL ? DAY)), " +
          "ac.closeStamp = (DATE_SUB(ac.closeStamp, INTERVAL ? DAY)), " +
          "ac.entityVersion = ac.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY_NEW_DRAWDOWN_CASE =
      "UPDATE AbstractCase ac " +
          "JOIN NewDrawdownCase ndc ON ndc.ID = ac.ID " +
          "JOIN Contract c ON c.ID = ndc.contract_ID " +
          "SET ac.openStamp = (DATE_ADD(ac.openStamp, INTERVAL ? DAY)), " +
          "ac.closeStamp = (DATE_ADD(ac.closeStamp, INTERVAL ? DAY)), " +
          "ac.entityVersion = ac.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY_NEW_DRAWDOWN_CASE =
      "UPDATE AbstractCase ac " +
          "JOIN NewDrawdownCase ndc ON ndc.ID = ac.ID " +
          "JOIN Contract c ON c.ID = ndc.contract_ID " +
          "SET ac.openStamp = (DATE_SUB(ac.openStamp, INTERVAL ? DAY)), " +
          "ac.closeStamp = (DATE_SUB(ac.closeStamp, INTERVAL ? DAY)), " +
          "ac.entityVersion = ac.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOGGER.info("Shifting AbstractCase dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY_CREDIT_APP_CASE);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY_REFINANCE_DRAWDOWN_CASE);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY_NEW_DRAWDOWN_CASE);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOGGER.info("Shifting AbstractCase dates on {} {} days backward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY_CREDIT_APP_CASE);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY_REFINANCE_DRAWDOWN_CASE);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY_NEW_DRAWDOWN_CASE);
  }

  private void updateDates(
      final String env, final Integer days, final Long customerId, final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setLong(3, customerId);
          final int rows = statement.executeUpdate();
          LOGGER.info("Updated {} rows in AbstractCase table.", rows);
          return rows;
        });
  }
}
