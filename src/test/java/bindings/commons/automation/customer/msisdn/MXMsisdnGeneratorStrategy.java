package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class MXMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.MX == country;
  }

  @Override
  public String generate() {
    return "70" + RandomStringUtils.randomNumeric(7) + "1";
  }
}
