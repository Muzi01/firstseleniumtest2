package bindings.commons.automation.generator.model.aio.dao;


import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.database.connection.DBServiceProvider;

public class JDBCProvidentDataDAO implements ProvidentDataDAO {

  private static final String COUNT_BY_PIN_QUERY =
      "SELECT COUNT(ID) FROM ProvidentData WHERE pinHash = ?";

  @Override
  public long countByPin(final Environment environment, final String hash) {
    return Long.parseLong(DBServiceProvider.aioDBService().getQueryResult(environment,
        COUNT_BY_PIN_QUERY, "COUNT(ID)", hash));
  }

  @Override
  public ProvidentData getByCustomerId(final Long customerId, final String env) {
    throw new UnsupportedOperationException();
  }
}
