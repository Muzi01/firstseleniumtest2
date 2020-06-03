package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

final class ESIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "ES";
  private static final String BANK_CODE = "2100"; // BANCO DE LA PROVINCIA DE BUENOS AIRES
  private static final int[] firstPartWages = {7, 3, 6, 1, 2, 4, 8, 5};
  private static final int[] secondPartWages = {10, 9, 7, 3, 6, 1, 2, 4, 8, 5};

  @Override
  public String generate() {
    final String bankNumber = BANK_CODE + RandomStringUtils.randomNumeric(4);
    final String accountNumber = RandomStringUtils.randomNumeric(10);
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";
    final String accountChecksum = calculateChecksumDigit(bankNumber, firstPartWages)
        + calculateChecksumDigit(accountNumber, secondPartWages);

    final BigInteger number =
        new BigInteger(bankNumber + accountChecksum + accountNumber + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + bankNumber + accountChecksum + accountNumber;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.ES == country;
  }

  private String calculateChecksumDigit(final String number, final int[] wages) {
    int sum = 0;
    for (int i = 0; i < number.length(); ++i) {
      final char character = number.charAt(i);
      final int weight = wages[i];
      sum += Character.getNumericValue(character) * weight;
    }

    final int result = sum % 11;
    return (result == 10) ? "1" : Integer.toString(result);
  }
}
