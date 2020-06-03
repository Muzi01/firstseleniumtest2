package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

final class FIIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "FI";
  private static final String BANK_CODE = "8"; // Sampo Pankki Bank

  @Override
  public String generate() {
    final String bankBranchCode =
        BANK_CODE + RandomStringUtils.randomNumeric(6 - BANK_CODE.length());
    final String accountNumber = bankBranchCode + RandomStringUtils.randomNumeric(7);
    final String accountChecksum = calculateAccountChecksumDigit(accountNumber);
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";

    final BigInteger number = new BigInteger(accountNumber + accountChecksum + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + accountNumber + accountChecksum;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.FI == country;
  }

  // https://en.wikipedia.org/wiki/Luhn_algorithm
  private String calculateAccountChecksumDigit(final String accountNumber) {
    final int[] wages = {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};

    int sum = 0;
    for (int i = 0; i < accountNumber.length(); ++i) {
      final int character = accountNumber.charAt(i);
      final int digit = Character.getNumericValue(character);
      final int multiplyResult = digit * wages[i];
      sum += (multiplyResult >= 10) ? multiplyResult - 9 : multiplyResult;
    }

    final int result = (sum * 9) % 10;
    return Integer.toString(result);
  }
}
