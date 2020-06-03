package bindings.commons.automation.config.server;

import com.ipfdigital.automation.config.server.dto.ConfigServerPropertiesDTO;
import feign.Param;
import feign.RequestLine;

interface ConfigServerClient {

  @RequestLine("GET /{application-name}/{env}/{branch}")
  ConfigServerPropertiesDTO getConfigServerProperties(
      @Param("application-name") String applicationName, @Param("env") String env,
      @Param("branch") String branchName);

}
