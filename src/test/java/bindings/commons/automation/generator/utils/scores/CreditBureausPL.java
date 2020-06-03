package bindings.commons.automation.generator.utils.scores;

public enum CreditBureausPL {
  DELTA_VISTA("DeltaVista_APP"),
  GDS_ERIF("GDS_ERIF"),
  GDS_KRD("GDS_KRD"),
  GDS_IM("GDS_IM"),
  GDS_BIK("GDS_BIK"),
  GDS_OFFER("GDS_OFFER");

  private final String name;

  private CreditBureausPL(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
