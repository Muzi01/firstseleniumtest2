package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class EECityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Tallinn", "15197"),
      new CityDTO("Tartu", "50304"),
      new CityDTO("Narva", "20001"),
      new CityDTO("Kohtla-jarve", "30101"),
      new CityDTO("Parnu", "80040"),
      new CityDTO("Viljandi", "71020"),
      new CityDTO("Rakvere", "44306"),
      new CityDTO("Maardu", "74111"),
      new CityDTO("Sillamae", "40201"),
      new CityDTO("Kuressaare", "93801"),
      new CityDTO("Voru", "65602"),
      new CityDTO("Valga", "68201"),
      new CityDTO("Johvi", "41502"),
      new CityDTO("Haapsalu", "90403"),
      new CityDTO("Turi", "72201"),
      new CityDTO("Paide", "72702"),
      new CityDTO("Rapla", "79502"),
      new CityDTO("Keila", "76605"),
      new CityDTO("Viimsi", "74001"),
      new CityDTO("Tapa", "45109"));

  @Override
  public boolean accept(final Country country) {
    return Country.EE == country;
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
