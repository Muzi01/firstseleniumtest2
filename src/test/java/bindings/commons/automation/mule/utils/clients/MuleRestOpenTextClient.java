package bindings.commons.automation.mule.utils.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MuleRestOpenTextClient extends MuleRestClient {

  public MuleRestOpenTextClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMuleInternalUrlProperty();
  }

  public ResponseEntity sendPutRequest(final String url) {

    final RestTemplate restTemplate = new RestTemplate();
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    final HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);


  }
}
