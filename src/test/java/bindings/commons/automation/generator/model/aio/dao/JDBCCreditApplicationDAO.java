package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.CreditApplication;
import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class JDBCCreditApplicationDAO implements CreditApplicationDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCCreditApplicationDAO.class);
  private static final String SQL_GET_CREDIT_APP_ID_BY_CUST_ID_DES_QUERY =
      "SELECT ID FROM CreditApplication WHERE customer_ID = ? ORDER BY ID DESC";
  private static final String SQL_GET_CREDIT_APP_BY_CUST_ID_DESC_QUERY =
      "SELECT * FROM CreditApplication WHERE customer_ID = ? ORDER BY ID DESC";
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY = "UPDATE CreditApplication ca " +
      "SET ca.created =(DATE_ADD(ca.created, INTERVAL ? DAY)), " +
      "ca.firstDueDate =(DATE_ADD(ca.firstDueDate, INTERVAL ? DAY)), " +
      "ca.entityVersion = ca.entityVersion+1 " +
      "WHERE ca.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE CreditApplication ca " +
      "SET ca.created =(DATE_SUB(ca.created, INTERVAL ? DAY)), " +
      "ca.firstDueDate =(DATE_SUB(ca.firstDueDate, INTERVAL ? DAY)), " +
      "ca.entityVersion = ca.entityVersion+1 " +
      "WHERE ca.customer_ID = ?";

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting CreditApplication dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting CreditApplication dates on {} {} days backward for customerID: {}", env,
        days, customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_BACK_QUERY);
  }

  private void updateDates(final String env, final Integer days, final Long customerId,
      final String query) {

    DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setLong(3, customerId);
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in CreditApplication table.", rows);
          return rows;
        });

  }

  @Override
  public Long findCreditApplicationId(final Environment environment, final Long customerId) {

    return Long.valueOf(DBServiceProvider.aioDBService()
        .getQueryResult(environment, SQL_GET_CREDIT_APP_ID_BY_CUST_ID_DES_QUERY, "ID", customerId));
  }

  @Override
  public CreditApplication getByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for CreditApplication on env: {} for customerId: {}", env, customerId);
    return new JDBCUtils<CreditApplication>().getByCustomerId(
        SQL_GET_CREDIT_APP_BY_CUST_ID_DESC_QUERY, env,
        customerId,
        new CreditApplicationMapper());
  }

  @Override
  public List<CreditApplication> findAllByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for CreditApplications on env: {} for customerId: {}", env, customerId);

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_CREDIT_APP_BY_CUST_ID_DESC_QUERY,
            statement -> getCreditApplicationsListByCustomerId(statement, customerId));
  }

  private List<CreditApplication> getCreditApplicationsListByCustomerId(
      final PreparedStatement statement,
      final Long customerId) throws SQLException {
    final List<CreditApplication> creditApplicationList = new ArrayList<>();
    statement.setLong(1, customerId);
    statement.setQueryTimeout(60);

    try (final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        creditApplicationList.add(new CreditApplicationMapper().mapByResultSet(resultSet));
      }
    }

    return creditApplicationList;
  }
}
