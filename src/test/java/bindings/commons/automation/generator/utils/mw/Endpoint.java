package bindings.commons.automation.generator.utils.mw;

import com.ipfdigital.automation.config.PropertyProviderFactory;
import com.ipfdigital.automation.generator.utils.Environment;

public class Endpoint {

  private Endpoint() {
    throw new IllegalStateException("Endpoint is utility class and should not be instantiate!");
  }

  public static String getLoanEngineEndpoint(final Environment environment,
      final String endpointSurfix) {
    final String key = "endpoint.mw.fi.rest";
    final String mwEndpoint =
        PropertyProviderFactory.propertyProvider(environment.envName)
            .getProperty(key);
    return mwEndpoint + endpointSurfix;
  }
}
