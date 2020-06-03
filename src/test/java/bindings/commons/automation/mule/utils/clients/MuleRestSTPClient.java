package bindings.commons.automation.mule.utils.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MuleRestSTPClient extends MuleRestClient {

  public MuleRestSTPClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
  }

  public ResponseEntity sendGetRequest(final String url, final Object responseObject) {
    final RestTemplate restTemplate = new RestTemplate();

    return restTemplate.getForEntity(url, responseObject.getClass());
  }

  public ResponseEntity sendPostRequest(final String url, final Object paramsJson,
      final Object responseObject) {
    final RestTemplate restTemplate = new RestTemplate();
    final HttpEntity<?> httpEntity = new HttpEntity<>(paramsJson);

    return restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseObject.getClass());
  }

}
