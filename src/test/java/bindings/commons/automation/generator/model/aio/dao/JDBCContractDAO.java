package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Contract;
import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.database.connection.DBService.StatementHandler;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class JDBCContractDAO implements ContractDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCContractDAO.class);
  private static final String DB_CHECK_LOG_FORMAT = "Checking AIO db on %s for %s for customer: %d";
  private static final String SQL_GET_CONTRACT_ID_BY_CUST_ID_DESC_QUERY =
      "SELECT ID FROM Contract WHERE customer_ID = ? ORDER BY ID DESC";
  private static final String SQL_GET_CONTRACT_BY_CUST_ID_DESC_QUERY =
      "SELECT * FROM Contract WHERE customer_ID = ? ORDER BY ID DESC";

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE Contract c SET c.created =(DATE_ADD(c.created, INTERVAL ? DAY)), " +
          "c.closed =(DATE_ADD(c.closed, INTERVAL ? DAY)), " +
          "c.withdrawalDate =(DATE_ADD(c.withdrawalDate, INTERVAL ? DAY)), " +
          "c.startDate = (DATE_ADD(c.startDate, INTERVAL ? DAY)), " +
          "c.entityVersion = c.entityVersion+1 WHERE c.customer_ID = ?";
  private static final String SQL_SHIFT_DATES_BACK_QUERY =
      "UPDATE Contract c SET c.created =(DATE_SUB(c.created, INTERVAL ? DAY)), " +
          "c.closed =(DATE_SUB(c.closed, INTERVAL ? DAY)), " +
          "c.withdrawalDate =(DATE_SUB(c.withdrawalDate, INTERVAL ? DAY)), " +
          "c.startDate = (DATE_SUB(c.startDate, INTERVAL ? DAY)), " +
          "c.entityVersion = c.entityVersion+1 WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_START_DATE_BACK_QUERY = "UPDATE Contract c " +
      "SET c.startDate = (DATE_SUB(c.startDate, INTERVAL ? DAY)) " +
      "WHERE c.customer_ID = ?";


  @Override
  public Contract findContractInAio(final Environment environment, final Long customerId) {

    LOG.info(String.format(DB_CHECK_LOG_FORMAT, environment, "Contract", customerId));

    return DBServiceProvider.aioDBService()
        .callDB(environment, SQL_GET_CONTRACT_ID_BY_CUST_ID_DESC_QUERY, statement -> {
          statement.setLong(1, customerId);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              final Contract contract = new Contract();
              contract.id = resultSet.getLong("ID");
              contract.customer =
                  new JDBCCustomerDAO().getByCustomerId(customerId, environment.envName);
              return contract;
            }
            throw new IllegalStateException(
                "No Contracts found for customer with ID: " + customerId);
          }
        });
  }

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting Contract dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting Contract dates on {} {} days backward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(final String env, final Integer days, final Long customerId,
      final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, (StatementHandler<Void>) statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setInt(3, days);
          statement.setInt(4, days);
          statement.setLong(5, customerId);
          LOG.info("Updated {} rows in Contract table.", statement.executeUpdate());
          return null;
        });
  }

  @Override
  public void updateContractStartDateBack(final String env, final Integer days,
      final Long customerId) {

    DBServiceProvider.aioDBService()
        .callDB(env, SQL_SHIFT_START_DATE_BACK_QUERY, statement -> {
          statement.setInt(1, days);
          statement.setLong(2, customerId);
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in Contract table.", rows);
          return rows;
        });
  }

  @Override
  public Contract getByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for Contract on env: {} for customerId: {}", env, customerId);
    return new JDBCUtils<Contract>().getByCustomerId(SQL_GET_CONTRACT_BY_CUST_ID_DESC_QUERY, env,
        customerId,
        new ContractMapper());
  }

  @Override
  public List<Contract> findAllByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for Contracts on env: {} for customerId: {}", env, customerId);

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_CONTRACT_BY_CUST_ID_DESC_QUERY,
            statement -> getContractsListByCustomerId(statement, customerId));
  }

  private List<Contract> getContractsListByCustomerId(final PreparedStatement statement,
      final Long customerId) throws SQLException {
    final List<Contract> contractList = new ArrayList<>();
    statement.setLong(1, customerId);
    statement.setQueryTimeout(60);

    try (final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        contractList.add(new ContractMapper().mapByResultSet(resultSet));
      }
    }

    return contractList;
  }
}
