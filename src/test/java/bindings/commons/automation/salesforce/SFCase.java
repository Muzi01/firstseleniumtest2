package bindings.commons.automation.salesforce;


public enum SFCase {
  REGISTRATION("", "C24 Registration Case"),
  NEW_APPLICATION("New application", "C24 New Application Case"),
  DRAW("Draw", "C24 Draw Case");

  private final String type;
  private final String recordTypeName;

  SFCase(final String type, final String recordTypeName) {
    this.type = type;
    this.recordTypeName = recordTypeName;
  }

  public String getType() {
    return type;
  }

  public String getRecordTypeName() {
    return recordTypeName;
  }
}
