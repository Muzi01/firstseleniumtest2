package bindings.commons.automation.customer.iban;

import java.math.BigInteger;

final class IbanHelper {
  private static final BigInteger MODULO_NUMBER = new BigInteger("97");
  private static final BigInteger DIVIDE_NUMBER = new BigInteger("98");

  private IbanHelper() {
  }

  public static String calculateCodeNumber(final String code) {
    final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final StringBuilder result = new StringBuilder();
    for (int j = 0; j < code.length(); ++j) {
      for (int i = 0; i < characters.length(); ++i) {
        if (code.charAt(j) == characters.charAt(i)) {
          result.append(Integer.toString(i + 10));
        }
      }
    }
    return result.toString();
  }

  public static String calculateIBANchecksum(final BigInteger number) {
    final BigInteger modulo = number.mod(MODULO_NUMBER);
    final BigInteger divideRest = DIVIDE_NUMBER.subtract(modulo);
    return (divideRest.intValue() < 10) ? "0" + divideRest.toString() : divideRest.toString();
  }
}
