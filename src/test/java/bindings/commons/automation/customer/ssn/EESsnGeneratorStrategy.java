package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

final class EESsnGeneratorStrategy implements GenerateSsnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.EE == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    // GYYMMDDSSSC odd number male, even number female, 1-2 19th century, 3-4 20th century, 5-6 21st
    // century
    builder.append(chooseYearChar(dto));
    builder.append(SsnHelper.parseYearAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseMonthAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    final int serial = RandomUtils.randomInt(1000);
    builder.append(String.format(SsnHelper.PARSE_03_TEMPLATE, serial));
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
      return dto.getGender() == Gender.FEMALE ? '2' : '1';
    } else if (dto.getBirthDate().getYear() < 2000) {
      return dto.getGender() == Gender.FEMALE ? '4' : '3';
    } else {
      return dto.getGender() == Gender.FEMALE ? '6' : '5';
    }
  }

  private static int checkSum(final String numbers) {
    final String weights = "1234567891";
    int sum = 0;

    for (int i = 0; i < numbers.length(); ++i) {
      final int weight = Character.getNumericValue(weights.charAt(i));
      final int number = Character.getNumericValue(numbers.charAt(i));
      sum += weight * number;
    }
    return sum % 11;
  }
}
