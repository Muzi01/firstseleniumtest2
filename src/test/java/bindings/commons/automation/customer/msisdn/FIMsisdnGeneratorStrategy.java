package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class FIMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.FI == country;
  }

  @Override
  public String generate() {
    return "04" + RandomStringUtils.randomNumeric(8);
  }
}
