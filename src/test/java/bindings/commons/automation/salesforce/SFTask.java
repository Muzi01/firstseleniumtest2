package bindings.commons.automation.salesforce;

public enum SFTask {
  CHECK_IDENTIFICATION("Check Identification"),
  CHECK_NEW_CUSTOMER("Check New Customer"),
  CHECK_EXISTING_CUSTOMER("Check Existing Customer"),
  PAY_MONEY("Pay money"),
  WAIT_UPLOAD_DOCUMENTS("Wait & upload documents"),
  CHECK_REFERENCE("Check Reference"),
  ADDRESS_IN_USE("Address in use by someone else"),
  BANK_ACCOUNT_IN_USE("Bank account in use by someone else"),
  BANK_REGISTRATION("Bank Registration"),
  AML_RISK("AML risk verification"),
  FREEZE_CONTRACT("Freeze Contract"),
  APPROVAL_NEEDED("Approval needed"),
  SMS("SMS"),
  PHONE_VERIFICATION("Phone verification"),
  DOCUMENTS_VERIFICATION("Documents verification"),
  INVESTIGATION("Investigation");

  public final String subject;

  SFTask(final String subject) {
    this.subject = subject;
  }

  public String getSubject() {
    return this.subject;
  }
}
