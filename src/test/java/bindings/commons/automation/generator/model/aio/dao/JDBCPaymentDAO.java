package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCPaymentDAO implements PaymentDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCPaymentDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE Payment p " +
      "JOIN Allocation a ON a.payment_id = p.id " +
      "JOIN CashFlow cf ON cf.ID = a.cashFlow_ID " +
      "JOIN Contract c ON c.ID = cf.contract_ID " +
      "SET p.paymentDate = (DATE_ADD(p.paymentDate, INTERVAL ? DAY)), " +
      "p.valueDate = (DATE_ADD(p.valueDate, INTERVAL ? DAY)), " +
      "p.created = (DATE_ADD(p.created, INTERVAL ? DAY)), " +
      "p.entityVersion = p.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE Payment p " +
      "JOIN Allocation a ON a.payment_id = p.id " +
      "JOIN CashFlow cf ON cf.ID = a.cashFlow_ID " +
      "JOIN Contract c ON c.ID = cf.contract_ID " +
      "SET p.paymentDate = (DATE_SUB(p.paymentDate, INTERVAL ? DAY)), " +
      "p.valueDate = (DATE_SUB(p.valueDate, INTERVAL ? DAY)), " +
      "p.created = (DATE_SUB(p.created, INTERVAL ? DAY)), " +
      "p.entityVersion = p.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting Payment dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting Payment dates on {} {} days backward for customerID: {}", env, days,
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
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in Payment table.", rows);
          return rows;
        });
  }
}
