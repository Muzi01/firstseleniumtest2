package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class JDBCInvoicingParamsDAO implements InvoicingParamsDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCInvoicingParamsDAO.class);
  private static final String SQL_GET_NEXT_DUE_DATE_QUERY =
      "SELECT i.nextDueDate FROM InvoicingParams i " +
          "JOIN Contract co ON co.ID = i.contract_ID " +
          "JOIN Customer cu ON cu.ID = co.customer_ID " +
          "WHERE cu.identifier = ? ORDER BY i.nextDueDate DESC LIMIT 1";
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE InvoicingParams i " +
      "JOIN Contract c ON i.contract_ID = c.ID " +
      "SET i.created = (DATE_ADD(i.created, INTERVAL ? DAY)), " +
      "i.closed = (DATE_ADD(i.closed, INTERVAL ? DAY)), " +
      "i.firstDueDate = (DATE_ADD(i.firstDueDate, INTERVAL ? DAY)), " +
      "i.nextDueDate = (DATE_ADD(i.nextDueDate, INTERVAL ? DAY)), " +
      "i.entityVersion = i.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE InvoicingParams i " +
      "JOIN Contract c ON i.contract_ID = c.ID " +
      "SET i.created = (DATE_SUB(i.created, INTERVAL ? DAY)), " +
      "i.closed = (DATE_SUB(i.closed, INTERVAL ? DAY)), " +
      "i.firstDueDate = (DATE_SUB(i.firstDueDate, INTERVAL ? DAY)), " +
      "i.nextDueDate = (DATE_SUB(i.nextDueDate, INTERVAL ? DAY)), " +
      "i.entityVersion = i.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  private static final String SQL_GET_LATEST_MMP_VALUE = "SELECT ip.mmp FROM InvoicingParams ip " +
      "JOIN Contract c ON ip.contract_id = c.id " +
      "JOIN Customer cus ON c.customer_id = cus.id " +
      "WHERE cus.id = ? ORDER BY ip.id DESC";
  private static final String SQL_GET_INVOICES_PARAMS_DATA_BY_CUSTOMER_ID_QUERY =
      "SELECT i.* FROM InvoicingParams i " +
          "JOIN Contract co ON co.ID = i.contract_ID " +
          "JOIN Customer cu ON cu.ID = co.customer_ID " +
          "WHERE cu.id = ? ORDER BY i.ID DESC";


  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting InvoicingParams dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting InvoicingParams dates on {} {} days backward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(final String env, final Integer days, final Long customerId,
      final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setInt(3, days);
          statement.setInt(4, days);
          statement.setLong(5, customerId);
          LOG.info("Updated {} rows in InvoicingParams table.", statement.executeUpdate());
          return null;
        });
  }

  @Override
  public int getLatestMmp(final String env, final Long customerId) {

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_LATEST_MMP_VALUE, statement -> {
          statement.setLong(1, customerId);
          try (final ResultSet result = statement.executeQuery()) {
            if (result.next()) {
              LOG.info("Latest mmp for customerId {} is: {}", customerId, result.getInt(1));
              return result.getInt(1);
            }
            LOG.info("Latest mmp for customerId {} is: {}", customerId, 0);
            return 0;
          }
        });
  }

  @Override
  public LocalDate findNextDueDate(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_NEXT_DUE_DATE_QUERY, statement -> {
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultSet.getDate("nextDueDate").toLocalDate();
            }
            throw new IllegalStateException(
                "nextDueDate not found for customer: " + customer.identifier);
          }
        });
  }

  @Override
  public List<InvoicingParams> getInvoicingParamsListByCustomerId(final String env,
      final Long customerId) {

    LOG.info("Searching for InvoicingParams on env: {} for customerId: {}", env, customerId);

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_INVOICES_PARAMS_DATA_BY_CUSTOMER_ID_QUERY,
            statement -> getInvoicingParamsListByCustomerId(statement, customerId));
  }

  private List<InvoicingParams> getInvoicingParamsListByCustomerId(
      final PreparedStatement statement,
      final Long customerId)
      throws SQLException {
    final List<InvoicingParams> invoicingParamsList = new ArrayList<>();
    statement.setLong(1, customerId);
    statement.setQueryTimeout(60);

    try (final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        invoicingParamsList.add(new InvoicingParamsMapper().mapByResultSet(resultSet));
      }
    }

    return invoicingParamsList;
  }

  @Override
  public InvoicingParams getByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for InvoicingParams on env: {} for customerId: {}", env, customerId);
    return new JDBCUtils<InvoicingParams>().getByCustomerId(
        SQL_GET_INVOICES_PARAMS_DATA_BY_CUSTOMER_ID_QUERY, env, customerId,
        new InvoicingParamsMapper());
  }
}
