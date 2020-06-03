package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class ESCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Madrid", "28013"),
      new CityDTO("Barcelona", "08003"),
      new CityDTO("Valencia", "46003"),
      new CityDTO("Seville", "41002"),
      new CityDTO("Bilbao", "48009"),
      new CityDTO("Málaga", "29016"),
      new CityDTO("Oviedo", "33003"),
      new CityDTO("Beche", "15318"),
      new CityDTO("Zaragoza", "50003"),
      new CityDTO("Murcia", "30001"),
      new CityDTO("Granada", "18010"),
      new CityDTO("Paloma", "15528"),
      new CityDTO("Vigo", "36202"),
      new CityDTO("Cartagena", "30310"),
      new CityDTO("Cádiz", "11005"),
      new CityDTO("San Sebastián", "33490"),
      new CityDTO("A Coruña", "15005"),
      new CityDTO("Tarragona", "43003"),
      new CityDTO("Córdoba", "14002"),
      new CityDTO("Pamplona", "31001"),
      new CityDTO("Santander", "39004"),
      new CityDTO("Alzira", "46600"),
      new CityDTO("Vitoria-Gasteiz", "01001"),
      new CityDTO("Algeciras", "11204"),
      new CityDTO("Huelva", "21003"),
      new CityDTO("Salamanca", "37002"),
      new CityDTO("Jerez de la Frontera", "11405"),
      new CityDTO("León", "24002"),
      new CityDTO("Burgos", "09002"),
      new CityDTO("Ferrol", "15404"),
      new CityDTO("Lleida", "25003"),
      new CityDTO("Girona", "17004"),
      new CityDTO("Pontevedra", "36002"),
      new CityDTO("Santiago de Compostela", "15704"),
      new CityDTO("Ourense", "32005"),
      new CityDTO("Benidorm", "03503"),
      new CityDTO("Gandia", "48630"),
      new CityDTO("Blanes", "17300"),
      new CityDTO("Manresa", "08242"),
      new CityDTO("Marbella", "29600"),
      new CityDTO("Torrelavega", "39300"),
      new CityDTO("Vic", "08500"),
      new CityDTO("Lugo", "27001"),
      new CityDTO("Palencia", "34005"),
      new CityDTO("Toledo", "45002"));

  @Override
  public boolean accept(final Country country) {
    return Country.ES == country;
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
