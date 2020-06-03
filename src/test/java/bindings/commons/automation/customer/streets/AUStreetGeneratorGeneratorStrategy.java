package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class AUStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> AU_STREET_NAMES = Arrays.asList(
      "Church Street",
      "High Street",
      "King Street",
      "Short Street",
      "Queen Street",
      "Wattle Street",
      "Eucalyptus Drive",
      "Bottle Brush Road",
      "Wallaby Road",
      "Brushtail Crescent",
      "Platypus Road");

  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(AU_STREET_NAMES);
  }
}
