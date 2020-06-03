package bindings.cucumber.funcjonal.pages;

import org.springframework.stereotype.Service;
import bindings.driver.DriverConfig;

@Service
public class ModalWindow {

    private static final String GET_ID_SCRIPT = "return document.activeElement.getAttribute('id')";
    private final JSExecutor jSExecutor;

    public ModalWindow(final JSExecutor jSExecutor) {
        this.jSExecutor = jSExecutor;
    }

    public void waitTillActive() throws InterruptedException {
        final long maxTimeout = System.currentTimeMillis() + DriverConfig.MAX_PAGE_LOAD_TIME * 1000;
        while (System.currentTimeMillis() < maxTimeout) {
            try {
                final Object id = this.jSExecutor.executeSriptAndReturnObject(GET_ID_SCRIPT);
                if (id != null) {
                    break;
                }

            } catch (final Exception e) {
                Thread.sleep(DriverConfig.PAGE_SLEEP_DURATION);
            }
        }
    }

    public String getActiveWindowID() {
        return (String) this.jSExecutor.executeSriptAndReturnObject(GET_ID_SCRIPT);
    }
}
