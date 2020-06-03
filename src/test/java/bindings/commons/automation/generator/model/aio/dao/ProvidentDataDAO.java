package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.utils.Environment;

public interface ProvidentDataDAO extends GenericDAO<ProvidentData> {
  long countByPin(Environment environment, String hash);
}
