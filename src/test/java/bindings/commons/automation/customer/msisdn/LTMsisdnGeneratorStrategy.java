package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class LTMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.LT == country;
  }

  @Override
  public String generate() {
    return "6" + RandomStringUtils.randomNumeric(7);
  }
}
