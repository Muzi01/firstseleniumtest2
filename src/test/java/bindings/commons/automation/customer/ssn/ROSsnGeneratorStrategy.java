package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

final class ROSsnGeneratorStrategy implements GenerateSsnStrategy {

  private static final String PLACE_OF_BIRTH = "42"; // valid values: "01" to "46", "51","52"

  @Override
  public boolean accept(final Country country) {
    return Country.RO == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder ssn = new StringBuilder();
    ssn
        .append(findGenderDigit(dto))
        .append(SsnHelper.parseYearAsString(dto.getBirthDate()))
        .append(SsnHelper.parseMonthAsString(dto.getBirthDate()))
        .append(SsnHelper.parseDayAsString(dto.getBirthDate()))
        .append(PLACE_OF_BIRTH)
        .append(String.format(SsnHelper.PARSE_03_TEMPLATE, RandomUtils.randomInt(1000)))
        .append(calculateCheckSum(ssn.toString()));
    return ssn.toString();
  }

  private int calculateCheckSum(final String ssn) {
    final int[] weights = new int[] {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
    final int[] number = convertStringToIntArray(ssn);

    int sumOfProducts = 0;
    for (int i = 0; i < number.length; i++) {
      sumOfProducts += weights[i] * number[i];
    }

    return sumOfProducts % 11 == 10 ? 1 : sumOfProducts % 11;
  }

  private String findGenderDigit(final GenerateSsnParamsDTO dto) {
    // 1 digit for the sex of the Person. 1=Male 2=Female before 2000, 5=Male 6=Female after 2000
    // (inclusive)
    if (dto.getBirthDate().getYear() < 2000) {
      return dto.getGender() == Gender.MALE ? "1" : "2";
    } else {
      return dto.getGender() == Gender.MALE ? "5" : "6";
    }
  }

  private int[] convertStringToIntArray(final String number) {
    final int[] array = new int[12];
    for (int i = 0; i < number.length(); i++) {
      array[i] = Character.getNumericValue(number.charAt(i));
    }
    return array;
  }
}
