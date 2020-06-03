package bindings.commons.automation.config.server;

import com.ipfdigital.automation.config.server.dto.ConfigServerPropertiesDTO;
import feign.Feign;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class ConfigServerProvider {

  private static final Logger LOGGER = LogManager.getLogger(ConfigServerProvider.class);

  private static final String CONFIG_ADDRESS_PARAM = "config.server.address";
  private static final String CONFIG_BRANCH_PARAM = "config.server.branch";
  private static final String CONFIG_APPNAME_PARAM = "config.server.appname";
  private static final String CONFIG_USERNAME_PARAM = "config.server.username";
  private static final String CONFIG_USERNAME_PASS_PARAM = "config.server.username.pass";

  private static final String ERROR_MSG = "Param %s is not set or value is empty";
  private static final String READ_PROPERTIES_ERROR = "Error when read properties";
  private static final String CONFIG_SERVER_SETTINGS_PATH = "/config-server.properties";

  private final ConfigServerClient configServerClient;
  private final String branch;
  private final String appName;
  private final String username;
  private final String pass;
  private final String address;

  public ConfigServerProvider() {
    try {
      final Properties properties = new Properties();
      properties.load(ConfigServerProvider.class.getResourceAsStream(CONFIG_SERVER_SETTINGS_PATH));

      this.address = getAndCheckValueOfParam(properties, CONFIG_ADDRESS_PARAM);
      this.branch = getAndCheckValueOfParam(properties, CONFIG_BRANCH_PARAM);
      this.appName = getAndCheckValueOfParam(properties, CONFIG_APPNAME_PARAM);
      this.username = getAndCheckValueOfParam(properties, CONFIG_USERNAME_PARAM);
      this.pass = getAndCheckValueOfParam(properties, CONFIG_USERNAME_PASS_PARAM);
      this.configServerClient = buildClient();
    } catch (final IOException e) {
      LOGGER.error(READ_PROPERTIES_ERROR, e);
      throw new IllegalStateException(READ_PROPERTIES_ERROR, e);
    }
  }

  public ConfigServerPropertiesDTO getConfigServerProperties(final String env) {
    return this.configServerClient.getConfigServerProperties(this.appName, env, this.branch);
  }

  private String getAndCheckValueOfParam(final Properties properties, final String param) {
    String value = System.getProperty(param);

    if (StringUtils.isBlank(value)) {
      LOGGER.info("Not found value {} in system properties", param);
      value = properties.getProperty(param);
    }

    if (StringUtils.isBlank(value)) {
      final String formattedErrorMsg = String.format(ERROR_MSG, param);
      LOGGER.error(formattedErrorMsg);
      throw new IllegalStateException(formattedErrorMsg);
    }
    return value;
  }

  private ConfigServerClient buildClient() {
    final BasicAuthRequestInterceptor basicAuthRequestInterceptor =
        new BasicAuthRequestInterceptor(this.username, this.pass);
    return Feign.builder()
        .options(new Request.Options())
        .decoder(new JacksonDecoder())
        .logger(new Slf4jLogger())
        .logLevel(feign.Logger.Level.FULL)
        .requestInterceptor(basicAuthRequestInterceptor)
        .target(ConfigServerClient.class, this.address);
  }

}
