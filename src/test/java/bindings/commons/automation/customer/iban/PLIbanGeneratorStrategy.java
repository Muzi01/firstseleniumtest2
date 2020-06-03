package bindings.commons.automation.customer.iban;

import com.ipfdigital.automation.customer.Country;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;

/**
 * bank numbers http://www.najlepszekonto.pl/identyfikacja-banku-po-numerze-konta
 */
class PLIbanGeneratorStrategy implements GenerateIbanStrategy {
  private static final String COUNTRY_CODE = "PL";
  private static final String PKO_BANK = "14401390";

  @Override
  public String generate() {
    final String bankCode = PKO_BANK;
    final String accountNumber = RandomStringUtils.randomNumeric(16);
    final String countryCodeNumber = IbanHelper.calculateCodeNumber(COUNTRY_CODE) + "00";

    final BigInteger number = new BigInteger(bankCode + accountNumber + countryCodeNumber);
    final String ibanCheckSum = IbanHelper.calculateIBANchecksum(number);

    return COUNTRY_CODE + ibanCheckSum + bankCode + accountNumber;
  }

  @Override
  public boolean accept(final Country country) {
    return Country.PL == country;
  }
}
