package bindings.commons.automation.customer.debitcards;

import com.ipfdigital.automation.api.customer.DebitCardGenerator;

import java.util.Arrays;
import java.util.List;

import static com.ipfdigital.automation.generators.RandomUtils.randomFromList;
import static com.ipfdigital.automation.generators.RandomUtils.randomLong;

final class MXDebitCardGenerator implements DebitCardGenerator {

  private static final List<Integer> IINS = Arrays.asList(
      // VISA
      450589, // Visa Issued
      421316, 483030, 491089, // HSBC
      409851, 415231, 455511, // Bbva Bancomer
      491566, // Banorte
      441313, // BBVA
      429522, // Banamex
      465828, // Inbursa
      400819, 416916, // Bancoppel
      402766, // Banco Azteca
      474174, // Banregio
      433454, // Ixe Banco S.A.
      421003, // Banbajio
      426808, // Multiva
      // MASTERCARD
      557907, 557909, 557910, // Santander
      533609, // Bancomer
      517712, 520416, 520694, 520698, 525678, 554492, // Banamex
      551238, // Banco Azteca
      534926, 545290, // Mastercard Issued
      557905 // Santander Serfin
  );

  /**
   * Generates a valid (fake) MX debit card number. The number consists of 16 digits:
   * <ul>
   * <li>first 6 digits - Issuer Identification Number</li>
   * <li>9 digits - Account Number</li>
   * <li>last digit - checksum</li>
   * </ul>
   * The checksum digit is calculated using the LUHN formula, also known as Mod 10 calculation.
   * <a href="https://www.creditcardvalidator.org/">https://www.creditcardvalidator.org/</a>
   *
   * @return a String representing a valid MX debit card number
   */

  @Override
  public String generate() {
    final long iin = randomFromList(IINS);
    final long accountNumber = randomLong(1000000000);
    long cardNumber = iin * 1000000000 + accountNumber;
    cardNumber = cardNumber * 10 + checkDigit(cardNumber);
    return String.valueOf(cardNumber);
  }

  /**
   * https://en.wikipedia.org/wiki/Luhn_algorithm
   *
   * @param number a number representing non-check digits
   * @return a check digit for the given number
   */
  private int checkDigit(final long number) {
    int sum = 0;
    final int[] digits = getDigits(number);
    reverseArray(digits);
    for (int i = 0; i < digits.length; i++) {
      if (i % 2 == 0) {
        int d = digits[i] * 2;
        if (d > 9) {
          d -= 9;
        }
        sum += d;
      } else {
        sum += digits[i];
      }
    }
    return sum % 10 == 0 ? 0 : (10 - sum % 10);
  }

  private void reverseArray(final int[] array) {
    for (int i = 0; i < array.length / 2; i++) {
      final int temp = array[i];
      array[i] = array[array.length - i - 1];
      array[array.length - i - 1] = temp;
    }
  }

  private int[] getDigits(long number) {
    if (number == 0)
      return new int[] {0};
    final int[] digits = new int[(int) (Math.log10(number) + 1)]; // number of digits in a number
    int i = digits.length - 1;
    while (number > 0) {
      digits[i] = (int) (number % 10);
      number /= 10;
      i--;
    }
    return digits;
  }

}
