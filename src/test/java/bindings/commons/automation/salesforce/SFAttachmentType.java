package bindings.commons.automation.salesforce;

public enum SFAttachmentType {
  ADDRESS_PROOF("/documentExamples/ADDRESS_PROOF.jpg"),
  AML_RELATED(null),
  APPLICATION(null),
  BALANCE_SHEET(null),
  BALANCE_SHEET_REQUEST(null),
  BANK_STATEMENT(null),
  BENEFITS(null),
  COLLECTION(null),
  COURT_PAPERS(null),
  CREDIT_LINE_CANCELLATION_DOCUMENT(null),
  DEBT_RESTRUCTURING_PAPERS(null),
  ESTATE_PAPERS(null),
  IBAN_PROOF("/documentExamples/IBAN_PROOF.jpg"),
  ID_CARD(null),
  ID_CARD_A(null),
  ID_CARD_B(null),
  INCOME_PROOF("/documentExamples/INCOME_PROOF.jpg"),
  LEGAL_DOCS(null),
  LOAN_BAN_DOCUMENT(null),
  OTHER(null),
  OTHER_DOCUMENTS(null),
  PASSPORT_A("/documentExamples/PASSPORT_A.jpg"),
  PASSPORT_B("/documentExamples/PASSPORT_B.jpg"),
  PAYMENT_RECEIPT(null),
  PAYMENT_RETURN_DOCUMENT(null),
  PENSION(null),
  POLICE_REPORT(null),
  POWER_OF_ATTORNEY(null),
  SALARY(null),
  SCAN_IMAGE(null),
  SCAN_IMAGE_BACKSIDE(null),
  SCAN_IMAGE_FACE(null),
  SELFIE("/documentExamples/SELFIE.jpg"),
  TERMINATION(null);

  public String getImagePath() {
    return this.imagePath;
  }

  private final String imagePath;

  SFAttachmentType(final String imagePath) {
    this.imagePath = imagePath;
  }

}
