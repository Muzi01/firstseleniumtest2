package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class ROCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Bucureşti", "030983"),
      new CityDTO("Brașov", "500474"),
      new CityDTO("Cluj-Napoca", "400133"),
      new CityDTO("Timișoara", "300002"),
      new CityDTO("Ploiești", "100028"),
      new CityDTO("Craiova", "200402"),
      new CityDTO("Oradea", "410065"),
      new CityDTO("Iași", "700064"),
      new CityDTO("Pitești", "110017"),
      new CityDTO("Constanța", "900675"),
      new CityDTO("Piatra Neamț", "610039"),
      new CityDTO("Baia Mare", "430242"));

  @Override
  public boolean accept(final Country country) {
    return Country.RO == country;
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
