package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Validator: http://centerkey.com/clabe/
 */
final class MXIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String BANK_NUMBER = "014"; // Santander
  private static final String CITY_NUMBER = "261"; // Acapulco
  private static final int[] weights = {3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7};

  @Override
  public String generate() {
    final String accountNumber = RandomStringUtils.randomNumeric(11);
    final String clabeNumber = BANK_NUMBER + CITY_NUMBER + accountNumber;
    final String checkSum = calculateChecksumDigit(clabeNumber);

    return clabeNumber + checkSum;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.MX == country;
  }

  private String calculateChecksumDigit(final String number) {
    int sum = 0;
    for (int i = 0; i < number.length(); ++i) {
      final char character = number.charAt(i);
      final int digit = Character.getNumericValue(character);
      sum += (digit * weights[i]) % 10;
    }

    final int modSum = sum % 10;
    final int checkSum = (10 - modSum) % 10;
    return Integer.toString(checkSum);
  }
}
