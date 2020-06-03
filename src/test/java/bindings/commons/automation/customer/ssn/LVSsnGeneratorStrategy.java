package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

final class LVSsnGeneratorStrategy implements GenerateSsnStrategy {

  @Override
  public boolean accept(final Country country) {
    return Country.LV == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    // DDMMYY-XNNNC
    builder.append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseMonthAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseYearAsString(dto.getBirthDate()));
    builder.append('-');
    builder.append(chooseYearChar(dto));
    builder.append(String.format(SsnHelper.PARSE_03_TEMPLATE, RandomUtils.randomInt(1000)));
    final int checkSum = checkSum(builder.toString());
    builder.append(checkSum);
    if (checkSum == 10) {
      return generate(dto);
    }
    return builder.toString();
  }

  private char chooseYearChar(final GenerateSsnParamsDTO dto) {
    // undefined for year < 1800 and year > 2099
    if (dto.getBirthDate().getYear() < 1900) {
      return '0';
    } else if (dto.getBirthDate().getYear() < 2000) {
      return '1';
    } else {
      return '2';
    }
  }

  private int checkSum(String numbers) {
    final String weights = "1637905842";
    numbers = numbers.replaceAll("-", "");
    int sum = 1101;

    for (int i = 0; i < numbers.length(); ++i) {
      int weight = Character.getNumericValue(weights.charAt(i));
      if (weight == 0) {
        weight = 10;
      }
      final int number = Character.getNumericValue(numbers.charAt(i));
      sum -= weight * number;
    }
    return sum % 11;
  }
}
