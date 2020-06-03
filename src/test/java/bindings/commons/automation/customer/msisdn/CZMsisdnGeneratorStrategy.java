package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class CZMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
  }

  @Override
  public String generate() {
    return "7" + RandomStringUtils.randomNumeric(8);
  }
}
