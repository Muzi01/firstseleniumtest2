package bindings.commons.automation.customer.city;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.address.CityDTO;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class PLCityGeneratorStrategy implements CityStrategy {

  private static final List<CityDTO> cities = Arrays.asList(
      new CityDTO("Warszawa", "00-465"),
      new CityDTO("Kraków", "31-144"),
      new CityDTO("Łódź", "90-303"),
      new CityDTO("Wrocław", "51-671"),
      new CityDTO("Poznań", "61-741"),
      new CityDTO("Gdańsk", "80-680"),
      new CityDTO("Szczecin", "87-860"),
      new CityDTO("Bydgoszcz", "77-138"),
      new CityDTO("Lublin", "20-001"),
      new CityDTO("Katowice", "40-001"),
      new CityDTO("Białystok", "15-001"),
      new CityDTO("Gdynia", "81-000"),
      new CityDTO("Częstochowa", "42-200"),
      new CityDTO("Radom", "26-600"),
      new CityDTO("Sosnowiec", "41-200"),
      new CityDTO("Toruń", "87-100"),
      new CityDTO("Kielce", "25-001"),
      new CityDTO("Rzeszów", "35-001"),
      new CityDTO("Gliwice", "44-100"),
      new CityDTO("Zabrze", "41-800"),
      new CityDTO("Olsztyn", "10-001"),
      new CityDTO("Bielsko-Biała", "43-300"),
      new CityDTO("Bytom", "41-900"),
      new CityDTO("Zielona", "09-311"),
      new CityDTO("Rybnik", "44-200"),
      new CityDTO("Ruda", "39-315"),
      new CityDTO("Tychy", "43-100"),
      new CityDTO("Dąbrowa", "06-522"),
      new CityDTO("Płock", "09-400"),
      new CityDTO("Elbląg", "82-300"),
      new CityDTO("Opole", "45-001"),
      new CityDTO("Wałbrzych", "58-300"),
      new CityDTO("Włocławek", "87-800"),
      new CityDTO("Tarnów", "33-100"),
      new CityDTO("Chorzów", "41-500"),
      new CityDTO("Kalisz", "62-800"),
      new CityDTO("Koszalin", "75-001"),
      new CityDTO("Legnica", "59-200"),
      new CityDTO("Grudziądz", "86-300"),
      new CityDTO("Słupsk", "76-200"),
      new CityDTO("Jaworzno", "43-600"),
      new CityDTO("Konin", "62-500"),
      new CityDTO("Siedlce", "08-100"),
      new CityDTO("Lubin", "59-300"),
      new CityDTO("Inowrocław", "88-100"),
      new CityDTO("Mysłowice", "41-400"),
      new CityDTO("Piła", "64-900"),
      new CityDTO("Ostrowiec", "76-129"),
      new CityDTO("Ostrów", "39-103"),
      new CityDTO("Pabianice", "95-200"),
      new CityDTO("Gniezno", "62-200"),
      new CityDTO("Suwałki", "16-400"),
      new CityDTO("Głogów", "67-200"),
      new CityDTO("Chełm", "22-100"),
      new CityDTO("Przemyśl", "37-700"),
      new CityDTO("Zamość", "22-400"),
      new CityDTO("Kędzierzyn-Koźle", "47-200"),
      new CityDTO("Leszno", "64-100"),
      new CityDTO("Łomża", "18-400"),
      new CityDTO("Żory", "44-240"),
      new CityDTO("Bełchatów", "97-400"),
      new CityDTO("Mielec", "39-300"),
      new CityDTO("Tczew", "83-100"),
      new CityDTO("Świdnica", "58-100"),
      new CityDTO("Będzin", "42-500"),
      new CityDTO("Zgierz", "95-100"),
      new CityDTO("Biała", "09-411"),
      new CityDTO("Racibórz", "47-400"),
      new CityDTO("Ełk", "19-300"),
      new CityDTO("Pruszków", "05-800"),
      new CityDTO("Świętochłowice", "41-600"),
      new CityDTO("Ostrołęka", "07-400"),
      new CityDTO("Starachowice", "27-200"),
      new CityDTO("Zawiercie", "42-400"),
      new CityDTO("Legionowo", "05-118"),
      new CityDTO("Tarnobrzeg", "39-400"),
      new CityDTO("Puławy", "24-100"),
      new CityDTO("Wodzisław", "28-330"),
      new CityDTO("Radomsko", "97-500"));

  @Override
  public boolean accept(final Country country) {
    return Country.PL == country;
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
