package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.config.PropertyProviderFactory;

public enum Environment {
  SIT("sit"),
  ST02("st02"),
  ST03("st03"),
  ST04("st04"),
  ST05("st05"),
  ST06("st06"),
  ST07("st07"),
  ST08("st08"),
  ST09("st09"),
  ST10("st10"),
  ST11("st11"),
  ST13("st13"),
  UAT("uat"),
  UAT2("uat2"),
  DT01("dt01"),
  PROD("prod");

  public final String envName;

  private static final String WS_ENDPOINT_PROPERTY_NAME = "ws.endpoint";

  Environment(final String envName) {
    this.envName = envName;
  }

  public static Environment forName(final String name) {
    for (final Environment env : values()) {
      if (env.envName.equalsIgnoreCase(name)) {
        return env;
      }
    }
    throw new IllegalArgumentException("No environments configured for name: " + name);
  }

  private static final String MISSING_CONFIG_MESSAGE =
      "%s config is missing for %s environment! Make sure to configure %s system property.";

  public SOAPUtils getSoapUtils() {
    return new SOAPUtils(
        PropertyProviderFactory.propertyProvider(envName)
            .getProperty(WS_ENDPOINT_PROPERTY_NAME));
  }
}
