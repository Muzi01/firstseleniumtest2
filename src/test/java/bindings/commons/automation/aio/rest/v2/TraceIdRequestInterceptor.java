package bindings.commons.automation.aio.rest.v2;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class TraceIdRequestInterceptor implements HttpRequestInterceptor {

  @Override
  public void process(final HttpRequest request, final HttpContext context)
      throws HttpException, IOException {
    context.setAttribute(RestConstants.CURRENT_ENDPOINT_ID,
        request.getRequestLine().getUri());
  }
}
