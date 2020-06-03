package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.StringUtils;

final class AUIbanGeneratorStrategy implements GenerateIbanStrategy {
  @Override
  public String generate() {
    // Generate iban on AU is not supported now
    return StringUtils.EMPTY;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
  }
}
