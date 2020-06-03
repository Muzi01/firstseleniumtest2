package bindings.commons.automation.mule.utils.clients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public abstract class MuleRestClient {

  private static final Logger LOG = LogManager.getLogger(MuleRestSFDCClient.class);
  String baseUrl;

  public String createUrl(final String uri) {
    String url = this.baseUrl;
    if (uri.charAt(0) != '/') {
      url += ("/" + uri);
    } else {
      url += uri;
    }
    return url;
  }

  public String createGetUrl(final String uri, final Map<String, String> pathParameters) {
    String url = createUrl(uri);
    final Writer wr = new StringWriter();
    url = checkPathParam(pathParameters, url, wr);
    url += wr.toString();
    if (url.charAt(url.length() - 1) == '&') {
      url = url.substring(0, url.length() - 1);
    }
    LOG.info("Generated URL: {}", url);
    return url;
  }

  private String checkPathParam(final Map<String, String> pathParameters, String url,
      final Writer wr) {
    if (!pathParameters.isEmpty()) {
      url += "?";
      pathParameters.forEach((k, v) -> {
        try {
          wr.append(k);
          wr.append("=");
          wr.append(v);
          wr.append("&");
        } catch (final IOException e) {
          LOG.error(e.getStackTrace());
        }
      });
    }
    return url;
  }

  public String createPostUrl(final String uri) {
    return createUrl(uri);
  }

}
