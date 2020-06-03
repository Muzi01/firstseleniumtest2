package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.cases.CreditApplicationCase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditApplicationCaseMapper implements
    ResultMapper<CreditApplicationCase> {

  @Override
  public CreditApplicationCase mapByResultSet(final ResultSet resultSet) throws SQLException {
    final CreditApplicationCase creditApplicationCase = new CreditApplicationCase();
    creditApplicationCase.id = resultSet.getLong("ID");
    creditApplicationCase.creditApplicationId = resultSet.getLong("creditApplication_ID");
    creditApplicationCase.updated = resultSet.getTimestamp("updated");
    return creditApplicationCase;
  }
}
