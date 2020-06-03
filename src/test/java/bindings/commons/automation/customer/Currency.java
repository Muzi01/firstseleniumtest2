package bindings.commons.automation.customer;

import java.lang.invoke.MethodHandles;

public enum Currency {

  FI(ConstantValues.EURO_SIGN),
  PL("zł"),
  LT(ConstantValues.EURO_SIGN),
  LV(ConstantValues.EURO_SIGN),
  EE(ConstantValues.EURO_SIGN),
  ES(ConstantValues.EURO_SIGN),
  MX(ConstantValues.DOLLAR_SIGN),
  AU(ConstantValues.DOLLAR_SIGN),
  CZ("Kč"),
  HU("Ft"),
  RO("L");

  private final String currencySign;

  Currency(final String currencySign) {
    this.currencySign = currencySign;
  }

  public String getCurrencySign() {
    return currencySign;
  }

  private static class ConstantValues {

    static final String EURO_SIGN = "€";
    static final String DOLLAR_SIGN = "$";

    private ConstantValues() {
      throw new IllegalStateException(
          MethodHandles.lookup().lookupClass() + "SHOULD NOT BE INSTANTIATED");
    }
  }

}
