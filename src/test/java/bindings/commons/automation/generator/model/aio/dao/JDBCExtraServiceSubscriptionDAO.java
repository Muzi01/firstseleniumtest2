package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.ExtraServiceSubscription;
import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCExtraServiceSubscriptionDAO implements ExtraServiceSubscriptionDAO {
  private static final Logger LOG = LogManager.getLogger(JDBCExtraServiceSubscriptionDAO.class);
  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE ExtraServiceSubscription ess " +
          "JOIN Contract c ON c.ID = ess.contract_ID " +
          "SET ess.created = (DATE_ADD(ess.created, INTERVAL ? DAY)), " +
          "ess.closed = (DATE_ADD(ess.closed, INTERVAL ? DAY)), " +
          "ess.entityVersion = ess.entityVersion+1 " +
          "WHERE c.customer_ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY = "UPDATE ExtraServiceSubscription ess " +
      "JOIN Contract c ON c.ID = ess.contract_ID " +
      "SET ess.created = (DATE_SUB(ess.created, INTERVAL ? DAY)), " +
      "ess.validFromDateTime = IF(extraService = 'CONTRACT_FREEZE' OR extraService = 'CALMA', (DATE_SUB(ess.validFromDateTime, INTERVAL ? DAY)), ess.validFromDateTime), "
      +
      "ess.closed = (DATE_SUB(ess.closed, INTERVAL ? DAY)), " +
      "ess.entityVersion = ess.entityVersion+1 " +
      "WHERE c.customer_ID = ?";

  private static final String SQL_GET_EXTRA_SERVICES_SUBSCRIPTION_BY_CUST_ID_DESC_QUERY =
      "SELECT * FROM ExtraServiceSubscription e "
          + "JOIN Contract c on c.ID = e.contract_ID "
          + "JOIN Customer cus on cus.ID = c.customer_ID "
          + "WHERE cus.ID = ? ORDER BY e.ID DESC";

  @Override
  public void shiftDatesForward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting ExtraServiceSubscription dates on {} {} days forward for customerID: {}",
        env, days, customerId);
    updateDatesForward(env, days, customerId);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days,
      final Long customerId) {
    LOG.info("Shifting ExtraServiceSubscription dates on {} {} days backward for customerID: {}",
        env, days, customerId);
    updateDatesBackward(env, days, customerId);
  }

  private void updateDatesBackward(final String env, final Integer days,
      final Long customerId) {

    DBServiceProvider.aioDBService()
        .callDB(env, SQL_SHIFT_DATES_BACK_QUERY, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setInt(3, days);
          statement.setLong(4, customerId);

          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in ExtraServiceSubscription table.", rows);
          return rows;

        });
  }

  private void updateDatesForward(final String env, final Integer days, final Long customerId) {

    DBServiceProvider.aioDBService()
        .callDB(env, SQL_SHIFT_DATES_FORWARD_QUERY, statement -> {
          statement.setInt(1, days);
          statement.setInt(2, days);
          statement.setLong(3, customerId);
          final int rows = statement.executeUpdate();
          LOG.info("Updated {} rows in ExtraServiceSubscription table.", rows);
          return rows;
        });
  }

  @Override
  public ExtraServiceSubscription getByCustomerId(final Long customerId, final String env) {
    LOG.info("Searching for ExtraServiceSubscription on env: {} for customerId: {}", env,
        customerId);
    return new JDBCUtils<ExtraServiceSubscription>().getByCustomerId(
        SQL_GET_EXTRA_SERVICES_SUBSCRIPTION_BY_CUST_ID_DESC_QUERY, env,
        customerId,
        new ExtraServiceSubscriptionMapper());
  }
}
