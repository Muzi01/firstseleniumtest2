package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.utils.Environment;

import java.time.LocalDate;
import java.util.List;

public interface InvoicingParamsDAO extends GenericDAO<InvoicingParams>, ShiftingDatesDAO {
  int getLatestMmp(String env, Long customerId);

  LocalDate findNextDueDate(Environment environment, Customer customer);

  List<InvoicingParams> getInvoicingParamsListByCustomerId(String env,
      Long customerId);
}
