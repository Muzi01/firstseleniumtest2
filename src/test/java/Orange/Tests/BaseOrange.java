package Orange.Tests;

import bindings.cucumber.pages.orange.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class BaseOrange extends bindings.cucumber.pages.orange.LoginPage {
    private static final Logger LOGGER = LogManager.getLogger(BaseOrange.class);
    public Object LoginPage;
    private Object page;

    @Test
    public void OrangeLogowanie() {
    driver.get ("https://www.orange.pl/zaloguj.phtml");

        LOGGER.info (Dalej);
        Zaloguj.click ();






    }


}
