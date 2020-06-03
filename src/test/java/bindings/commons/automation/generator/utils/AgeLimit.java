package bindings.commons.automation.generator.utils;

public enum AgeLimit {
  MX(21, 65);

  private int minAge;
  private int maxAge;

  AgeLimit(int minAge, int maxAge) {
    this.minAge = minAge;
    this.maxAge = maxAge;
  }

  public int getMinAge() {
    return minAge;
  }

  public int getMaxAge() {
    return maxAge;
  }
}
