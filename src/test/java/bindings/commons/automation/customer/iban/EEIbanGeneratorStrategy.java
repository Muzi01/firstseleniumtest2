package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

final class EEIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "EE";
  private static final String BANK_IDENTIFIER = "42"; // Estonian Credit Bank

  @Override
  public String generate() {
    final String accountNumber = BANK_IDENTIFIER + RandomStringUtils.randomNumeric(11);
    final String accountChecksum = calculateAccountChecksumDigit(accountNumber);
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";

    final BigInteger number =
        new BigInteger(BANK_IDENTIFIER + accountNumber + accountChecksum + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + BANK_IDENTIFIER + accountNumber + accountChecksum;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.EE == country;
  }

  private String calculateAccountChecksumDigit(final String accountNumber) {
    final int[] accountWeights = {7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7};
    int result = 0;
    for (int i = 0; i < accountNumber.length(); ++i) {
      final char character = accountNumber.charAt(i);
      final int weight = accountWeights[i];
      result += Character.getNumericValue(character) * weight;
    }

    final int rounded = result + (10 - result % 10);
    int checkSumDigit = rounded - result;
    if (checkSumDigit == 10) {
      checkSumDigit = 0;
    }

    return Integer.toString(checkSumDigit);
  }
}
