package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum OccupationFI {
  ENTREPRENEUR(3),
  FULL_TIME(null),
  PART_TIME(null),
  FIXED_TERM(null),
  STUDENT(-3),
  RETIRED(3),
  DISABILITY_RETIRED(3),
  UNEMPLOYED(0);

  protected Integer value;

  OccupationFI(final Integer value) {
    this.value = value;
  }
}
