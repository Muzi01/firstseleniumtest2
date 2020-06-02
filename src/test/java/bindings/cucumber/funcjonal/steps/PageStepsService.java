package bindings.cucumber.funcjonal.steps;

import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import org.openqa.selenium.support.PageFactory;

@Service
public class PageStepsService {

    private static final Logger LOGGER = LogManager.getLogger(PageStepsService.class);
    private static final String LAST = "last";
    private static final String FIRST = "first";
    private final PageFactory pageFactory;


    public PageStepsService (final PageFactory pageFactory,final DriverFactory driverFactory) {
        this.pageFactory = pageFactory;
    }
}
