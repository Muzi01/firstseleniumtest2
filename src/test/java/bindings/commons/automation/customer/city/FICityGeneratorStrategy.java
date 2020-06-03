package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class FICityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Helsinki", "00100"),
      new CityDTO("Espoo", "02002"),
      new CityDTO("Tampere", "33002"),
      new CityDTO("Vantaa", "01002"),
      new CityDTO("Turku", "20002"),
      new CityDTO("Oulu", "90003"),
      new CityDTO("Lahti", "15007"),
      new CityDTO("Kuopio", "70007"),
      new CityDTO("Jyväskylä", "40003"),
      new CityDTO("Pori", "28003"));

  @Override
  public boolean accept(final Country country) {
    return Country.FI == country;
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
