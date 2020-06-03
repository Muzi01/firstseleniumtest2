package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Contract;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractMapper implements ResultMapper<Contract> {

  @Override
  public Contract mapByResultSet(final ResultSet resultSet) throws SQLException {
    final Contract contract = new Contract();
    contract.id = resultSet.getLong("ID");
    contract.created = resultSet.getDate("created");
    contract.closed = resultSet.getDate("closed");
    contract.contractType = resultSet.getString("contractType");
    contract.customerId = resultSet.getLong("customer_ID");
    contract.documentDeliveryId = resultSet.getLong("documentDelivery_ID");
    contract.productId = resultSet.getLong("product_ID");
    contract.entityVersion = resultSet.getInt("entityVersion");
    contract.updated = resultSet.getTimestamp("updated");
    contract.creditApplicationId = resultSet.getLong("creditApplication_ID");
    contract.stateType = resultSet.getString("stateType");
    contract.hierarchyId = resultSet.getLong("hierarchy_ID");
    contract.contractsOrder = resultSet.getInt("contracts_ORDER");
    contract.withdrawalDate = resultSet.getDate("withdrawalDate");
    contract.migrated = resultSet.getBoolean("migrated");
    contract.courtOrderLateInterestDate = resultSet.getDate("courtOrderLateInterestDate");
    contract.accountNumber = resultSet.getString("accountNumber");
    contract.contractIdentity = resultSet.getLong("contractIdentity");
    contract.closedBy = resultSet.getString("closedBy");
    contract.terminationDate = resultSet.getDate("terminationDate");
    contract.startDate = resultSet.getDate("startDate");
    contract.mandatoryProvisioningFeeFactor = resultSet.getFloat("mandatoryProvisioningFeeFactor");
    return contract;
  }
}
