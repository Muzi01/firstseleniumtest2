package bindings.cucumber.linkstatusverification;

public enum Brand {
    CREDIT24("credit24.com"),
    SVING("sving.com"),
    HAPI("hapicredito.com"),
    MW("");

    String domain;

    private Brand(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }
}
