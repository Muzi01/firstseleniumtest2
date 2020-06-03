package bindings.commons.automation.customer.ssn;

import java.time.LocalDate;

final class SsnHelper {

  private SsnHelper() {
  }

  public static final String PARSE_02_TEMPLATE = "%02d";
  public static final String PARSE_03_TEMPLATE = "%03d";

  public static String parseYearAsString(final LocalDate birthDate) {
    return String.format(PARSE_02_TEMPLATE, birthDate.getYear() % 100);
  }

  public static String parseMonthAsString(final LocalDate birthDate) {
    return String.format(PARSE_02_TEMPLATE, birthDate.getMonthValue());
  }

  public static String parseDayAsString(final LocalDate birthDate) {
    return String.format(PARSE_02_TEMPLATE, birthDate.getDayOfMonth());
  }
}
