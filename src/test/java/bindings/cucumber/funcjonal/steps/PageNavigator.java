package bindings.cucumber.funcjonal.steps;

import bindings.driver.Driver2;
import bindings.driver.DriverFactory;
import org.springframework.stereotype.Service;


    @Service
    public class PageNavigator {

        private final Driver2 driver2;

        public PageNavigator(final Driver2 driver2) {
            this.driver2 = driver2;
        }

        public void navigateToApplicationPage() {
            Driver2.driver.get ("https://www.orange.pl/zaloguj.phtml");
        }

        public void navigatetoLinkedin(){
            Driver2.driver.get  ("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
        }

        public void navigateToRegisterPageTravel(){
            Driver2.driver.get  ("http://www.kurs-selenium.pl/demo/register");
        }


    }
