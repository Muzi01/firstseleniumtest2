package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class AUMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
  }

  @Override
  public String generate() {
    return "4" + RandomStringUtils.randomNumeric(8);
  }
}
