package bindings.commons.automation.generator.utils.grt;

public enum GrtMaritalStatus {
  MARRIED("V"),
  DIVORCED("I"),
  WIDOWED("N"),
  NA("");
  final String value;

  GrtMaritalStatus(String v) {
    value = v;
  }
}
