package bindings.commons.automation.customer.names;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;
import java.util.List;

final class EENameGeneratorStrategy implements GenerateNameStrategy {

  private static final List<String> MALE_FIRST_NAMES = Arrays.asList(
      "Aleksander", "Alvar", "Andres", "Andrus", "Anton", "Artur", "Eduard", "Edvin", "Georg",
      "Heino", "Hendrik",
      "Hillar", "Jaagup", "Jaak", "Jaakob", "Jaan", "Johannes", "Joosep", "Juhan", "Kalev",
      "Kalju", "Koit",
      "Kristjan", "Mihkel", "Nigul", "Olavi", "Olev", "Oliver", "Paavo", "Peeter", "Priidik",
      "Priit", "Rain",
      "Taavet", "Taavi", "Tarmo", "Tiitus", "Tonis", "Toomas", "Valter", "Villem");
  private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList(
      "Age", "Aleksandra", "Anna", "Anu", "Ave", "Eha", "Eliisabet", "Evelin", "Helena", "Jelena",
      "Johanna",
      "Kadri", "Kaia", "Kaisa", "Kaja", "Katariina", "Kati", "Katrin", "Kristiina", "Lagle",
      "Laine", "Laura",
      "Leelo", "Leena", "Liis", "Liisa", "Liisu", "Loviise", "Luule", "Maarika", "Maarja",
      "Maimu", "Mare",
      "Maret", "Margit", "Mari", "Marika", "Mirjam", "Piia", "Riina", "Sigrid", "Siiri", "Sofia",
      "Terje",
      "Triinu", "Tuule", "Viktoria");
  private static final List<String> LAST_NAMES = Arrays.asList(
      "Tamm", "Saar", "Sepp", "Magi", "Kask", "Kukk", "Rebane", "Ilves", "Parn", "Koppel",
      "Ivanov", "Smirnov",
      "Vassiljev", "Petrov", "Kuznetsov", "Mihhailov", "Pavlov", "Semjonov", "Andrejev",
      "Aleksejev");

  @Override
  public boolean accept(final Country country) {
    return Country.EE == country;
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
