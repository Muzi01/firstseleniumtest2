package bindings.commons.automation.salesforce;

public enum SFRecord {
  ACCOUNT("Account"),
  CREDIT_APPLICATION("MCBCreditApplication__c"),
  ATTACHMENT("Attachment__c"),
  MCB_CREDIT_APP("MCBCreditApplication__c"),
  EXTERNAL_VERIFICATION_REPORT("External_Verification_Report__c"),
  TASK("Task"),
  CASE("Case");

  public final String name;

  SFRecord(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
