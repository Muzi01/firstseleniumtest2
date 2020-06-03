package bindings.commons.automation.aio.rest.finishfinancialdata;

public enum HousingCostScoreFI {
  HOUSING_COST_RANGE1(0, 15000, -2),
  HOUSING_COST_RANGE2(15000, 50000, 0),
  HOUSING_COST_RANGE3(50000, 60000, 3),
  HOUSING_COST_RANGE5(60000, 1000000000, 5);

  protected Integer costMin;
  protected Integer costMax;
  protected Integer scoreValue;

  HousingCostScoreFI(final Integer costMin, final Integer costMax, final Integer scoreValue) {
    this.costMin = costMin;
    this.costMax = costMax;
    this.scoreValue = scoreValue;
  }
}
