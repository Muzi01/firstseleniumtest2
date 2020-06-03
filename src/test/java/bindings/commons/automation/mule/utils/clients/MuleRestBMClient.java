package bindings.commons.automation.mule.utils.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class MuleRestBMClient extends MuleRestClient {

  public MuleRestBMClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMulePublicUrlProperty();
  }

  public ResponseEntity sendGetRequest(final String url) {
    final RestTemplate restTemplate = new RestTemplate();

    return restTemplate.getForEntity(url, String.class);
  }

  public ResponseEntity sendPostRequestQueryParams(final String url,
      final Map<String, String> parameters) {
    final RestTemplate restTemplate = new RestTemplate();

    return restTemplate.postForEntity(url, parameters, String.class);
  }

}
