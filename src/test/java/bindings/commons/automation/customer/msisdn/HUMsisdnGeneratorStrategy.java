package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class HUMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.HU == country;
  }

  @Override
  public String generate() {
    return "30" + RandomStringUtils.randomNumeric(7);
  }
}
