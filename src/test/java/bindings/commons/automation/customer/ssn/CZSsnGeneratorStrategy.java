package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;
import com.ipfdigital.automation.generators.RandomUtils;

final class CZSsnGeneratorStrategy implements GenerateSsnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.CZ == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    builder
        .append(SsnHelper.parseYearAsString(dto.getBirthDate()))
        .append(String.format(SsnHelper.PARSE_02_TEMPLATE, monthDigits(dto)))
        .append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    final String serial =
        String.format(SsnHelper.PARSE_03_TEMPLATE, RandomUtils.randomInt(999) + 1);
    if (dto.getBirthDate().getYear() >= 1954) {
      builder.append(checkSum(builder.toString(), serial));
    } else {
      builder.append(serial);
    }
    return builder.toString();
  }

  private int monthDigits(final GenerateSsnParamsDTO dto) {
    int month = dto.getBirthDate().getMonthValue();

    if (dto.getGender().equals(Gender.FEMALE)) {
      month += 50;
    }
    return month;
  }

  private String checkSum(final String numbers, final String serial) {
    final String number = numbers + serial;
    int modulo = (int) (Long.parseLong(number) % 11);
    int serialTemp = Integer.parseInt(serial);
    if (modulo == 10) { // exception handle, in case of result of modulo(11) is 10, we need to
      // decrease the serial number by 1, so the number modulo(11) = 9
      serialTemp--;
      modulo = 9;
    }
    return String.format("%03d%s", serialTemp, String.valueOf(modulo));
  }
}
