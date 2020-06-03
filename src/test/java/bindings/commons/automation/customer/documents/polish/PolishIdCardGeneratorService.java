package bindings.commons.automation.customer.documents.polish;

import com.ipfdigital.automation.api.customer.IdCardGenerator;
import com.ipfdigital.automation.customer.AbstractDocumentIdGenerator;

import java.util.Arrays;

public final class PolishIdCardGeneratorService extends AbstractDocumentIdGenerator
    implements IdCardGenerator {
  private static final int RANDOM_DIGITS = 5;
  private static final int RANDOM_LETTERS = 3;
  private static final int[] CHECKSUM_WEIGHTS = {7, 3, 1, 7, 3, 1, 7, 3};

  @Override
  public String generate() {
    final StringBuilder builder = new StringBuilder();
    final char[] letters = generateRandomLetters(RANDOM_LETTERS);
    final int[] digits = generateRandomDigits(RANDOM_DIGITS);
    builder.append(letters);
    builder.append(checksum(letters, digits));
    Arrays.stream(digits).forEach(builder::append);
    return builder.toString();
  }

  private int checksum(final char[] chars, final int[] digits) {
    int sum = 0;
    for (int i = 0; i < chars.length; i++) {
      final int letterValue = chars[i] - 'A' + 10;
      sum += letterValue * CHECKSUM_WEIGHTS[i];
    }
    for (int i = 0; i < digits.length; i++) {
      sum += digits[i] * CHECKSUM_WEIGHTS[RANDOM_LETTERS + i];
    }
    return sum % 10;
  }
}
