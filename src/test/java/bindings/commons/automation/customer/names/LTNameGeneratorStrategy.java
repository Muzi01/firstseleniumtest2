package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class LTNameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Adomas", "Albertas", "Aleksandras", "Alfredas", "Andrius", "Antanas", "Aras", "Artūras",
      "Augustas",
      "Augustinas", "Aurelijus", "ąžuolas", "Benas", "Benediktas", "Benjaminas", "Bronislovas",
      "Bronius",
      "Danielius", "Darijus", "Darius", "Daumantas", "Dominykas", "Donatas", "Dovydas", "Edgaras",
      "Emilis",
      "Erikas", "Ernestas", "Eugenijus", "Gabrielius", "Giedrius", "Gintaras", "Gvidas",
      "Henrikas", "Herkus",
      "Ignas", "Jaroslavas", "Jokūbas", "Jonas", "Juozapas", "Juozas", "Jurgis", "Justinas",
      "Kajus", "Karolis",
      "Kasparas", "Kazimieras", "Kęstutis", "Kristijonas", "Kristupas", "Laurynas", "Leonas",
      "Linas",
      "Liudvikas", "Lukas", "Marijus", "Martynas", "Matas", "Mindaugas", "Modestas", "Mykolas",
      "Nojus",
      "Paulius", "Petras", "Pilypas", "Pranciškus", "Raimondas", "Ramūnas", "Ričardas",
      "Robertas", "Saulius",
      "Simonas", "Stanislovas", "Steponas", "Tadas", "Titas", "Tomas", "Valdas", "Valdemaras",
      "Viktoras",
      "Vilhelmas", "Viltautas", "Vincentas", "Virgilijus", "Visvaldas", "Vitalijus", "Vladimiras",
      "Voldemaras",
      "Vygantas", "Vytautas", "Žydrūnas");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Agnė", "Aldona", "Amalija", "Audra", "Aurelija", "Aušra", "Austėja", "Barbora", "Birutė",
      "Daina", "Daiva", "Danutė", "Donata", "Dorotėja", "Edita", "Eglė", "Elžbieta",
      "Elzė", "Emilija", "Ernesta", "Estera", "Gabija", "Gabrielė", "Gertrūda", "Giedrė",
      "Gintarė", "Ieva",
      "Ilona", "Inga", "Irena", "Irma", "Jadvyga", "Janina", "Jolanta", "Judita", "Julija",
      "Justina",
      "Kamilė", "Karolina", "Katrė", "Kotryna", "Kristina", "Laima", "Laimutė", "Lėja", "Lilija",
      "Lina",
      "Liucija", "Liudvika", "Marija", "Marijona", "Melanija", "Miglė", "Milda", "Monika",
      "Morta",
      "Odeta", "Ona", "Rasa", "Rozalija", "Rožė", "Rūta", "Saulė", "Silvija",
      "Simona", "Smiltė", "Sofija", "Solveiga", "Svajonė", "Tatjana", "Ugnė", "Urtė", "Vaiva",
      "Valerija",
      "Veronika", "Viktorija", "Vilhelmina", "Viltautė", "Viltė", "Vita", "Vitalija");
  private static final List<String> MALE_LAST_NAMES = Arrays.asList(
      "Kazlauskas", "Jankauskas", "Petrauskas", "Stankevičius", "Vasiliauskas", "Butkus",
      "Žukauskas",
      "Paulauskas", "Urbonas", "Kavaliauskas");
  private static final List<String> FEMALE_LAST_NAMES = Arrays.asList(
      "Kazlauskienė", "Jankauskienė", "Petrauskienė", "Stankevičienė", "Vasiliauskienė",
      "Butkutė", "Žukauskienė",
      "Paulauskienė", "Urbonienė", "Kavaliauskienė");

  @Override
  public boolean accept(final Country country) {
    return Country.LT == country;
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
    if (gender == Gender.MALE) {
      return RandomUtils.randomFromList(MALE_LAST_NAMES);
    } else {
      return RandomUtils.randomFromList(FEMALE_LAST_NAMES);
    }
  }
}
