package cucumber.linkstatusverification;


public class RESTClientException extends RuntimeException {
    private final Integer httpStatusCode;

    public RESTClientException(String message) {
        super(message);
        this.httpStatusCode = 500;
    }

    public RESTClientException(Throwable t) {
        super(t);
        this.httpStatusCode = 500;
    }

    public RESTClientException(String message, Integer httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public RESTClientException(String message, Throwable e) {
        super(message, e);
        this.httpStatusCode = 500;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
