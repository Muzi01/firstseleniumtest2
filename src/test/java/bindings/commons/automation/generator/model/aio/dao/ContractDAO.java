package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Contract;
import com.ipfdigital.automation.generator.utils.Environment;

import java.util.List;

public interface ContractDAO extends GenericDAO<Contract>, ShiftingDatesDAO {

  Contract findContractInAio(Environment environment, Long customerId);

  void updateContractStartDateBack(String env, Integer days,
      Long customerId);

  List<Contract> findAllByCustomerId(Long customerId, String env);
}
