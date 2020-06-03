package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;

interface GenerateSsnStrategy {
  // This interface not extends GenerateStrategy because only them generate data using dto as param.
  // Adding generic method like this increase complexity
  boolean accept(final Country country);

  String generate(GenerateSsnParamsDTO dto);
}
