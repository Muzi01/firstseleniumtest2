package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum ResidenceFI {
  RENT(-1),
  OWN(4),
  OTHER(0);

  protected Integer value;

  ResidenceFI(final Integer value) {
    this.value = value;
  }
}
