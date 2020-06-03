package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.generators.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class NumberUtils {
  private NumberUtils() {
    throw new IllegalStateException("NumberUtils is utility class and should not be instantiates!");
  }

  /**
   * Generate random number by provided magnitude For example, for magnitude 10, number will be
   * generated between range 0 and 1000000000
   *
   * @param magnitude - max number length
   * @return random number
   */
  public static long generateRandomNumber(final int magnitude) {
    final long maxNumber = (long) Math.pow(magnitude, magnitude - 1.0f);
    return RandomUtils.randomLong(maxNumber);
  }

  /**
   * Format provided number with leading zeros
   *
   * @param number - number without leading zeros
   * @param length - max number length (if provided number is shorter, following zeros will be
   *        added)
   * @return number with leading zeros
   */
  public static String addLeadingZeros(final long number, final int length) {
    final String parsedNumber = Long.toString(number);
    return StringUtils.leftPad(parsedNumber, length, '0');
  }
}
