package bindings.commons.automation.generator.model.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class InvoicingParamsMapper implements ResultMapper<InvoicingParams> {

  @Override
  public InvoicingParams mapByResultSet(final ResultSet resultSet) throws SQLException {
    final InvoicingParams invoicingParams = new InvoicingParams();
    invoicingParams.id = resultSet.getLong("ID");
    invoicingParams.entityVersion = resultSet.getLong("entityVersion");
    invoicingParams.created = resultSet.getDate("created");
    invoicingParams.closed = resultSet.getDate("closed");
    invoicingParams.firstDueDate = resultSet.getDate("firstDueDate");
    invoicingParams.mmp = resultSet.getInt("mmp");
    invoicingParams.contractId = resultSet.getLong("contract_ID");
    invoicingParams.updated = resultSet.getTimestamp("updated");
    invoicingParams.nextDueDate = resultSet.getDate("nextDueDate");
    invoicingParams.mmpCalculationStrategy =
        Objects.nonNull(resultSet.getString("mmpCalculationStrategy"))
            ? MmpCalculationStrategy.valueOf(resultSet.getString("mmpCalculationStrategy"))
            : null;
    invoicingParams.event =
        Objects.nonNull(resultSet.getString("event")) ? Event.valueOf(resultSet.getString("event"))
            : null;
    invoicingParams.minimalPrincipalValue = resultSet.getBigDecimal("minimalPrincipalValue");
    invoicingParams.minimalPrincipalType = resultSet.getString("minimalPrincipalType");

    return invoicingParams;
  }
}
