package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.utils.Environment;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDAO extends GenericDAO<Customer> {
  Customer getCustomerBySsn(final String env, final String ssn);

  Customer getCustomerById(String env, String customerId);

  Customer fromAIObyEmail(String env, String email);

  Long findCustomerFirstDrawAmount(Environment environment, String customerId);

  boolean isUsedMsisdn(Environment env, String msisdn);

  long countByLastNameAndDateOfBirth(Environment env, String lastName, LocalDate dateOfBirth);

  void updatePassword(Environment environment, String password, String customerId);

  List<String> findSsnByVerificationStateAndCountryAndProductType(
      final Environment environment, final String verificationState,
      final String country,
      final String productType);
}
