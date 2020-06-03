package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.time.LocalDate;

final class PLSsnGeneratorStrategy implements GenerateSsnStrategy {

  @Override
  public boolean accept(final Country country) {
    return Country.PL == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    // YYMMDDZZZXQ
    builder.append(SsnHelper.parseYearAsString(dto.getBirthDate()));
    builder.append(String.format(SsnHelper.PARSE_02_TEMPLATE, monthDigits(dto.getBirthDate())));
    builder.append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    final int serial = RandomUtils.randomInt(1000);
    builder.append(String.format(SsnHelper.PARSE_03_TEMPLATE, serial));
    builder.append(generateGenderInt(dto.getGender()));
    builder.append(checkSum(builder.toString()));
    return builder.toString();
  }

  private int monthDigits(final LocalDate birhDate) {
    int month = birhDate.getMonthValue();
    if (birhDate.getYear() >= 1800 && birhDate.getYear() < 1900) {
      month += 80;
    } else if (birhDate.getYear() >= 2000 && birhDate.getYear() < 2100) {
      month += 20;
    } else if (birhDate.getYear() >= 2100 && birhDate.getYear() < 2200) {
      month += 40;
    } else if (birhDate.getYear() >= 2200 && birhDate.getYear() < 2300) {
      month += 60;
    }
    return month;
  }

  private int generateGenderInt(final Gender gender) {
    int genderInt = RandomUtils.randomInt(10);

    if (gender == Gender.FEMALE) {
      if (genderInt % 2 != 0) {
        genderInt--;
      }
    } else {
      if (genderInt % 2 == 0) {
        genderInt++;
      }
    }
    return genderInt;
  }

  private int checkSum(final String numbers) {
    final String weights = "1379137913";
    int sum = 0;

    for (int i = 0; i < numbers.length(); ++i) {
      final int weight = Character.getNumericValue(weights.charAt(i));
      final int number = Character.getNumericValue(numbers.charAt(i));
      sum += weight * number;
    }
    return sum % 10 == 0 ? 0 : 10 - (sum % 10);
  }
}
