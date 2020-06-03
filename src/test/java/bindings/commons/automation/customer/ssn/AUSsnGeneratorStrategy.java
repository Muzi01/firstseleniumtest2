package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

final class AUSsnGeneratorStrategy implements GenerateSsnStrategy {

  @Override
  public boolean accept(final Country country) {
    return Country.AU == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    int number;
    int checkDigit;
    do {
      number = 10000000 + RandomUtils.randomInt(89999999);
      checkDigit = checkSum(number);
    } while (checkDigit == 10);
    return String.valueOf(number * 10 + checkDigit);
  }

  private int checkSum(final int number) {
    final int[] weights = {1, 4, 3, 7, 5, 8, 6, 9};
    int remainder = number;
    int sum = 0;
    for (int i = 8; i > 0; i--) {
      sum += (remainder % 10) * weights[i - 1];
      remainder /= 10;
    }
    return sum % 11;
  }
}
