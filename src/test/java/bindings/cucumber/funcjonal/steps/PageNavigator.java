package bindings.cucumber.funcjonal.steps;

import bindings.driver.DriverFactory;
import org.springframework.stereotype.Service;


    @Service
    public class PageNavigator {

        private final DriverFactory driverFactory;

        public PageNavigator(final DriverFactory driverFactory) {
            this.driverFactory = driverFactory;
        }

        public void navigateToApplicationPage() {
            driverFactory.getDriver().get("https://www.linkedin.com/feed/");
        }

    }
