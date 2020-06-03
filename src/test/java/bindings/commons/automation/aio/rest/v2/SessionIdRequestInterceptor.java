package bindings.commons.automation.aio.rest.v2;

import com.ipfdigital.automation.customer.Country;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;
import java.util.List;

public class SessionIdRequestInterceptor implements HttpRequestInterceptor {

  private static final String SESSION_HEADER_NAME = "Cookie";
  private static final String COUNTRY_HEADER_NAME = "x-country";
  private static final String BRAND_HEADER_NAME = "x-brand";
  private static final String LANGUAGE_HEADER_NAME = "x-language";
  private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
  private static final String CONTENT_TYPE_HEADER_DEFAULT_VALUE = "application/json;charset=utf-8";

  private final String brand;
  private final Country country;
  private final SessionStore sessionStore;

  private final List<NoAddSessionIdItem> endpointsNotRequireSessionList = Arrays.asList(
      new NoAddSessionIdItem("/loanengine/rest/password-recovery/send-email", "POST"),
      new NoAddSessionIdItem("/loanengine/rest/authentication", "POST"));

  SessionIdRequestInterceptor(final String brand, final Country country,
      final SessionStore sessionStore) {
    this.brand = brand;
    this.country = country;
    this.sessionStore = sessionStore;
  }

  @Override
  public void process(final HttpRequest httpRequest, final HttpContext httpContext) {
    clearSessionIfNewAuthentication(httpRequest);
    httpRequest.addHeader(COUNTRY_HEADER_NAME, String.valueOf(this.country));
    httpRequest.addHeader(BRAND_HEADER_NAME, this.brand);
    httpRequest.addHeader(LANGUAGE_HEADER_NAME, this.country.getLanguage().toLowerCase());
    if (shouldAddSessionId(httpRequest.getRequestLine().getUri(),
        httpRequest.getRequestLine().getMethod())) {
      httpRequest.addHeader(SESSION_HEADER_NAME, this.sessionStore.getSessionId());
    }

    if (shouldSetContentType(httpRequest)) {
      httpRequest.addHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_DEFAULT_VALUE);
    }

    httpContext.setAttribute(RestConstants.CURRENT_ENDPOINT_ID,
        httpRequest.getRequestLine().getUri());
  }

  private void clearSessionIfNewAuthentication(final HttpRequest httpRequest) {
    if (httpRequest.getRequestLine().getUri().contains("/authentication/impersonation")) {
      this.sessionStore.clear();
    }
  }

  private boolean shouldSetContentType(final HttpRequest request) {
    return request.getFirstHeader(CONTENT_TYPE_HEADER_NAME) == null;
  }

  private boolean shouldAddSessionId(final String uri, final String method) {
    return this.endpointsNotRequireSessionList
        .stream()
        .noneMatch(item -> item.getUri().equals(uri) && item.getMethod().equals(method))
        && this.sessionStore.isSessionIdAvailable();
  }
}
