package bindings.commons.automation.mule.utils.clients;

import com.ipfdigital.automation.mule.utils.Tools;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MuleRestDataPartnerClient extends MuleRestClient {

  private final String dataPartnerUser;
  private final String dataPartnerPassword;


  public MuleRestDataPartnerClient(final String environment) {
    final MulePropertyProvider mulePropertyProvider = new MulePropertyProvider(environment);
    this.baseUrl = mulePropertyProvider.getMulePublicUrlProperty();
    this.dataPartnerUser = mulePropertyProvider.getDataPartnerRestUser();
    this.dataPartnerPassword = mulePropertyProvider.getDataPartnerRestPassword();
  }

  public ResponseEntity sendGetRequest(final String url, final Object responseObject) {

    final RestTemplate restTemplate = new RestTemplate();
    final HttpEntity<?> httpEntity =
        new HttpEntity<>(createHeaders(this.dataPartnerUser, this.dataPartnerPassword));

    return restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseObject.getClass());

  }

  public ResponseEntity sendGetRequest(final String url) {

    final RestTemplate restTemplate = new RestTemplate();
    final HttpEntity<?> httpEntity =
        new HttpEntity<>(createHeaders(this.dataPartnerUser, this.dataPartnerPassword));

    return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

  }

  private HttpHeaders createHeaders(final String username, final String password) {
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", new Tools().getAuthorizationBasic(username, password));

    return httpHeaders;
  }
}
