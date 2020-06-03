package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;

// This interface not extends GenerateStrategy because they have non generic methods.
// Adding generic method like this increase complexity
interface GenerateNameStrategy {
  boolean accept(Country country);

  String generateFirstName(Gender gender);

  String generateLastName(Gender gender);
}
