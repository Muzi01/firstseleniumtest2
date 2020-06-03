package bindings.commons.automation.mule.utils.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MuleRestMessenteClient extends MuleRestClient {

  public MuleRestMessenteClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMulePublicUrlProperty();
  }

  public ResponseEntity sendGetRequest(final String url) {

    final RestTemplate restTemplate = new RestTemplate();

    return restTemplate.getForEntity(url, String.class);

  }

}
