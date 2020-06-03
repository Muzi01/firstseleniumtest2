package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class HUCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Budapest", "1011"),
      new CityDTO("Debrecen", "4010"),
      new CityDTO("Szeged", "6729"),
      new CityDTO("Miskolc", "3549"),
      new CityDTO("Pécs", "7600"),
      new CityDTO("Győr", "9028"),
      new CityDTO("Nyíregyháza", "4433"),
      new CityDTO("Kecskemét", "6008"),
      new CityDTO("Jászfelsőszentgyörgy", "5111"));

  @Override
  public boolean accept(final Country country) {
    return Country.HU == country;
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
