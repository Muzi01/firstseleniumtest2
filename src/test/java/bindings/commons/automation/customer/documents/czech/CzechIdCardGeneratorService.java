package bindings.commons.automation.customer.documents.czech;

import com.ipfdigital.automation.api.customer.IdCardGenerator;
import com.ipfdigital.automation.customer.AbstractDocumentIdGenerator;

import java.util.Arrays;

public final class CzechIdCardGeneratorService extends AbstractDocumentIdGenerator
    implements IdCardGenerator {
  private static final int RANDOM_DIGITS = 6;
  private static final int RANDOM_LETTERS = 2;

  @Override
  public String generate() {
    final StringBuilder builder = new StringBuilder();
    final char[] letters = generateRandomLetters(RANDOM_LETTERS);
    final int[] digits = generateRandomDigits(RANDOM_DIGITS);
    builder.append(letters);
    Arrays.stream(digits).forEach(builder::append);
    return builder.toString();
  }


}
