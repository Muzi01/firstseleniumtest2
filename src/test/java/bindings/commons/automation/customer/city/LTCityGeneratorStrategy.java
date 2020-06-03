package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class LTCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Vilnius", "01001"),
      new CityDTO("Kaunas", "44001"),
      new CityDTO("Klaipėda", "91110"),
      new CityDTO("Šiauliai", "76001"),
      new CityDTO("Panevėžys", "35001"),
      new CityDTO("Alytus", "62001"),
      new CityDTO("Marijampolė", "68100"),
      new CityDTO("Mažeikiai", "87224"),
      new CityDTO("Jonava", "55014"),
      new CityDTO("Utena", "28001"),
      new CityDTO("Kėdainiai", "57143"),
      new CityDTO("Telšiai", "87101"),
      new CityDTO("Tauragė", "72110"),
      new CityDTO("Ukmergė", "20106"),
      new CityDTO("Visaginas", "31002"),
      new CityDTO("Radviliškis", "82126"),
      new CityDTO("Plungė", "90112"),
      new CityDTO("Kretinga", "97105"),
      new CityDTO("Palanga", "00103"),
      new CityDTO("Druskininkai", "66102"),
      new CityDTO("Rokiškis", "42106"),
      new CityDTO("Šilutė", "99113"),
      new CityDTO("Biržai", "41119"),
      new CityDTO("Elektrėnai", "41122"),
      new CityDTO("Jurbarkas", "74111"),
      new CityDTO("Vilkaviškis", "70108"),
      new CityDTO("Raseiniai", "60120"),
      new CityDTO("Anykščiai", "29107"),
      new CityDTO("Prienai", "59112"),
      new CityDTO("Joniškis", "84123"),
      new CityDTO("Kelmė", "86135"),
      new CityDTO("Varėna", "65170"),
      new CityDTO("Kaišiadorys", "56121"),
      new CityDTO("Pasvalys", "39106"),
      new CityDTO("Kupiškis", "40114"),
      new CityDTO("Zarasai", "32110"),
      new CityDTO("Skuodas", "98114"),
      new CityDTO("Kazlų Rūda", "69368"),
      new CityDTO("Širvintos", "19110"),
      new CityDTO("Molėtai", "33106"),
      new CityDTO("Šalčininkai", "17104"),
      new CityDTO("Šakiai", "71119"));

  @Override
  public boolean accept(final Country country) {
    return Country.LT == country;
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
