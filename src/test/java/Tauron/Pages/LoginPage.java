package Tauron.Pages;

import bindings.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

@StoryProxyComponent
public abstract class LoginPage extends Driver {
    private static final Logger LOGGER = LogManager.getLogger (LoginPage.class);
    private static final String LOGIN_VALUE = "piotr.kramkowski@gmail.com";
    private static final String PASSWORD_VALUE = "piotr.kramkowski@gmail.com";

    @FindBy (id = "username1")
    public WebElement username;

    @FindBy (id = "password1")
    public WebElement password;

    @FindBy (linkText = "Zaloguj się")
    public WebElement zaloguj;


@Test
    public void logowaniesteps () {
        driver.get ("https://logowanie.tauron.pl/");
        username.sendKeys (LOGIN_VALUE);
        password.sendKeys (PASSWORD_VALUE);
        zaloguj.click ();


    }
}




    // public LoginPage (final DriverFactory driverFactory) {
  //      PageFactory.initElements (DriverFactory.getDriver (),this);
 //   }


