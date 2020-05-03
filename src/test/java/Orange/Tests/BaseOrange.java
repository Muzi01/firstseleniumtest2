package Orange.Tests;

import Orange.Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class BaseOrange extends Orange.Pages.LoginPage {
    private static final Logger LOGGER = LogManager.getLogger(BaseOrange.class);
    public Object LoginPage;
    private Object page;

    @Test

    public void OrangeLogowanie() {
    driver.get ("https://www.orange.pl/zaloguj.phtml");
        Orange.Pages.LoginPage loginPage = new LoginPage ();
        LOGGER.info (Dalej);
Zaloguj.click ();






    }


}
