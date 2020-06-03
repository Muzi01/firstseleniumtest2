package bindings.commons.automation.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PropertyProviderFactory {

  private static final ConcurrentMap<String, PropertyProvider> propertyProviderMap =
      new ConcurrentHashMap<>();

  private PropertyProviderFactory() {
  }

  public static PropertyProvider propertyProvider(final String env) {
    return propertyProviderMap.computeIfAbsent(env.toLowerCase(), PropertyProvider::new);
  }
}
