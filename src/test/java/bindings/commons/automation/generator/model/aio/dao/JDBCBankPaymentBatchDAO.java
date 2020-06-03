package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class JDBCBankPaymentBatchDAO implements BankPaymentBatchDAO {

  private static final Logger LOG = LogManager.getLogger(JDBCBankPaymentBatchDAO.class);

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE BankPaymentBatch bpb " +
      "JOIN BankPayment bp ON bp.batch_id = bpb.id " +
      "JOIN Allocation a ON a.payment_id = bp.id " +
      "JOIN CashFlow cf ON cf.ID = a.cashFlow_ID " +
      "JOIN Contract c ON c.ID = cf.contract_ID " +
      "SET bpb.created = (DATE_ADD(bpb.created, INTERVAL ? DAY)), " +
      "bpb.entityVersion = bpb.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE BankPaymentBatch bpb " +
      "JOIN BankPayment bp ON bp.batch_id = bpb.id " +
      "JOIN Allocation a ON a.payment_id = bp.id " +
      "JOIN CashFlow cf ON cf.ID = a.cashFlow_ID " +
      "JOIN Contract c ON c.ID = cf.contract_ID " +
      "SET bpb.created = (DATE_SUB(bpb.created, INTERVAL ? DAY)), " +
      "bpb.entityVersion = bpb.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting BankPaymentBatch dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting BankPaymentBatch dates on {} {} days backward for customerID: {}", env, days,
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
          LOG.info("Updated {} rows in BankPaymentBatch table.", rows);
          return rows;
        });
  }
}
