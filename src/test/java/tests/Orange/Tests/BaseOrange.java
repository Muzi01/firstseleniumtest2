package tests.Orange.Tests;

import bindings.cucumber.pages.orange.LoginPage;
import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BaseOrange extends LoginPage {
    private static final Logger LOGGER = LogManager.getLogger(BaseOrange.class);

    public BaseOrange (DriverFactory driverFactory) {
        super (driverFactory);
    }


    @Test
    public void OrangeLogowanie() {
    driver.get ("https://www.orange.pl/zaloguj.phtml");

        LOGGER.info (Dalej);
        Zaloguj.click ();






    }


}
