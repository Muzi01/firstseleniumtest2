package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCContractTerminationNoticeDAO implements ContractTerminationNoticeDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCContractTerminationNoticeDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE ContractTerminationNotice ctn " +
          "JOIN Contract c ON c.ID=ctn.contract_ID " +
          "SET ctn.created = (DATE_ADD(ctn.created, INTERVAL ? day)), " +
          "ctn.updated = (DATE_ADD(ctn.updated, INTERVAL ? day)), " +
          "ctn.entityVersion=ctn.entityVersion+1 " +
          "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE ContractTerminationNotice ctn " +
      "JOIN Contract c ON c.ID=ctn.contract_ID " +
      "SET ctn.created = (DATE_SUB(ctn.created, INTERVAL ? day)), " +
      "ctn.updated = (DATE_SUB(ctn.updated, INTERVAL ? day)), " +
      "ctn.entityVersion=ctn.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting ContractTerminationNotice dates on {} {} days forward for customerID: {}",
        env, days, customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting ContractTerminationNotice dates on {} {} days backward for customerID: {}",
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
          LOG.info("Updated {} rows in ContractTerminationNotice table.", rows);
          return rows;
        });
  }
}
