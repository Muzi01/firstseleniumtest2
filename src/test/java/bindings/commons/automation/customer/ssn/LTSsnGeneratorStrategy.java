package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

final class LTSsnGeneratorStrategy implements GenerateSsnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.LT == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    // G YYMMDD NNN C
    builder.append(generateGenderInt(dto));
    builder.append(SsnHelper.parseYearAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseMonthAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    final int serial = RandomUtils.randomInt(1000);
    builder.append(String.format(SsnHelper.PARSE_03_TEMPLATE, serial));
    builder.append(checkSum(builder.toString()));
    return builder.toString();
  }

  private int generateGenderInt(final GenerateSsnParamsDTO dto) {
    final int genderInt = dto.getGender() == Gender.FEMALE ? 0 : 1;
    return (dto.getBirthDate().getYear() / 100) * 2 - 34 - genderInt;
  }

  private static int checkSum(final String code) {
    final int firstPart = calculateChecksumPart(code, 1) % 11;
    final int secondPart = calculateChecksumPart(code, 3) % 11;
    if (firstPart < 10) {
      return firstPart;
    } else if (secondPart < 10) {
      return secondPart;
    } else {
      return 0;
    }
  }

  private static int calculateChecksumPart(final String code, int magicNumber) {
    int digit;
    int result = 0;
    for (int i = 0; i < 10; i++) {
      digit = Integer.parseInt(code.substring(i, i + 1));
      result += digit * magicNumber;
      magicNumber++;
      if (magicNumber == 10)
        magicNumber = 1;
    }
    return result;
  }

}
