package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class LVStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> LV_STREET_NAMES = Arrays.asList(
      "Vaidavas", "Marcienas", "Bauskas", "Kalpaka Bulv", "Plata", "Sigulda", "Elizabetes", "Lenku",
      "Motoru",
      "Agenskalna", "Maksa", "Liepajas", "Zentenes", "Veldres", "Lielvardes", "Kekava", "Brivibas",
      "Sesku");

  @Override
  public boolean accept(final Country country) {
    return Country.LV == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(LV_STREET_NAMES);
  }
}
