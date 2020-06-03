package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.cases.NewDrawdownCase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewDrawdownCaseMapper implements
    ResultMapper<NewDrawdownCase> {

  @Override
  public NewDrawdownCase mapByResultSet(final ResultSet resultSet) throws SQLException {
    final NewDrawdownCase newDrawdownCase = new NewDrawdownCase();
    newDrawdownCase.amount = resultSet.getInt("amount");
    newDrawdownCase.id = resultSet.getLong("ID");
    newDrawdownCase.contractId = resultSet.getLong("contract_ID");
    newDrawdownCase.principalCashFlowId = resultSet.getLong("principalCashFlow_ID");
    newDrawdownCase.reference = resultSet.getString("reference");
    newDrawdownCase.creditApplicationId = resultSet.getLong("creditApplication_ID");
    newDrawdownCase.updated = resultSet.getTimestamp("updated");
    return newDrawdownCase;
  }
}
