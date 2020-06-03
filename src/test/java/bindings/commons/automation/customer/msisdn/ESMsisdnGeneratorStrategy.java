package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class ESMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.ES == country;
  }

  @Override
  public String generate() {
    return "5" + RandomStringUtils.randomNumeric(8);
  }
}
