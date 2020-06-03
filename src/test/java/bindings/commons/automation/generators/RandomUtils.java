package bindings.commons.automation.generators;

import org.apache.commons.text.RandomStringGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class RandomUtils {

  private RandomUtils() {
    throw new IllegalStateException("Utility classes are not meant to be instantiated.");
  }

  private static final RandomStringGenerator RANDOM_STRING_GENERATOR =
      new RandomStringGenerator.Builder()
          .withinRange('0', 'z')
          .filteredBy(DIGITS, LETTERS)
          .build();

  private static ThreadLocalRandom random() {
    return ThreadLocalRandom.current();
  }

  public static <T> T randomFromList(final List<T> list) {
    return list.get(random().nextInt(list.size()));
  }

  public static <T extends Enum<?>> T randomEnum(final Class<T> enumClass) {
    final T[] elements = enumClass.getEnumConstants();
    return elements[random().nextInt(elements.length)];
  }

  public static int randomInt(final int bound) {
    return random().nextInt(bound);
  }

  public static long randomLong(final long bound) {
    return random().nextLong(bound);
  }

  public static boolean randomBoolean() {
    return random().nextBoolean();
  }

  /**
   * Returns a random birth date, limited by minimum and maximum age.
   * 
   * @param minAge minimum Age
   * @param maxAge maximum Age
   * @return a LocalDate object representing random date of birth
   */
  public static LocalDate randomBirthday(final int minAge, final int maxAge) {
    final LocalDate today = LocalDate.now();
    final int range = maxAge - minAge;
    final int maxDay = today.getDayOfYear() - 1;
    final int year = today.getYear() - (minAge + (range > 0 ? randomInt(range) : 0));
    final int dayOfYear = 1 + randomInt(maxDay);
    return LocalDate.ofYearDay(year, dayOfYear);
  }

  public static String randomAlphanumeric(final int length) {
    return RANDOM_STRING_GENERATOR.generate(length);
  }

}
