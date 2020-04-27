package Orange.Tests;

import Orange.Pages.LoginPage;
import bindings.driver.Driver;
import bindings.driver.SeleniumHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class BaseOrange extends Driver {

    public Object LoginPage;
    private Object page;

    @Test

    public void OrangeLogowanie() {
        SeleniumHelper helper = new SeleniumHelper();
        LoginPage = new LoginPage() ;
        PageFactory.initElements(driver, LoginPage);
        driver.get("https://www.orange.pl/zaloguj.phtml");


    }


}
