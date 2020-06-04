package bindings.cucumber.linkstatusverification;


import java.lang.invoke.MethodHandles;

public enum Country {
    FI("Finland", "Finnish", "+358", "fi"),
    PL("Poland", "Not used", "+48", "pl"),
    LT("Lithuania", "Lithuanian", "+370", "lt"),
    LV("Latvia", "Latvian", "+371", "lv"),
    EE("Estonia", "Not used", "+372", "et"),
    ES("Spain", "Spanish", "+34", "es"),
    MX("Mexico", "Not used", "+52", "es"),
    AU("Australia", "Not used", "+61", "en"),
    CZ("Czech", "Not used", "+420", "cz"),
    HU("Hungary", "Not used", "+36", "hu"),
    RO("Romania", "Not used", "+40", "ro"),
    GL("Global", "Not used", "+00", "gl");

    private final String countryName;
    private final String languageName;
    private final String prefix;
    private final String language;

    private Country(String countryName, String languageName, String prefix, String language) {
        this.countryName = countryName;
        this.languageName = languageName;
        this.prefix = prefix;
        this.language = language;
    }

    public static Country getByCountryCode(String countryCode) {
        Country[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Country country = var1[var3];
            if (country.name().equalsIgnoreCase(countryCode)) {
                return country;
            }
        }

        throw new IllegalStateException("Unable to find country for provided country code: " + countryCode);
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getLanguage() {
        return this.language;
    }

    private static class ConstantValues {
        static final String NOT_USED = "Not used";
        static final String SPANISH = "es";

        private ConstantValues() {
            throw new IllegalStateException(MethodHandles.lookup().lookupClass() + "SHOULD NOT BE INSTANTIATED");
        }
    }
}