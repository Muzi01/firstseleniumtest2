package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class HUNameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Ákos", "Albert", "Alpár", "Alvin", "Ambrus", "András", "Anton", "Árpád", "Atilla", "Aurél",
      "Balázs",
      "Balint", "Barnabás", "Béla", "Bence", "Berény", "Botond", "Bottyán", "Csaba", "Dénes",
      "Dezső", "Elemér",
      "Endre", "Ernő", "Ervin", "Farkas", "Ferenc", "Gábor", "Gáspár", "Gergő", "Géza", "Gusztáv",
      "Győző",
      "György", "Gyula", "Gyuri", "Helge", "Henrik", "Imre", "István", "János", "Jenő", "József",
      "Kálmán",
      "Károly", "Kelemen", "Lajos", "László", "Lőrinc", "Lukács", "Márk", "Máté", "Mátyás",
      "Medard", "Megyer",
      "Mihály", "Miki", "Miklós", "Mór", "Nándor", "Norbert", "Ödön", "Osvát", "Pál", "Péter",
      "Rajmund",
      "Rezső", "Roman", "Ronald", "Sándor", "Sigmund", "Szabolcs", "Szervác", "Szilárd", "Tamás",
      "Tibor",
      "Vincenz", "Zoltán", "Zsolt", "Zsombor");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Agnes", "Amalia", "Anikó", "Anita", "Anna", "Barbara", "Bianka", "Boglárka", "Csilla",
      "Diana", "Dorina",
      "Enikő", "Etelka", "Gyöngyi", "Hajnal", "Helga", "Ildikó", "Ilona", "Irina", "Jolana",
      "Judit", "Katalin",
      "Livia", "Margit", "Mária", "Marika", "Marta", "Medea", "Melinda", "Nadia", "Olga",
      "Orsolya", "Paloma",
      "Paula", "Piroska", "Réka", "Rózsa", "Stefania", "Tímea", "Tünde", "Veronica", "Vilma",
      "Viola", "Virág",
      "Zita", "Zsófia", "Zsuzsanna");
  private final List<String> LAST_NAMES = Arrays.asList(
      "Nagy", "Kovács", "Tóth", "Szabó", "Horváth", "Varga", "Kiss", "Molnár", "Németh", "Farkas",
      "Balogh",
      "Papp", "Takács", "Juhász", "Lakatos", "Mészáros", "Oláh", "Simon", "Rácz", "Fekete");

  @Override
  public boolean accept(final Country country) {
    return Country.HU == country;
  }

  @Override
  public String generateFirstName(final Gender gender) {
    if (gender == Gender.MALE) {
      return RandomUtils.randomFromList(MALE_FIRST_NAMES);
    }
    return RandomUtils.randomFromList(FEMALE_FIRST_NAMES);
  }

  @Override
  public String generateLastName(final Gender gender) {
    return RandomUtils.randomFromList(LAST_NAMES);
  }
}
