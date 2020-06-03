package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class CZStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> CZ_STREET_NAMES = Arrays.asList(
      "Radimova",
      "Anastázova",
      "Vašátkova",
      "Litožnická",
      "Nový Zlíchov",
      "Za Novákovou zahradou",
      "Pod Karlovarskou silnicí",
      "Stolařská",
      "U Můstku",
      "Generála Vlachého",
      "Šebestiana Hněvkovského");

  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(CZ_STREET_NAMES);
  }
}
