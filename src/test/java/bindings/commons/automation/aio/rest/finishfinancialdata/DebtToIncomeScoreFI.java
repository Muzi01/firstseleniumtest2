package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum DebtToIncomeScoreFI {
  DEBT_TO_INCOME_SCORE1(0.0, 0.71, -1),
  DEBT_TO_INCOME_SCORE2(0.71, 1.0, 13);

  protected Double ratioMin;
  protected Double ratioMax;
  protected Integer scoreValue;

  DebtToIncomeScoreFI(final Double ratioMin, final Double ratioMax, final Integer scoreValue) {
    this.ratioMin = ratioMin;
    this.ratioMax = ratioMax;
    this.scoreValue = scoreValue;
  }
}
