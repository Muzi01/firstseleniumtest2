package bindings.commons.automation.aio.rest.v2;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class SessionIdResponseInterceptor implements HttpResponseInterceptor {
  private static final String JSESSIONID_HEADER_NAME = "JSESSIONID";
  private static final String AWSELB_HEADER_NAME = "AWSELB";
  private static final String SET_COOKIE_HEADER_NAME = "Set-Cookie";
  private static final String HEADER_SEPARATOR = ";";
  private static final Logger LOG = LogManager.getLogger(SessionIdResponseInterceptor.class);

  private final SessionStore sessionStore;

  SessionIdResponseInterceptor(final SessionStore sessionStore) {
    this.sessionStore = sessionStore;
  }

  @Override
  public void process(final HttpResponse httpResponse, final HttpContext httpContext) {
    if (!this.sessionStore.isSessionIdAvailable()) {
      final String newSessionId = findSessionId(Arrays.asList(httpResponse.getAllHeaders()));
      LOG.info("Update session id {}", newSessionId);
      this.sessionStore.updateSessionId(newSessionId);
    }
  }

  private String findSessionId(final List<Header> headers) {
    return headers
        .stream()
        .filter(header -> StringUtils.equalsIgnoreCase(header.getName(), SET_COOKIE_HEADER_NAME))
        .findFirst()
        .map(this::parseSessionHeader)
        .orElse(null);
  }

  private String parseSessionHeader(final Header header) {
    if (StringUtils.isBlank(header.getValue())) {
      throw new IllegalStateException("Not found session header with value");
    }

    return Arrays.stream(header.getValue().split(HEADER_SEPARATOR))
        .filter(this::isSessionIdHeader)
        .findFirst()
        .orElseThrow(
            () -> new IllegalStateException("Can't parse session header " + header.getName()));

  }

  private boolean isSessionIdHeader(final String splitedValue) {
    return StringUtils.containsIgnoreCase(splitedValue, JSESSIONID_HEADER_NAME)
        || StringUtils.containsIgnoreCase(splitedValue, AWSELB_HEADER_NAME);
  }
}
