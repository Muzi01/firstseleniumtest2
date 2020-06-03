package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;

final class HUSsnGeneratorStrategy implements GenerateSsnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.HU == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    final StringBuilder builder = new StringBuilder();
    // YYMMDDZZZXQ
    builder.append(SsnHelper.parseYearAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseMonthAsString(dto.getBirthDate()));
    builder.append(SsnHelper.parseDayAsString(dto.getBirthDate()));
    return builder.toString();
  }
}
