package Orange.Tests;

import bindings.driver.Driver;
import bindings.driver.SeleniumHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class BaseOrange extends Driver {

    private Object LoginPage;

    @Test

    public void OrangeLogowanie() {
        SeleniumHelper helper = new SeleniumHelper();
        PageFactory.initElements(driver,this);
        driver.get("https://www.orange.pl/zaloguj.phtml");






    }


}
