package bindings.commons.automation.generator.model.aio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultMapper<T> {
  T mapByResultSet(final ResultSet resultSet) throws SQLException;
}
