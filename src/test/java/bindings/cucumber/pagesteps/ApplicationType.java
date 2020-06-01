package bindings.cucumber.pagesteps;

public enum ApplicationType {
    CREDIT24_FI("credit24.com/fi", "credit24.fi", "", ""),
    CREDIT24_LV("credit24.lv", "credit24.lv", "", ""),
    CREDIT24_EE("credit24.com/ee", "credit24.ee", "credit24.com/ee/application", "credit24.com/ee/apply/home"),
    CREDIT24_LT("credit24.com/lt", "credit24.lt/lt", "", ""),
    HAPI_ES("creditea.es", "creditea.es", "", ""),
    HAPI_PL("hapipozyczki.pl", "hapipozyczki.pl", "", ""),
    HAPI_MX("creditea.mx", "creditea.mx", "", ""),
    HAPI_CZ("creditea.cz", "creditea.cz", "", ""),
    FRONT_ES("credit24.com/es", "", "", ""),
    CREDIT24_AU("credit24.com.au", "https://www.credit24.com.au/", "", ""),
    CREDIT24_AU_IL("credit24.com.au/au/apply-il", "credit24.com.au/au/apply-il", "", "");

    private final String name;
    private final String prodName;
    private final String applicationAddress;
    private final String applyAddress;


    ApplicationType(final String name, final String prodName, final String applicationAddress,
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
