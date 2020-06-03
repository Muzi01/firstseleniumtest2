package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum EmailWithDigit {
  NO(6),
  YES(-1),
  MISSING(-5);

  protected Integer value;

  EmailWithDigit(final Integer value) {
    this.value = value;
  }
}
