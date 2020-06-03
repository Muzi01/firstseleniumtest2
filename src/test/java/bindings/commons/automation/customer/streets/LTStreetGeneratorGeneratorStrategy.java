package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class LTStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> LT_STREET_NAMES = Arrays.asList(
      "Vytauto g.", "Tilžės g.", "Telšių g.", "Didžioji g.", "Laisvės pr.", "Taikos pr.",
      "Pylimo g.",
      "Neries krantinė", "Rūtų g.", "Vilkpėdės g.", "K. Baršausko g.", "Vilniaus g.", "Bijūnų g.");

  @Override
  public boolean accept(final Country country) {
    return Country.LT == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(LT_STREET_NAMES);
  }
}
