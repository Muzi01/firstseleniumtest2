package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.utils.Tools;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MuleRestSFDCClient extends MuleRestClient {

  private final String salesforceUser;
  private final String salesforcePassword;


  public MuleRestSFDCClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMulePublicUrlProperty();
    this.salesforceUser = mulePropertyProvider.getDefaultSfRestUser();
    this.salesforcePassword = mulePropertyProvider.getDefaultSfRestPassword();
  }

  public ResponseEntity sendGetRequest(final String url, final Object responseObject) {
    final RestTemplate restTemplate = new RestTemplate();
    final HttpEntity<?> httpEntity =
        new HttpEntity<>(createHeaders(this.salesforceUser, this.salesforcePassword));

    return restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseObject.getClass());

  }

  public ResponseEntity sendPostRequest(final String url, final Object paramsJson,
      final Object responseObject) {

    final RestTemplate restTemplate = new RestTemplate();
    final HttpEntity<?> httpEntity =
        new HttpEntity<>(paramsJson, createHeaders(this.salesforceUser, this.salesforcePassword));

    return restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseObject.getClass());

  }

  private HttpHeaders createHeaders(final String username, final String password) {
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", new Tools().getAuthorizationBasic(username, password));

    return httpHeaders;
  }
}
