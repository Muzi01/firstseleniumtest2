package bindings.commons.automation.customer.documents.polish;

import com.ipfdigital.automation.api.customer.NipCheckSumCalculator;
import com.ipfdigital.automation.api.customer.NipGenerator;
import com.ipfdigital.automation.api.customer.TaxOfficeCodesGenerator;

import java.util.Random;

final class NipGeneratorService implements NipGenerator {

  private final NipCheckSumCalculator nipCheckSumCalculator;
  private final TaxOfficeCodesGenerator taxOfficeCodes;

  public NipGeneratorService(final NipCheckSumCalculator nipCheckSumCalculator,
      final TaxOfficeCodesGenerator taxOfficeCodes) {
    this.nipCheckSumCalculator = nipCheckSumCalculator;
    this.taxOfficeCodes = taxOfficeCodes;
  }

  @Override
  public String generate() {
    final StringBuilder nipNumberBuilder = new StringBuilder();
    nipNumberBuilder.append(taxOfficeCodes.generate());
    nipNumberBuilder.append(generateSixRandomNumbers());

    int checksum;
    do {
      // change last number, check sum must be different from 10
      nipNumberBuilder.replace(8, 9, "" + new Random().nextInt(10));
      checksum = nipCheckSumCalculator.generate(nipNumberBuilder.toString());
    } while (checksum == 10);

    nipNumberBuilder.append(checksum);

    return nipNumberBuilder.toString();
  }

  private String generateSixRandomNumbers() {
    final int maxValue = 999999;
    final int minValue = 100000;
    return String.valueOf(new Random().ints(minValue, maxValue).limit(1).findFirst().getAsInt());
  }
}
