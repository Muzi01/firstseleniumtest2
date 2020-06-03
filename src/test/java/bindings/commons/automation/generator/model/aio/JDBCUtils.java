package bindings.commons.automation.generator.model.aio;

import com.ipfdigital.automation.generator.model.aio.dao.ResultMapper;
import com.ipfdigital.database.connection.DBServiceProvider;

import java.sql.ResultSet;

public class JDBCUtils<T> {

  public T getByCustomerId(final String query, final String env,
      final Long customerId, final ResultMapper<T> resultMapper) {

    return DBServiceProvider.aioDBService()
        .callDB(env, query, statement -> {

          statement.setLong(1, customerId);
          statement.setQueryTimeout(60);

          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return resultMapper.mapByResultSet(resultSet);
            }

            throw new IllegalStateException(String
                .format("Not found results for query: %s with customerId: %s ", query, customerId));
          }
        });
  }
}
