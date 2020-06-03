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
import java.util.LinkedList;
import java.util.List;

class JDBCInvoiceDAO implements InvoiceDAO {

  private static final Logger LOG = LogManager.getLogger(JDBCInvoiceDAO.class);

  // NOSONAR
  private static final String SQL_GET_INVOICES_DATA_BY_CUSTOMER_ID_QUERY =
      "SELECT i.* FROM Invoice i " +
          "JOIN Contract co ON co.ID = i.contract_ID " +
          "JOIN Customer cu ON cu.ID = co.customer_ID " +
          "WHERE cu.id = ? ORDER BY i.ID DESC";

  // NOSONAR
  private static final String SQL_GET_INVOICE_DATA_BY_CUSTOMER_ID_DESC_QUERY =
      "SELECT i.* FROM Invoice i " +
          "JOIN Contract co ON co.ID = i.contract_ID " +
          "JOIN Customer cu ON cu.ID = co.customer_ID " +
          "WHERE cu.id = ? ORDER BY i.ID DESC LIMIT 1";

  // NOSONAR
  private static final String SQL_GET_INVOICE_DATA_BY_CUSTOMER_ID_ASC_QUERY =
      "SELECT i.* FROM Invoice i " +
          "JOIN Contract co ON co.ID = i.contract_ID " +
          "JOIN Customer cu ON cu.ID = co.customer_ID " +
          "WHERE cu.id = ? ORDER BY i.ID ASC LIMIT 1";
  private static final String SQL_GET_INVOICE_INFO_QUERY =
      "SELECT i.ID, i.amount, i.invoiceDate, i.due_date, i.closed, i.stateType, i.invoice_type FROM Invoice i "
          +
          "JOIN Contract co ON co.ID = i.contract_ID " +
          "JOIN Customer cu ON cu.ID = co.customer_ID " +
          "WHERE cu.identifier = ? ORDER BY i.ID DESC";

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE Invoice i " +
      "JOIN Contract c ON i.contract_ID=c.ID " +
      "SET i.created=(DATE_ADD(i.created, INTERVAL ? DAY)), " +
      "i.invoiceDate=(DATE_ADD(i.invoiceDate, INTERVAL ? DAY)), " +
      "i.due_date=(DATE_ADD(i.due_date, INTERVAL ? DAY)), " +
      "i.closed=(DATE_ADD(i.closed, INTERVAL ? DAY)), " +
      "i.entityVersion = i.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE Invoice i " +
      "JOIN Contract c ON i.contract_ID=c.ID " +
      "SET i.created=(DATE_SUB(i.created, INTERVAL ? DAY)), " +
      "i.invoiceDate=(DATE_SUB(i.invoiceDate, INTERVAL ? DAY)), " +
      "i.due_date=(DATE_SUB(i.due_date, INTERVAL ? DAY)), " +
      "i.closed=(DATE_SUB(i.closed, INTERVAL ? DAY)), " +
      "i.entityVersion = i.entityVersion+1 " +
      "WHERE c.customer_ID = ?";
  private static final String CLOSED = "closed";
  private static final String AMOUNT = "amount";

  @Override
  public LocalDate lastInvoiceDueDate(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultSet.getDate("due_date").toLocalDate();
            }
            throw new IllegalStateException(
                "Invoice.due_date not found for customer: " + customer.identifier);
          }
        });
  }

  @Override
  public LocalDate lastInvoiceDate(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultSet.getDate("invoiceDate").toLocalDate();
            }
            throw new IllegalStateException(
                "Invoice.invoiceDate not found for customer: " + customer.identifier);
          }
        });
  }

  @Override
  public List<String> getInvoiceStateTypeDESC(final Environment environment,
      final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          statement.setString(1, customer.identifier);
          final List<String> listOfStateTypes = new ArrayList<>();
          try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              listOfStateTypes.add(resultSet.getString("stateType"));
            }
          }
          return listOfStateTypes;
        });
  }

  @Override
  public List<String> getInvoiceTypeDESC(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          final List<String> listOfTypes = new LinkedList<>();
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              listOfTypes.add(resultSet.getString("invoice_type"));
            }
          }
          return listOfTypes;
        });
  }

  @Override
  public List<String> getInvoiceIdsDESC(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {

          final List<String> stateTypes = new ArrayList<>();
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              stateTypes.add(resultSet.getString("ID"));
            }
          }
          return stateTypes;
        });
  }

  @Override
  public boolean isLastInvoiceClosed(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultSet.getDate(CLOSED) != null;
            }
            throw new IllegalStateException(
                "Invoice not found for customer: " + customer.identifier);
          }
        });
  }

  @Override
  public LocalDate lastInvoiceClosedDate(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultSet.getDate(CLOSED).toLocalDate();
            }
            throw new IllegalStateException(
                "Invoice not found for customer: " + customer.identifier);
          }
        });
  }

  @Override
  public Integer lastInvoicesCombinedAmount(final int invoices, final Environment environment,
      final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          int result = 0;
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            int i = 0;
            while (resultSet.next() && i < invoices) {
              result += resultSet.getInt(AMOUNT);
              i++;
            }
          }
          return result;
        });
  }

  @Override
  public Integer lastInvoiceAmount(final Environment environment, final Customer customer) {

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_INVOICE_INFO_QUERY, statement -> {
          statement.setString(1, customer.identifier);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultSet.getInt(AMOUNT);

            }
            throw new IllegalStateException(
                "Invoice.amount not found for customer: " + customer.identifier);
          }
        });
  }

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting Invoice dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting Invoice dates on {} {} days backward for customerID: {}", env, days,
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
          LOG.info("Updated {} rows in Invoice table.", statement.executeUpdate());
          return null;
        });
  }

  @Override
  public List<Invoice> getInvoiceListByCustomerId(final String env, final Long customerId) {

    LOG.info("Searching for Invoices on env: {} for customerId: {}", env, customerId);

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_INVOICES_DATA_BY_CUSTOMER_ID_QUERY,
            statement -> getInvoiceListByCustomerId(statement, customerId));
  }

  private List<Invoice> getInvoiceListByCustomerId(final PreparedStatement statement,
      final Long customerId)
      throws SQLException {
    final List<Invoice> invoicesList = new ArrayList<>();
    statement.setLong(1, customerId);
    statement.setQueryTimeout(60);

    try (final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        invoicesList.add(new InvoiceMapper().mapByResultSet(resultSet));
      }
    }

    return invoicesList;
  }

  @Override
  public Invoice getLastInvoiceByCustomerId(final String env, final Long customerId) {
    return new JDBCUtils<Invoice>().getByCustomerId(SQL_GET_INVOICE_DATA_BY_CUSTOMER_ID_DESC_QUERY,
        env, customerId,
        new InvoiceMapper());
  }

  @Override
  public Invoice getOldestInvoiceByCustomerId(final String env, final Long customerId) {
    return new JDBCUtils<Invoice>().getByCustomerId(SQL_GET_INVOICE_DATA_BY_CUSTOMER_ID_ASC_QUERY,
        env, customerId,
        new InvoiceMapper());
  }


  @Override
  public Invoice getByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for Invoice on env: {} for customerId: {}", env, customerId);
    return new JDBCUtils<Invoice>().getByCustomerId(SQL_GET_INVOICES_DATA_BY_CUSTOMER_ID_QUERY, env,
        customerId,
        new InvoiceMapper());
  }
}
