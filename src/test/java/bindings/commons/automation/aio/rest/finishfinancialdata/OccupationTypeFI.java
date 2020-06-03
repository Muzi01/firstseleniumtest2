package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum OccupationTypeFI {
  EMPLOYEE(2, 6, 6),
  MANAGER(10, 10, -3),
  LOWER_OFFICIAL(3, 3, -3),
  HIGHER_OFFICIAL(10, 10, -3),
  EXECUTIVE(10, 10, -3),
  OTHER(-3, -3, -3);

  protected Integer fullTimeValue;
  protected Integer fixedTermValue;
  protected Integer partTimeValue;

  OccupationTypeFI(final Integer fullTimeValue, final Integer fixedTermValue,
      final Integer partTimeValue) {
    this.fullTimeValue = fullTimeValue;
    this.fixedTermValue = fixedTermValue;
    this.partTimeValue = partTimeValue;
  }
}
