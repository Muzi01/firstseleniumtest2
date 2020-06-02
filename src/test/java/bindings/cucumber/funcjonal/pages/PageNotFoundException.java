package bindings.cucumber.funcjonal.pages;

public class PageNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4065193356600086092L;

    public PageNotFoundException(final String message) {
        super(message);
    }
}
