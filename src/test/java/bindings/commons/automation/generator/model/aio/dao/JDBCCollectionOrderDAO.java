package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBService.StatementHandler;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCCollectionOrderDAO implements CollectionOrderDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCCollectionOrderDAO.class);

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE CollectionOrder co " +
      "JOIN Invoice i ON i.ID = co.invoice_ID " +
      "JOIN Contract c ON c.ID = i.contract_ID " +
      "SET co.created = (DATE_ADD(co.created, INTERVAL ? DAY)), " +
      "co.collectionDate = (DATE_ADD(co.collectionDate, INTERVAL ? DAY)), " +
      "co.submitted = (DATE_ADD(co.submitted, INTERVAL ? DAY)), " +
      "co.closed = (DATE_ADD(co.closed, INTERVAL ? DAY)), " +
      "co.entityVersion = co.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE CollectionOrder co " +
      "JOIN Invoice i ON i.ID = co.invoice_ID " +
      "JOIN Contract c ON c.ID = i.contract_ID " +
      "SET co.created = (DATE_SUB(co.created, INTERVAL ? DAY)), " +
      "co.collectionDate = (DATE_SUB(co.collectionDate, INTERVAL ? DAY)), " +
      "co.submitted = (DATE_SUB(co.submitted, INTERVAL ? DAY)), " +
      "co.closed = (DATE_SUB(co.closed, INTERVAL ? DAY)), " +
      "co.entityVersion = co.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting CollectionOrder dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting CollectionOrder dates on {} {} days backward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(
      final String env, final Integer days, final Long customerId, final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, (StatementHandler<Void>) statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setInt(3, days);
          statement.setInt(4, days);
          statement.setLong(5, customerId);
          LOG.info("Updated {} rows in CollectionOrder table.", statement.executeUpdate());
          return null;
        });
  }
}
