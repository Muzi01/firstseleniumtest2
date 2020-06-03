package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class ROMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.RO == country;
  }

  @Override
  public String generate() {
    return "7" + RandomStringUtils.randomNumeric(8);
  }
}
