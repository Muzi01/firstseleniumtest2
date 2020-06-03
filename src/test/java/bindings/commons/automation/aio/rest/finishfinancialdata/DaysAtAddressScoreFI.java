package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum DaysAtAddressScoreFI {
  DAYS_AT_ADDRESS_SCORE1(0, 165, -7),
  DAYS_AT_ADDRESS_SCORE2(166, 847, 0),
  DAYS_AT_ADDRESS_SCORE3(848, 3250, 4),
  DAYS_AT_ADDRESS_SCORE4(3251, 5200, 8),
  DAYS_AT_ADDRESS_SCORE5(5201, 7260, 13),
  DAYS_AT_ADDRESS_SCORE6(7261, 10000000, 21);

  protected Integer ratioMin;
  protected Integer ratioMax;
  protected Integer scoreValue;

  DaysAtAddressScoreFI(final Integer ratioMin, final Integer ratioMax, final Integer scoreValue) {
    this.ratioMin = ratioMin;
    this.ratioMax = ratioMax;
    this.scoreValue = scoreValue;
  }
}
