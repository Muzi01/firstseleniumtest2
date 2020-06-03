package bindings.commons.automation.aio.rest.dto;

public enum BankAccountDuration {
  YEARS_LESS_1(-26),
  YEARS_1_3(-4),
  YEARS_OVER_3(12);

  protected Integer scoreValue;

  BankAccountDuration(final Integer scoreValue) {
    this.scoreValue = scoreValue;
  }
}
