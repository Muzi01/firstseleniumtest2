package bindings.commons.automation.customer.iban;


// https://www.ecbs.org/iban/romania-bank-account-number.html

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

final class ROIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "RO";
  private static final String BANK_CODE = "BTRL"; // Banca Transilvania s.a.
  private static final String ACCOUNT_NUMBER = RandomStringUtils.randomNumeric(16);

  @Override
  public String generate() {
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";
    final String bankAccountNumber = IbanHelper.calculateCodeNumber(BANK_CODE) + ACCOUNT_NUMBER;
    final String bankAccountNumberForIBAN = bankAccountNumber + countryCodeNumber;
    final BigInteger number = new BigInteger(bankAccountNumberForIBAN);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + BANK_CODE + ACCOUNT_NUMBER;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.RO == country;
  }

}
