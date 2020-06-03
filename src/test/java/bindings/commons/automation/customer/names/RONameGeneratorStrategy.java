package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class RONameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList("Adi", "Alexandru", "Alin",
      "Andrei",
      "Antoniu", "Beniamin", "Bogdan", "Cătălin", "Cezar", "Cosmin", "Cristian", "Dănuț", "Dorin",
      "Emil", "Florin", "Gheorghe", "Grigore", "Ilie", "Ioan", "Ion", "Ionuț", "Iorgu", "Iuliu",
      "Liviu", "Luca", "Lucian", "Mihai", "Mihnea", "Mircea", "Nicolae", "Petre", "Petru", "Radu",
      "Sandu", "Sergiu", "Sorin", "Ștefan", "Varujan", "Vasile", "Vasilica");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList("Adelina", "Adina",
      "Adriana",
      "Alexandra", "Alina", "Amalia", "Ana", "Anamaria", "Anastasia", "Anca", "Andrada", "Andreea",
      "Anica", "Antonia", "Aurelia", "Aurora", "Beatrice", "Bianca", "Bianca Maria", "Camelia",
      "Carmen", "Cătălina", "Clara", "Claudia", "Constance", "Corina", "Cornelia", "Cosmina",
      "Cristiana", "Cristina", "Dana", "Daniela", "Daria", "Delia", "Denisa", "Despina", "Diana",
      "Doina", "Dolores", "Dorina", "Dragana", "Ecaterina", "Elena", "Eleonora", "Elvira", "Emilia",
      "Eugenia", "Felicia", "Flavia", "Florentina", "Florina", "Gabriela", "Georgeta", "Georgiana",
      "Ileana", "Ilinca", "Ina", "Ioana", "Iolanda", "Ionela", "Irina", "Irinel", "Isabella",
      "Iulia", "Iuliana", "Juliana", "Larisa", "Laura", "Liana", "Lidia", "Liliana", "Livia",
      "Loredana", "Luana", "Lucia", "Luciana", "Luiza", "Luminița", "Magdalena", "Mara", "Marcela",
      "Margareta", "Maria", "Mariana", "Maricica", "Marina", "Marioara", "Marta", "Melania",
      "Mihaela", "Milena", "Mirela", "Monica", "Nadia", "Nadine", "Natalia", "Natasha", "Nicoleta",
      "Niculina", "Nikolina", "Nina", "Nuta", "Oana", "Olga", "Otilia", "Patricia", "Paula",
      "Phoebe", "Rachel", "Raluca", "Ramona", "Rebeca", "Roberta", "Rodica", "Ruxandra", "Sabina",
      "Sanda", "Sandra", "Silvia", "Simona", "Sonia", "Sophia", "Sorina", "Ștefania", "Stela",
      "Svetlana", "Tamara", "Tatiana", "Teodora", "Valentina", "Valeria", "Veronica", "Victoria",
      "Violeta", "Viorica", "Virginia");
  private static final List<String> LAST_NAMES = Arrays.asList("Albescu", "Albu", "Alexandrescu",
      "Andrei",
      "Anghelescu", "Antonescu", "Ardelean", "Balan", "Bogdan", "Cojocaru", "Constantinescu",
      "Cosmescu", "Craioveanu", "Creţu", "Dalca", "Dascălu", "Dumitrescu", "Dumitru", "Enache",
      "Erner", "Florescu", "Gabor", "Grigorescu", "Grosu", "Hagi", "Iancolescu", "Iliescu",
      "Ionesco", "Ionescu", "Ioveanu", "Kazaku", "Lazar", "Luca", "Lungu", "Lupei", "Mocanu",
      "Negrescu", "Nicolescu", "Olarescu", "Petran", "Petrescu", "Popescu", "Radu", "Roşu", "Sandu",
      "Stefan", "Stoian", "Stoica", "Ungur", "Vaduva", "Vasile", "Vasilescu", "Vladimirescu");


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

  @Override
  public boolean accept(final Country country) {
    return Country.RO == country;
  }
}
