package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

/**
 * bank numbers https://www.cnb.cz/en/payment_systems/accounts_bank_codes/
 * https://confluence.ipfdigital.tech/pages/viewpage.action?pageId=100635547
 */
final class CZIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String PREFIX_SEPARATOR = "-";
  private static final String BANK_CODE_SEPARATOR = "/";

  private static final String ACCOUNT_PREFIX = "000000";
  private static final String BANK_CODE = "0710"; // Česká národní banka

  @Override
  public String generate() {
    return ACCOUNT_PREFIX + PREFIX_SEPARATOR + generateValidAccountNumber() + BANK_CODE_SEPARATOR
        + BANK_CODE;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
  }

  private String generateValidAccountNumber() {
    final int[] weights = new int[] {6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    final StringBuilder validAccountNumber = new StringBuilder();
    final long startTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - startTime < 1000) {
      final int[] accountNumber = createRandomAccountNumber();
      if (calculateChecksum(weights, accountNumber) % 11 == 0) {
        for (final int anAccountNumber : accountNumber) {
          validAccountNumber.append(anAccountNumber);
        }
        return validAccountNumber.toString();
      }
    }
    return "2000145399"; // if we fail to generate valid random account number in 1 second, go with
                         // the default valid account number
  }

  private int[] createRandomAccountNumber() {
    final int[] accountNumber = new int[10];
    for (int i = 0; i < accountNumber.length; i++) {
      accountNumber[i] = RandomUtils.randomInt(10);
    }
    return accountNumber;
  }

  private int calculateChecksum(final int[] weights, final int[] accountNumber) {
    int sum = 0;
    for (int i = 0; i < accountNumber.length; i++) {
      sum += weights[i] * accountNumber[i];
    }
    return sum;
  }
}
