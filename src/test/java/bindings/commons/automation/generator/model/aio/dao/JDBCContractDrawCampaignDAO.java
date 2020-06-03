package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCContractDrawCampaignDAO implements ContractDrawCampaignDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCContractDrawCampaignDAO.class);

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE ContractDrawCampaign cdc " +
          "JOIN Contract con on cdc.contract_ID = con.ID " +
          "SET cdc.validFrom =(DATE_ADD(cdc.validFrom, INTERVAL ? DAY)), " +
          "cdc.validTo =(DATE_ADD(cdc.validTo, INTERVAL ? DAY)), " +
          "cdc.created =(DATE_ADD(cdc.created, INTERVAL ? DAY)), " +
          "cdc.entityVersion = cdc.entityVersion+1 " +
          "WHERE con.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY =
      "UPDATE ContractDrawCampaign cdc " +
          "JOIN Contract con on cdc.contract_ID = con.ID " +
          "SET cdc.validFrom =(DATE_SUB(cdc.validFrom, INTERVAL ? DAY)), " +
          "cdc.validTo =(DATE_SUB(cdc.validTo, INTERVAL ? DAY)), " +
          "cdc.created =(DATE_SUB(cdc.created, INTERVAL ? DAY)), " +
          "cdc.entityVersion = cdc.entityVersion+1 " +
          "WHERE con.customer_ID = ?";


  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting ContractDrawCampaign dates on {} {} days forward for customerID: {}",
        env, days, customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting ContractDrawCampaign dates on {} {} days backward for customerID: {}",
        env, days, customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(final String env, final Integer days, final Long customerId,
      final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setInt(3, days);
          statement.setLong(4, customerId);
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in ContractDrawCampaign table.", rows);
          return rows;
        });
  }
}
