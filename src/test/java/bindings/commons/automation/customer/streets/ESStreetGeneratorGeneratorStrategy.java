package bindings.commons.automation.customer.streets;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class ESStreetGeneratorGeneratorStrategy implements StreetGeneratorStrategy {

  private static final List<String> ES_STREET_NAMES = Arrays.asList(
      "Paraguay", "Valadouro", "Padre Caro", "Escuadro", "Pascual Yunquera", "Canónigo Valiño",
      "Rosa de los Vientos", "Cercas Bajas", "Cañadilla", "Crta. Cádiz-Málaga",
      "Calle Carril de la Fuente",
      "Ctra. Villena", "Plaza de España", "Avda. de la Estación", "Celso Emilio Ferreiro",
      "Rua da Rapina");

  @Override
  public boolean accept(final Country country) {
    return Country.ES == country;
  }

  @Override
  public String generate() {
    return RandomUtils.randomFromList(ES_STREET_NAMES);
  }
}
