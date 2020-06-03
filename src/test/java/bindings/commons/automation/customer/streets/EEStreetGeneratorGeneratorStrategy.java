package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class EEStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> EE_STREET_NAMES = Arrays.asList(
      "Aiandi tn", "Alvari tn", "Armatuuri tn", "Auru tn", "Eerikneeme tee", "Estonia puiestee",
      "Filtri tee",
      "Nikolai von Glehni tn", "Haabersti tn", "Hallikivi tn", "Heki tee",
      "Karl August Hermanni tn", "Hommiku tn",
      "Jaagu tn", "Carl Robert Jakobsoni tn", "Kaabli tn", "Kadaka puiestee", "Kalasadama tn",
      "Karjamaa tn",
      "Kiini tn", "August Kitzbergi tn", "Kriibi tn", "Lahepea tn", "Naaritsa tn",
      "Paljassaare tee",
      "Rotermanni tn", "Taela tn", "Tuhkru tn", "Tulimulla tn", "Vikerlase tn", "Vismeistri tn",
      "Wismari tn");

  @Override
  public boolean accept(final Country country) {
    return Country.EE == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(EE_STREET_NAMES);
  }
}
