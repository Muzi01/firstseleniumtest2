package bindings.commons.automation.generator.utils.scores;

public enum CreditBureauAU {
  VEDA("Veda"),
  DNB("DNB"),
  ID_MATRIX("IDMATRIX"),
  GREEN_ID("GREENID");

  private final String name;

  CreditBureauAU(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
