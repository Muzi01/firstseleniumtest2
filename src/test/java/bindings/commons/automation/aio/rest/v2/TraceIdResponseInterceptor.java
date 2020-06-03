package bindings.commons.automation.aio.rest.v2;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class TraceIdResponseInterceptor implements HttpResponseInterceptor {

  private static final String TRACE_ID_HEADER_NAME = "traceid";

  private final Optional<AIORestEventListener> listener;

  public TraceIdResponseInterceptor(final Optional<AIORestEventListener> listener) {
    this.listener = listener;
  }

  @Override
  public void process(final HttpResponse response, final HttpContext context)
      throws HttpException, IOException {
    final AIORestEvent restEvent = new AIORestEvent(
        findTraceId(response),
        String.valueOf(context.getAttribute(RestConstants.CURRENT_ENDPOINT_ID)));
    this.listener.ifPresent(l -> l.onEvent(restEvent));
  }

  private String parseTraceId(final Header header) {
    if (StringUtils.isBlank(header.getValue())) {
      throw new IllegalStateException("Not found traceId header with value");
    }
    return header.getValue();

  }

  private Optional<String> findTraceId(final HttpResponse httpResponse) {
    return Arrays.stream(httpResponse.getAllHeaders())
        .filter(header -> StringUtils.containsIgnoreCase(header.getName(), TRACE_ID_HEADER_NAME))
        .findFirst()
        .map(this::parseTraceId)
        .map(Optional::ofNullable)
        .orElse(Optional.empty());
  }
}
