package bindings.commons.automation.customer.documents.polish;

import com.ipfdigital.automation.api.customer.RegonChecksumGenerator;

import static java.lang.Character.getNumericValue;

final class PolishRegonCheckSumCalculatorService implements RegonChecksumGenerator {
  private static final int[] WEIGHT = {8, 9, 2, 3, 4, 5, 6, 7};

  @Override
  public Integer generate(final String nip) {
    final int checkSum = getCheckSum(nip) % 11;
    return (checkSum == 10) ? 0 : checkSum;
  }

  private int getCheckSum(final String regon) {
    int checkSum = 0;
    for (int i = 0; i < WEIGHT.length; i++) {
      checkSum += WEIGHT[i] * getNumericValue(regon.charAt(i));
    }
    return checkSum;
  }


}
