package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.utils.Environment;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDAO extends GenericDAO<Invoice>, ShiftingDatesDAO {

  LocalDate lastInvoiceDueDate(Environment environment, Customer customer);

  LocalDate lastInvoiceDate(Environment environment, Customer customer);

  List<String> getInvoiceStateTypeDESC(Environment environment,
      Customer customer);

  List<String> getInvoiceTypeDESC(Environment environment, Customer customer);

  List<String> getInvoiceIdsDESC(Environment environment, Customer customer);

  boolean isLastInvoiceClosed(Environment environment, Customer customer);

  LocalDate lastInvoiceClosedDate(Environment environment, Customer customer);

  Integer lastInvoicesCombinedAmount(int invoices, Environment environment,
      Customer customer);

  Integer lastInvoiceAmount(Environment environment, Customer customer);

  List<Invoice> getInvoiceListByCustomerId(String env, Long customerId);

  Invoice getLastInvoiceByCustomerId(String env, Long customerId);

  Invoice getOldestInvoiceByCustomerId(String env, Long customerId);
}
