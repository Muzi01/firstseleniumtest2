package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.math.BigInteger;

final class HUIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "HU";
  private static final String BANK_CODE = "100"; // Magyar Államkincstár
  private static final String BRANCH_CODE = "23002"; // Budapest

  @Override
  public String generate() {
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";
    final String accountNumber = generateValidAccountNumber();
    final BigInteger number =
        new BigInteger(BANK_CODE + BRANCH_CODE + accountNumber + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + BANK_CODE + BRANCH_CODE + accountNumber;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.HU == country;
  }

  private int[] createRandomAccountNumber() {
    final int[] accountNumber = new int[7];
    for (int i = 0; i < accountNumber.length; i++) {
      accountNumber[i] = RandomUtils.randomInt(10);
    }
    return accountNumber;
  }

  private String generateValidAccountNumber() {
    final int[] weights = new int[] {9, 7, 3, 1, 9, 7, 3};
    final int[] accountNumber = createRandomAccountNumber();
    final int checksum = calculateChecksum(weights, accountNumber);
    final StringBuilder validAccountNumber = new StringBuilder();
    for (final int anAccountNumber : accountNumber)
      validAccountNumber.append(anAccountNumber);
    validAccountNumber.append(checksum).append("00000000");
    return validAccountNumber.toString();
  }

  private static int calculateChecksum(final int[] weights, final int[] accountNumber) {
    int sum = 0;
    for (int i = 0; i < accountNumber.length; i++)
      sum += weights[i] * accountNumber[i];
    final int lastDigit = sum % 10;
    return 10 - lastDigit == 10 ? 0 : 10 - lastDigit;
  }
}
