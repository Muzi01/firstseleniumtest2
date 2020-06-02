package bindings.cucumber.funcjonal;


public class TimeoutException extends RuntimeException {
    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
