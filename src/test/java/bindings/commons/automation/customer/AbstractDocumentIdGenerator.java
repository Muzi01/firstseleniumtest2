package bindings.commons.automation.customer;

import java.util.Random;

public abstract class AbstractDocumentIdGenerator {
  private static final Random RANDOM = new Random();

  protected int[] generateRandomDigits(final int randomDigits) {
    final int[] digits = new int[randomDigits];
    for (int i = 0; i < digits.length; i++) {
      digits[i] = RANDOM.nextInt(10);
    }
    return digits;
  }

  protected char[] generateRandomLetters(final int randomLetters) {
    final char[] chars = new char[randomLetters];
    for (int i = 0; i < chars.length; i++) {
      chars[i] = (char) (RANDOM.nextInt(26) + 'A');
    }
    return chars;
  }
}
