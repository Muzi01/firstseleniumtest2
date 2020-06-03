package bindings.commons.automation;

public enum DocumentsConstants {
  ACCOUNT("ACCOUNT"),
  IMAGE_JPG_TYPE("image/jpeg"),
  JPG("jpg");

  DocumentsConstants(final String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  String value;

}
