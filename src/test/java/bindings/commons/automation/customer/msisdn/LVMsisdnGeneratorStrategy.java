package bindings.commons.automation.customer.msisdn;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

final class LVMsisdnGeneratorStrategy implements GenerateMsisdnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.LV == country;
  }

  @Override
  public String generate() {
    return "2" + RandomStringUtils.randomNumeric(7);
  }
}
