package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class AUCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Adelaide", "5000", "Adelaide"),
      new CityDTO("Brisbane", "4000", "Brisbane"),
      new CityDTO("Darwin", "0800", "Darwin"),
      new CityDTO("Hobart", "7000", "Hobart"),
      new CityDTO("Melbourne", "3000", "Melbourne"),
      new CityDTO("Perth", "6000", "Perth"),
      new CityDTO("Sydney", "2000", "Sydney"),
      new CityDTO("Dubbo", "2830", "Dubbo Grove"),
      new CityDTO("Geelong", "3220", "South Geelong"),
      new CityDTO("Cairns", "4870", "Cairns City"),
      new CityDTO("Ballarat", "3350", "Ballarat East"),
      new CityDTO("Bunbury", "5266", "Bunbury"),
      new CityDTO("Albany", "6330", "Port Albany"));

  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
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
