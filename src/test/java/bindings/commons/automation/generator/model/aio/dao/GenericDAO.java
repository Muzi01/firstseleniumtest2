package bindings.commons.automation.generator.model.aio.dao;

import org.apache.commons.lang3.NotImplementedException;

public interface GenericDAO<T> {

  default boolean insert(final T t) {
    throw new NotImplementedException("Not implemented");
  }

  default boolean update(final T t) {
    throw new NotImplementedException("Not implemented");
  }

  default boolean delete() {
    throw new NotImplementedException("Not implemented");
  }

  T getByCustomerId(final Long customerId, final String env);
}
