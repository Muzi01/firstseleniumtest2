package bindings.commons.automation.config;

import com.ipfdigital.automation.config.server.ConfigServerProvider;
import com.ipfdigital.automation.config.server.dto.ConfigServerPropertiesDTO;
import com.ipfdigital.automation.config.server.dto.PropertySourceDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class PropertyProvider {

  private final Map<String, String> configServerProperties;

  private static final Logger LOGGER = LogManager.getLogger(PropertyProvider.class);

  private static final String NOT_FOUND_ERROR_MSG_TEMPLATE =
      "Not value of key %s in system properties and config server";

  private static final String YML_EXTENSION = "yml";

  PropertyProvider(final String env) {
    this.configServerProperties = new HashMap<>();
    final ConfigServerProvider configServerProvider = new ConfigServerProvider();
    final ConfigServerPropertiesDTO dto =
        configServerProvider.getConfigServerProperties(env.toLowerCase());
    dto.getPropertySources()
        .stream()
        .sorted((s1, s2) -> comparePropertySources(s1, s2, env))
        .forEach(ps -> this.configServerProperties.putAll(ps.getSource()));
  }

  public String getProperty(final String key) {
    final String systemValue = System.getProperty(key);
    if (StringUtils.isNotBlank(systemValue)) {
      LOGGER.debug("Find key {} in system properties", key);
      return systemValue;
    }

    final String configServerValue = this.configServerProperties.get(key);
    if (StringUtils.isNotBlank(configServerValue)) {
      LOGGER.debug("Find key {} in ConfigServer", key);
      return configServerValue;
    }

    final String errorMsg = String.format(NOT_FOUND_ERROR_MSG_TEMPLATE, key);
    LOGGER.error(errorMsg);
    throw new IllegalStateException(errorMsg);

  }

  private int comparePropertySources(final PropertySourceDTO previous, final PropertySourceDTO next,
      final String env) {
    if (previous.getName().contains(env) && next.getName().contains(env)) {
      if (previous.getName().endsWith(YML_EXTENSION)) {
        return 1;
      } else {
        return 0;
      }
    } else {
      return -1;
    }
  }
}
