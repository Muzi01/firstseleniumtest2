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
            driverFactory.getDriver().get ("https://www.orange.pl/zaloguj.phtml");
        }

        public void navigatetoLinkedin(){
            driverFactory.getDriver().get  ("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
        }

        public void navigateToRegisterPageTravel(){
            driverFactory.getDriver().get  ("http://www.kurs-selenium.pl/demo/register");
        }


    }
