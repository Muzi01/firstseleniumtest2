package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class LVNameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Aleksandrs", "Anatolijs", "Andrejs", "Andris", "Artjoms", "Artūrs", "Augusts", "Bendiks",
      "Daniels",
      "Eduards", "Eižens", "Ervīns", "Filips", "Fricis", "Frīdrihs", "Gabriels", "Georgijs",
      "Georgs",
      "Grigorijs", "Gustavs", "Jānis", "Jāzeps", "Jevgēņijs", "Jevgeņijs", "Juris", "Kaspars",
      "Kirils",
      "Klaudijs", "Kristaps", "Kristiāns", "Ludis", "Ludvigs", "Markuss", "Miervaldis", "Mihails",
      "Miķelis",
      "Nikolajs", "Oskars", "Pāvils", "Raimonds", "Ričards", "Rihards", "Roberts", "Stefans",
      "Teodors", "Toms",
      "Valdis", "Valērijs", "Viktors", "Vilhelms", "Vilis", "Visvaldis", "Vladimirs",
      "Voldemārs");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Agnese", "Agnija", "Alise", "Anita", "Anna", "Beatrise", "Brigita", "Dagnija", "Daina",
      "Diāna", "Elīna",
      "Elita", "Emīlija", "Helēna", "Ieva", "Ilona", "Inga", "Jeļena", "Jelena", "Jevgēņija",
      "Jevgeņija",
      "Jūlija", "Klāra", "Kristiāna", "Kristīna", "Kristīne", "Laima", "Larisa", "Lauma",
      "Lilija", "Lilita",
      "Linda", "Lūcija", "Madara", "Marija", "Melanija", "Monika", "Monta", "Nadežda", "Natālija",
      "Olga", "Rozālija", "Sandra", "Saule", "Silvija", "Sofija", "Solveiga", "Tatjana", "Valda",
      "Valērija",
      "Veronika", "Viktorija", "Vita", "Zane", "Zoja", "Zuzanna");
  private static final List<String> LAST_NAMES = Arrays.asList(
      "Bērziņš", "Kalniņš", "Ozoliņš", "Jansons", "Ozols", "Liepiņš", "Krūmiņš", "Balodis",
      "Eglītis", "Zariņš",
      "Pētersons", "Vītols", "Kļaviņš", "Kārkliņš", "Vanags");

  @Override
  public boolean accept(final Country country) {
    return Country.LV == country;
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
