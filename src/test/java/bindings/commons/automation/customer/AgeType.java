package bindings.commons.automation.customer;

public enum AgeType {
  OPTIMAL(22, 64),
  UNDERAGE(10, 17),
  OVERAGE(80, 99);

  private final int minAge;
  private final int maxAge;

  AgeType(final int minAge, final int maxAge) {
    this.minAge = minAge;
    this.maxAge = maxAge;
  }

  public int getMaxAge() {
    return this.maxAge;
  }

  public int getMinAge() {
    return this.minAge;
  }
}
