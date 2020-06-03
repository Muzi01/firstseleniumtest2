package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDocumentDeliveryDAO implements DocumentDeliveryDAO {

  private static final Logger LOG = LogManager.getLogger(JDBCDocumentDeliveryDAO.class);
  private static final String SQL_GET_LAST_DOCUMENT_DELIVERY_BY_CUSTOMER_ID_QUERY =
      "SELECT * FROM DocumentDelivery WHERE customer_ID = ? ORDER BY ID DESC LIMIT 1";
  private static final String SQL_GET_DOCUMENT_DELIVERY_BY_CUSTOMER_ID_QUERY =
      "SELECT * FROM DocumentDelivery WHERE customer_ID = ? ORDER BY ID DESC";

  private static final String SQL_SHIFT_DATES_FORWARD_QUERY =
      "UPDATE DocumentDelivery dd " +
          "JOIN Customer cu ON cu.ID=dd.customer_ID " +
          "SET dd.created = (DATE_ADD(dd.created, INTERVAL ? DAY)), " +
          "dd.submitted = (DATE_ADD(dd.submitted, INTERVAL ? DAY)), " +
          "dd.entityVersion=dd.entityVersion+1 " +
          "WHERE cu.ID = ?";

  private static final String SQL_SHIFT_DATES_BACK_QUERY =
      "UPDATE DocumentDelivery dd " +
          "JOIN Customer cu ON cu.ID=dd.customer_ID " +
          "SET dd.created = (DATE_SUB(dd.created, INTERVAL ? DAY)), " +
          "dd.submitted = (DATE_SUB(dd.submitted, INTERVAL ? DAY)), " +
          "dd.entityVersion=dd.entityVersion+1 " +
          "WHERE cu.ID = ?";


  private List<DocumentDelivery> getDocumentDeliveryListByCustomerId(
      final PreparedStatement statement,
      final Long customerId)
      throws SQLException {
    final List<DocumentDelivery> documentDeliveryList = new ArrayList<>();
    statement.setLong(1, customerId);
    statement.setQueryTimeout(60);

    try (final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        documentDeliveryList.add(new DocumentDeliveryMapper().mapByResultSet(resultSet));
      }
    }

    return documentDeliveryList;
  }

  @Override
  public List<DocumentDelivery> getByCustomerId(final Long customerId, final String env) {

    LOG.info("Searching for Delivered Documents on env: {} for customerId: {}", env, customerId);

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_DOCUMENT_DELIVERY_BY_CUSTOMER_ID_QUERY,
            statement -> getDocumentDeliveryListByCustomerId(statement, customerId));
  }

  @Override
  public DocumentDelivery getLastDocumentDeliveryByCustomerId(final String env,
      final Long customerId) {
    LOG.info("Searching for last Delivered Document on env: {} for customerId: {}", env,
        customerId);
    return new JDBCUtils<DocumentDelivery>().getByCustomerId(
        SQL_GET_LAST_DOCUMENT_DELIVERY_BY_CUSTOMER_ID_QUERY,
        env,
        customerId,
        new DocumentDeliveryMapper());
  }

  @Override
  public void shiftDatesForward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting DocumentDelivery dates on {} {} days forward for customerID: {}", env, days,
        customerId);
    updateDates(env, days, customerId, SQL_SHIFT_DATES_FORWARD_QUERY);
  }

  @Override
  public void shiftDatesBackward(final String env, final Integer days, final Long customerId) {
    LOG.info("Shifting DocumentDelivery dates on {} {} days backward for customerID: {}", env, days,
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
          LOG.info("Updated {} rows in DocumentDelivery table.", statement.executeUpdate());
          return null;
        });
  }
}
