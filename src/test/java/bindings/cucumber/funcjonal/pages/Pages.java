package bindings.cucumber.funcjonal.pages;

public enum Pages {
    CREDIT24_FI("credit24.fi", "credit24.fi", "", "credit24.fi/fi/apply/login"),
    ;

    private final String name;
    private final String prodName;
    private final String applicationAddress;
    private final String applyAddress;

    Pages(final String name, final String prodName, final String applicationAddress,
                    final String applyAddress) {
        this.name = name;
        this.prodName = prodName;
        this.applicationAddress = applicationAddress;
        this.applyAddress = applyAddress;
}
    public String getName() {
        return name;
    }

    public String getProdName() {
        return prodName;
    }

    public String getApplicationAddress() {
        return applicationAddress;
    }

    public String getApplyAddress() {
        return applyAddress;
    }
}