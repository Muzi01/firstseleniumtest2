package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class HUStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> HU_STREET_NAMES = Arrays.asList(
      "Ady Endre út", "Dózsa György út", "Petőfi utca", "Kossuth Lajos utca", "Rákóczi út",
      "Petőfi Sándor utca",
      "Arany János utca", "Kossuth utca", "József Attila utca", "Béke utca", "Szabadság utca");

  @Override
  public boolean accept(final Country country) {
    return Country.HU == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(HU_STREET_NAMES);
  }
}
