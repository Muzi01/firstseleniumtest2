package bindings.commons.automation.customer.documents.polish;

import com.ipfdigital.automation.api.customer.NipCheckSumCalculator;

final class PolishNipCheckSumCalculatorService implements NipCheckSumCalculator {
  private static final int[] WEIGHT = {6, 5, 7, 2, 3, 4, 5, 6, 7};

  @Override
  public Integer generate(final String object) {
    final int checkSum = getCheckSum(object);
    return checkSum % 11;
  }

  private int getCheckSum(final String nip) {
    int checkSum = 0;

    for (int i = 0; i < WEIGHT.length; i++) {
      checkSum += WEIGHT[i] * Character.getNumericValue(nip.charAt(i));
    }
    return checkSum;
  }


}
