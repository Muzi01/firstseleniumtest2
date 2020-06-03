package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.CreditApplication;
import com.ipfdigital.automation.generator.utils.Environment;

import java.util.List;

public interface CreditApplicationDAO extends GenericDAO<CreditApplication>, ShiftingDatesDAO {

  Long findCreditApplicationId(Environment environment, Long customerId);

  List<CreditApplication> findAllByCustomerId(Long customerId, String env);
}
