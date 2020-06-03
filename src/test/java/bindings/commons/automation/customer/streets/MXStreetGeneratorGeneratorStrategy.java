package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class MXStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> MX_STREET_NAMES = Arrays.asList(
      "Michoacan", "Jose Maria Morelos", "Av. Arbol De La Vida", "Coronel Medina",
      "Calle Belisario Dominguez",
      "José Vasconcelos", "Josefa O De Dominguez", "Calle", "Via Metepec", "Mexico Insurgente",
      "Interior Mecado Central Entrada", "Ramales", "Maestros Veracruzanos", "Felix Cuevas",
      "Paseo De Los Laureles", "Peninsula", "Guadalajara Centro", "Fracc Central", "Balderas",
      "Urión",
      "Super Manzana", "Nezahualcoyotl", "8th Straco", "Plutarco Elias Calles", "Víctor Hugo");

  @Override
  public boolean accept(final Country country) {
    return Country.MX == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(MX_STREET_NAMES);
  }
}
