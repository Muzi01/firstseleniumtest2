package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCCollectionOrderUpdateDAO implements CollectionOrderUpdateDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCCollectionOrderUpdateDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE CollectionOrderUpdate cou " +
      "JOIN CollectionOrder co ON co.ID = cou.Order_ID " +
      "JOIN Invoice i ON i.ID=co.invoice_ID " +
      "JOIN Contract c ON c.ID = i.contract_ID " +
      "SET cou.created = (DATE_ADD(cou.created, INTERVAL ? DAY)), " +
      "cou.updateDate = (DATE_ADD(cou.updateDate, INTERVAL ? DAY)), " +
      "cou.entityVersion = cou.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE CollectionOrderUpdate cou " +
      "JOIN CollectionOrder co ON co.ID = cou.Order_ID " +
      "JOIN Invoice i ON i.ID=co.invoice_ID " +
      "JOIN Contract c ON c.ID = i.contract_ID " +
      "SET cou.created = (DATE_SUB(cou.created, INTERVAL ? DAY)), " +
      "cou.updateDate = (DATE_SUB(cou.updateDate, INTERVAL ? DAY)), " +
      "cou.entityVersion = cou.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting CollectionOrderUpdate dates on {} {} days forward for customerID: {}",
        env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOGGER.info("Shifting CollectionOrderUpdate dates on {} {} days backward for customerID: {}",
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
          LOGGER.info("Updated {} rows in CollectionOrderUpdate table.", statement.executeUpdate());
          return null;
        });
  }
}
