package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum AgeScoreFI {
  AGE_RANGE1(18, 20, -1),
  AGE_RANGE2(21, 22, 0),
  AGE_RANGE3(23, 40, 3),
  AGE_RANGE4(41, 51, 4),
  AGE_RANGE5(52, 80, 7);

  protected Integer ageMin;
  protected Integer ageMax;
  protected Integer scoreValue;

  AgeScoreFI(final Integer ageMin, final Integer ageMax, final Integer scoreValue) {
    this.ageMin = ageMin;
    this.ageMax = ageMax;
    this.scoreValue = scoreValue;
  }
}
