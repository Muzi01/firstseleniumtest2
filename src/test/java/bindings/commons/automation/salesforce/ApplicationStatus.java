package bindings.commons.automation.salesforce;

public enum ApplicationStatus {

  PENDING("Pending"),
  REJECTED("Rejected");

  private final String name;

  ApplicationStatus(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
