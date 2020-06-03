package bindings.commons.automation.customer.documents.polish;

import com.ipfdigital.automation.api.customer.RegonChecksumGenerator;
import com.ipfdigital.automation.api.customer.RegonGenerator;

import java.util.Random;

final class RegonGeneratorService implements RegonGenerator {

  private final RegonChecksumGenerator checkSumCalculator;

  public RegonGeneratorService(final RegonChecksumGenerator checkSumCalculator) {
    this.checkSumCalculator = checkSumCalculator;
  }

  @Override
  public String generate() {
    final StringBuilder regonNumberBuilder = new StringBuilder();
    regonNumberBuilder.append(generateEightRandomNumbers());
    final int checksum = checkSumCalculator.generate(regonNumberBuilder.toString());
    regonNumberBuilder.append(checksum);

    return regonNumberBuilder.toString();
  }

  private String generateEightRandomNumbers() {
    final int maxValue = 99999999;
    final int minValue = 10000000;
    return String.valueOf(new Random().ints(minValue, maxValue).limit(1).findFirst().getAsInt());
  }
}
