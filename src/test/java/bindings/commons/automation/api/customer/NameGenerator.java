package bindings.commons.automation.api.customer;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;

public interface NameGenerator {
  String generateFirstName(final Country country, final Gender gender);

  String generateLastName(final Country country, final Gender gender);
}
