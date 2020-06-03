package bindings.commons.automation.generator.utils.scores;

public enum MonitorsPL {
  GDS_M_BIK("GDS_Monitor_BIK"),
  GDS_M_OFFER("GDS_Monitor_OFFER"),
  VCS("VCS");

  private final String name;

  private MonitorsPL(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
