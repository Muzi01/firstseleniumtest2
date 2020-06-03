package bindings.commons.automation.rest.client.deprecated.exceptions;

/**
 * @deprecated Please use "HttpClientException" from com.ipfdigital.rest.exceptions package (as
 *             error statuses are handled directly from ApacheClient)
 */
@Deprecated
public class RESTClientException extends RuntimeException {

  private final Integer httpStatusCode;

  public RESTClientException(final String message) {
    super(message);
    this.httpStatusCode = 500;
  }

  public RESTClientException(final Throwable t) {
    super(t);
    this.httpStatusCode = 500;
  }

  public RESTClientException(final String message, final Integer httpStatusCode) {
    super(message);
    this.httpStatusCode = httpStatusCode;
  }

  public RESTClientException(final String message, final Throwable e) {
    super(message, e);
    this.httpStatusCode = 500;
  }

  public int getHttpStatusCode() {
    return this.httpStatusCode;
  }
}
