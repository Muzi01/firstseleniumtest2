package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

import java.time.LocalDate;

final class FISsnGeneratorStrategy implements GenerateSsnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.FI == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    // DDMMYYCZZZQ
    builder.append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseMonthAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseYearAsString(dto.getBirthDate()));
    builder.append(chooseYearChar(dto.getBirthDate()));
    final int genderInt = generateGenderInt(dto.getGender());
    builder.append(String.format(SsnHelper.PARSE_03_TEMPLATE, genderInt));
    builder.append(calculateChecksum(genderInt, dto.getBirthDate()));
    return builder.toString();
  }

  private char chooseYearChar(final LocalDate birthDate) {
    // undefined for year < 1800 and year > 2099
    if (birthDate.getYear() < 1900) {
      return '+';
    } else if (birthDate.getYear() < 2000) {
      return '-';
    } else {
      return 'A';
    }
  }

  private int generateGenderInt(final Gender gender) {
    int genderInt = 2 + RandomUtils.randomInt(897);

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

  private char calculateChecksum(final int genderInt, final LocalDate birthDate) {
    final String checkChars = "0123456789ABCDEFHJKLMNPRSTUVWXY";
    final int checksumIndex = (birthDate.getDayOfMonth() * 10000000
        + birthDate.getMonthValue() * 100000
        + (birthDate.getYear() % 100) * 1000
        + genderInt) % 31;
    return checkChars.charAt(checksumIndex);
  }
}
