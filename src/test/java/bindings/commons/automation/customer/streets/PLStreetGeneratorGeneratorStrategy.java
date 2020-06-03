package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class PLStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> PL_STREET_NAMES = Arrays.asList(
      "Polna", "Leśna", "Słoneczna", "Krótka", "Szkolna", "Ogrodowa", "Lipowa", "Brzozowa",
      "Łąkowa", "Kwiatowa",
      "Kołowa", "Dojlidzka", "Złotnicza", "Gorkiego Maksyma", "Chlebowa", "Przyrynek", "Toruńska",
      "Janikowska",
      "Jordana", "Ananasowa", "Na Podgórniku", "Krokusów", "Mogilska", "Broniewskiego Władysława",
      "Wodospady");

  @Override
  public boolean accept(final Country country) {
    return Country.PL == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(PL_STREET_NAMES);
  }
}
