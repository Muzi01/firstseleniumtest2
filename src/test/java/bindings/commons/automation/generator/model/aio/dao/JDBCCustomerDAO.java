package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.database.connection.DBServiceProvider;
import com.ipfdigital.exceptions.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class JDBCCustomerDAO implements CustomerDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCCustomerDAO.class);
  private static final String SQL_GET_CUSTOMER_BASE_QUERY =
      "SELECT c.*, IFNULL(e.email, c.email) as notNullEmail FROM Customer c "
          +
          "LEFT JOIN EmailChangeRequest e ON e.customer_ID=c.ID " +
          "LEFT JOIN Contract co ON co.customer_ID=c.ID " +
          "LEFT JOIN Product p ON co.product_ID=p.ID ";
  private static final String USED_MSISDN_QUERY =
      "SELECT COUNT(ID) FROM Customer WHERE msisdn = ? or msisdn2 = ?";
  private static final String SQL_GET_CUSTOMER_BY_SSN_QUERY =
      SQL_GET_CUSTOMER_BASE_QUERY +
          "WHERE c.identifier = ?";

  private static final String SQL_GET_CUSTOMER_BY_ID_QUERY =
      SQL_GET_CUSTOMER_BASE_QUERY +
          "WHERE c.ID = ?";

  private static final String SQL_GET_CUSTOMER_BY_EMAIL_QUERY =
      SQL_GET_CUSTOMER_BASE_QUERY +
          "WHERE c.email = ?";

  private static final String SQL_GET_CREDIT_APP_FIRST_DRAW_AMOUNT_QUERY =
      "SELECT firstDrawAmount FROM CreditApplication WHERE customer_ID = ? ORDER BY ID DESC LIMIT 1";

  private static final String GET_REGISTERED_CUSTOMER_QUERY =
      "SELECT cu.identifier FROM Customer cu LEFT JOIN Contract co ON co.customer_ID = cu.ID LEFT JOIN Product p ON co.product_ID = p.ID LEFT JOIN EmailChangeRequest e ON e.customer_ID=cu.ID WHERE co.stateType IS null AND cu.country = ? AND cu.onHold = 0 AND e.email LIKE ? AND co.created IS NOT null AND co.ID NOT IN (SELECT i.contract_ID FROM CollectionOrder col JOIN Invoice i ON i.ID=col.invoice_ID) AND p.type = ? AND e.state = ? ORDER BY co.ID DESC LIMIT 10";


  private static final String IS_USED_LAST_NAME_AND_DOB_IN_CUSTOMER_TABLE_QUERY =
      "SELECT COUNT(ID) FROM Customer where lastName = ? and dateOfBirth = ?";

  private static final String UPDATE_PASS_QUERY = "UPDATE Customer " +
      "SET password = ? WHERE Id = ?";


  @Override
  public Customer getCustomerBySsn(final String env, final String ssn) {
    LOG.info("Checking AIO db on {} for customer with ssn: {}", env, ssn);

    return getCustomerByParam(env, ssn, SQL_GET_CUSTOMER_BY_SSN_QUERY);
  }

  @Override
  public Customer getCustomerById(final String env, final String customerId) {
    LOG.info("Checking AIO db on {} for customer with id: {}", env, customerId);

    return getCustomerByParam(env, customerId, SQL_GET_CUSTOMER_BY_ID_QUERY);
  }

  private Customer getCustomerByParam(final String env, final String param,
      final String query) {

    return DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> getCustomerByStatement(statement, param));
  }

  @Override
  public Customer fromAIObyEmail(final String env, final String email) {
    LOG.info("Checking AIO db on {} for customer with email: {}", env, email);
    return getCustomerByParam(env, email, SQL_GET_CUSTOMER_BY_EMAIL_QUERY);
  }

  private Customer getCustomerByStatement(final PreparedStatement statement, final String parameter)
      throws SQLException {
    statement.setString(1, parameter);
    statement.setQueryTimeout(60);
    try (final ResultSet resultSet = statement.executeQuery()) {
      if (resultSet.next()) {
        return new CustomerMapper().mapByResultSet(resultSet);
      } else {
        throw new IllegalStateException("No Customers found with SSN or ID: " + parameter);
      }
    }
  }

  @Override
  public Long findCustomerFirstDrawAmount(final Environment environment, final String customerId) {

    final String firstDrawAmount =
        DBServiceProvider.aioDBService()
            .getQueryResult(environment, SQL_GET_CREDIT_APP_FIRST_DRAW_AMOUNT_QUERY,
                "firstDrawAmount", customerId);
    return Long.valueOf(firstDrawAmount);
  }

  @Override
  public Customer getByCustomerId(final Long customerId, final String env) {

    return new JDBCUtils<Customer>().getByCustomerId(SQL_GET_CUSTOMER_BY_ID_QUERY, env, customerId,
        new CustomerMapper());
  }

  @Override
  public boolean isUsedMsisdn(final Environment env, final String msisdn) {

    final boolean isUsed = !DBServiceProvider.aioDBService()
        .getQueryResult(env, USED_MSISDN_QUERY, "COUNT(ID)", msisdn, msisdn).equals("0");
    if (isUsed) {
      LOG.info("MSISDN : {} is already used", msisdn);
    }
    return isUsed;
  }

  @Override
  public long countByLastNameAndDateOfBirth(final Environment env, final String lastName,
      final LocalDate dateOfBirth) {
    return Long.parseLong(DBServiceProvider.aioDBService().getQueryResult(env,
        IS_USED_LAST_NAME_AND_DOB_IN_CUSTOMER_TABLE_QUERY, "COUNT(ID)", lastName, dateOfBirth));
  }

  @Override
  public void updatePassword(final Environment environment, final String password,
      final String customerId) {
    DBServiceProvider.aioDBService().callDB(environment, UPDATE_PASS_QUERY, statement -> {
      statement.setString(1, password);
      statement.setString(2, customerId);
      if (statement.executeUpdate() == 0) {
        throw new DatabaseConnectionException(
            "Can't update password for customer with id: " + customerId);
      }
      return null;
    });
  }

  @Override
  public List<String> findSsnByVerificationStateAndCountryAndProductType(
      final Environment environment, final String verificationState,
      final String country,
      final String productType) {
    return DBServiceProvider.aioDBService().callDB(environment, GET_REGISTERED_CUSTOMER_QUERY,
        statement -> {
          statement.setString(1, country);
          statement.setString(2, "%@fakemail." + country.toLowerCase());
          statement.setString(3, productType);
          statement.setString(4, verificationState);
          return tryFindResultByStatement(statement, "identifier");
        });
  }

  List<String> tryFindResultByStatement(final PreparedStatement statement,
      final String column) throws SQLException {
    LOG.info("Running AIO DB query: {}", statement);
    final List<String> resultList = new ArrayList<>();
    final ResultSet result = statement.executeQuery();
    while (result.next()) {
      resultList.add(result.getString(column));
    }
    return resultList;
  }
}
