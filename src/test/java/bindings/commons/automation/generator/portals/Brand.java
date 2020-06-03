package bindings.commons.automation.generator.portals;

public enum Brand {
  CREDIT24("credit24.com"),
  SVING("sving.com"),
  HAPI("hapicredito.com"),
  CREDITEA(""),
  MW("");

  String domain;

  Brand(final String domain) {
    this.domain = domain;
  }

  public String getDomain() {
    return this.domain;
  }
}
