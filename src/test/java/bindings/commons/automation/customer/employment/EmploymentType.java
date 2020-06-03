package bindings.commons.automation.customer.employment;

public enum EmploymentType {

  STUDENT(false),
  STAY_HOME_PARENT(false),
  RETIRED(false),
  OTHER(false),
  UNEMPLOYED(false),

  FULL_TIME(true),
  PART_TIME(true),
  SELF_EMPLOYED(true);

  private final boolean isEmployed;

  EmploymentType(final boolean isEmployed) {
    this.isEmployed = isEmployed;
  }

  public boolean isEmployed() {
    return isEmployed;
  }
}
