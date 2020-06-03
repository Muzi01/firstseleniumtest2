package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.ExtraServiceSubscription;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtraServiceSubscriptionMapper implements ResultMapper<ExtraServiceSubscription> {

  @Override
  public ExtraServiceSubscription mapByResultSet(final ResultSet resultSet) throws SQLException {
    final ExtraServiceSubscription extraServiceSubscription = new ExtraServiceSubscription();
    extraServiceSubscription.id = resultSet.getLong("ID");
    extraServiceSubscription.entityVersion = resultSet.getLong("entityVersion");
    extraServiceSubscription.created = resultSet.getDate("created");
    extraServiceSubscription.closed = resultSet.getDate("closed");
    extraServiceSubscription.extraService = resultSet.getString("extraService");
    extraServiceSubscription.price = resultSet.getInt("price");
    extraServiceSubscription.contractId = resultSet.getLong("contract_ID");
    extraServiceSubscription.updated = resultSet.getTimestamp("updated");
    extraServiceSubscription.issuerMode = resultSet.getString("issuerMode");
    extraServiceSubscription.issuedBy = resultSet.getString("issuedBy");
    extraServiceSubscription.validFromDateTime = resultSet.getDate("validFromDateTime");
    extraServiceSubscription.validToDateTime = resultSet.getDate("validToDateTime");
    extraServiceSubscription.deactivationReason = resultSet.getString("deactivationReason");
    extraServiceSubscription.deactivationRequestedOn = resultSet.getDate("deactivationRequestedOn");
    return extraServiceSubscription;
  }
}
