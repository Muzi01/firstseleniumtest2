package bindings.commons.automation.salesforce;

public enum SFTaskStatus {

  PENDING("Pending"),
  REJECTED("Rejected");

  private final String name;

  SFTaskStatus(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
