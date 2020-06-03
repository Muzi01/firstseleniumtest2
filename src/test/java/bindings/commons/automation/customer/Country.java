package bindings.commons.automation.customer;

import java.lang.invoke.MethodHandles;

public enum Country {
  FI("Finland", "Finnish", "+358", "fi"),
  PL("Poland", ConstantValues.NOT_USED, "+48", "pl"),
  LT("Lithuania", "Lithuanian", "+370", "lt"),
  LV("Latvia", "Latvian", "+371", "lv"),
  EE("Estonia", ConstantValues.NOT_USED, "+372", "et"),
  ES("Spain", "Spanish", "+34", ConstantValues.SPANISH),
  MX("Mexico", ConstantValues.NOT_USED, "+52", ConstantValues.SPANISH),
  AU("Australia", ConstantValues.NOT_USED, "+61", "en"),
  CZ("Czech", ConstantValues.NOT_USED, "+420", "cs"),
  HU("Hungary", ConstantValues.NOT_USED, "+36", "hu"),
  RO("Romania", ConstantValues.NOT_USED, "+40", "ro"),
  GL("Global", ConstantValues.NOT_USED, "+00", "gl");

  private final String countryName;
  private final String languageName;
  private final String prefix;
  private final String language;

  Country(final String countryName, final String languageName, final String prefix,
      final String language) {
    this.countryName = countryName;
    this.languageName = languageName;
    this.prefix = prefix;
    this.language = language;
  }

  public static Country getByCountryCode(final String countryCode) {
    for (final Country country : Country.values()) {
      if (country.name().equalsIgnoreCase(countryCode)) {
        return country;
      }
    }

    throw new IllegalStateException(
        "Unable to find country for provided country code: " + countryCode);
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
      throw new IllegalStateException(
          MethodHandles.lookup().lookupClass() + "SHOULD NOT BE INSTANTIATED");
    }
  }

}
