package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class EEMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.EE == country;
  }

  @Override
  public String generate() {
    return "55" + RandomStringUtils.randomNumeric(6);
  }
}
