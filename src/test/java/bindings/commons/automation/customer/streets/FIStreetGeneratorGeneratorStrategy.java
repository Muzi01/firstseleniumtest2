package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class FIStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> FI_STREET_NAMES = Arrays.asList(
      "Aleksanterinkatu", "Bulevardi", "Esplanadi", "Fredrikinkatu", "Hämeentie",
      "Korkeavuorenkatu",
      "Mannerheimintie", "Nordenskiöldinkatu", "Ring I", "Ring III", "Siilitie", "Tehtaankatu");

  @Override
  public boolean accept(final Country country) {
    return Country.FI == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(FI_STREET_NAMES);
  }
}
