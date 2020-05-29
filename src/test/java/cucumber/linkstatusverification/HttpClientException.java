package cucumber.linkstatusverification;

public class HttpClientException extends RuntimeException {
    public HttpClientException(Throwable cause) {
        super(cause);
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(String message, Throwable e) {
        super(message, e);
    }
}
