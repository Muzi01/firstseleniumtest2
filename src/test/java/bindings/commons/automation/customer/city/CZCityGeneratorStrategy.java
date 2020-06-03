package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class CZCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Praha", "16900"),
      new CityDTO("Kladno", "27201"),
      new CityDTO("Nymburk", "28802"),
      new CityDTO("Kroměříž", "76701"),
      new CityDTO("Brno", "60200"),
      new CityDTO("České Budějovice", "37001"),
      new CityDTO("Karlovy Vary", "36001"),
      new CityDTO("Ceský Krumlov", "38101"),
      new CityDTO("Kopřivnice", "74221"),
      new CityDTO("Náchod", "54701"),
      new CityDTO("Ostrava", "70200"),
      new CityDTO("Zlín", "76001"),
      new CityDTO("Jičín", "50601"),
      new CityDTO("Tábor", "39001"),
      new CityDTO("Plzeň", "30100"));

  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
  }

  @Override
  public CityDTO generate() {
    return RandomUtils.randomFromList(cities);
  }

  @Override
  public List<CityDTO> getCities() {
    return cities;
  }
}
