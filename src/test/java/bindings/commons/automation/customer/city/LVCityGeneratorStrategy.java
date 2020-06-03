package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class LVCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Riga", "1001"),
      new CityDTO("Daugavpils", "5401"),
      new CityDTO("Liepāja", "3401"),
      new CityDTO("Jelgava", "3001"),
      new CityDTO("Jūrmala", "2008"),
      new CityDTO("Ventspils", "3601"),
      new CityDTO("Rēzekne", "4601"),
      new CityDTO("Jēkabpils", "5201"),
      new CityDTO("Valmiera", "4201"),
      new CityDTO("Ogre", "5001"),
      new CityDTO("Tukums", "3320"),
      new CityDTO("Cēsis", "4101"),
      new CityDTO("Salaspils", "2121"));

  @Override
  public boolean accept(final Country country) {
    return Country.LV == country;
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
