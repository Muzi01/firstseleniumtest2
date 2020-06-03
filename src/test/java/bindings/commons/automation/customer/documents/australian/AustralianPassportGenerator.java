package bindings.commons.automation.customer.documents.australian;

import com.ipfdigital.automation.api.customer.PassportGenerator;
import com.ipfdigital.automation.customer.australian.AustralianPassportDTO;
import com.ipfdigital.automation.generators.RandomUtils;

final class AustralianPassportGenerator implements PassportGenerator {

  private static final char[] SERIES_LETTERS = {'N', 'P'};

  @Override
  public AustralianPassportDTO generate() {
    final char seriesLetter = SERIES_LETTERS[RandomUtils.randomInt(SERIES_LETTERS.length)];
    final int numberLength = RandomUtils.randomInt(2) + 7;
    final String format = "%0" + numberLength + "d";
    final String generatedNumber =
        String.format(format, RandomUtils.randomInt((int) Math.pow(10, numberLength)));
    return new AustralianPassportDTO(seriesLetter + generatedNumber);
  }

}
