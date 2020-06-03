package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generators.RandomUtils;

import java.util.Arrays;

final class ESSsnGeneratorStrategy implements GenerateSsnStrategy {
  @Override
  public boolean accept(final Country country) {
    return Country.ES == country;
  }

  @Override
  public String generate(final GenerateSsnParamsDTO dto) {
    if (dto.getSsnType() == SsnType.DNI) {
      return generateDNI();
    } else if (dto.getSsnType() == SsnType.NIE) {
      return generateNIE();
    } else {
      throw new IllegalArgumentException(
          "SSN Type must be declared for Spain SSN generation... add .setDNI() or .setNIE()");
    }
  }

  private String generateDNI() {
    final StringBuilder builder = new StringBuilder();
    builder.append(String.format("%08d", RandomUtils.randomInt(100000000)));
    builder.append(checkSumES(builder.toString()));
    return builder.toString();
  }

  private String generateNIE() {
    final StringBuilder builder = new StringBuilder();
    final String initialLetter = RandomUtils.randomFromList(Arrays.asList("X", "Y", "Z"));
    builder.append(initialLetter);
    builder.append(String.format("%07d", RandomUtils.randomInt(10000000)));
    builder.append(checkSumES(builder.toString()
        .replaceAll("X", "0")
        .replaceAll("Y", "1")
        .replaceAll("Z", "2")));
    return builder.toString();
  }

  static char checkSumES(final String code) {
    if (code.length() != 8)
      return '-';
    final String checkChars = "TRWAGMYFPDXBNJZSQVHLCKET";
    final int checkChar = Integer.parseInt(code) % 23;
    return checkChars.charAt(checkChar);
  }
}
