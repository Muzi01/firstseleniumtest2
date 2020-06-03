package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

final class LTIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "LT";
  private static final String SWEDBANK_BANK_CODE = "73000";

  @Override
  public String generate() {
    final String bankCode = SWEDBANK_BANK_CODE;
    final String accountNumber = RandomStringUtils.randomNumeric(11);
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";

    final BigInteger number = new BigInteger(bankCode + accountNumber + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + bankCode + accountNumber;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.LT == country;
  }
}
