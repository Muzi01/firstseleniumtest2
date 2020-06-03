package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

final class LVIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "LV";
  private static final String BANK_CODE = "PARX";

  @Override
  public String generate() {
    final String bankCodeNumber = IbanHelper.calculateCodeNumber(BANK_CODE);
    final String accountNumber = RandomStringUtils.randomNumeric(13);
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";

    final BigInteger number = new BigInteger(bankCodeNumber + accountNumber + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + BANK_CODE + accountNumber;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.LV == country;
  }
}
