package bindings.cucumber.funcjonal.pages.orange;

import bindings.driver.DriverFactory;
import bindings.driver.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 extends DriverFactory {
    private static final Logger LOGGER = LogManager.getLogger (LoginPage.class);
    private static final String EMAIL_ID = "login-field";
    private static final String PASSWORD_ID = "password";
    private static final String DALEJ_TEXT = "Dalej";
    private static final String ZALOGUJ_TEXT = "Dalej";
    private static final String LOGINBUTTON_ID = "loginButton";
    private static final String KONTAKT_ID = "drawerLink-0";
    private static final String LOGIN2_ID = "emptyLogin";
    private static final String ZALOGUJ2_TEXT = "Zaloguj się";
    private static final String LOGIN = "piotr.kramkowski@gmail.com";
    private static final String PASSWORD = "Pioneer123!";

    @FindBy (id = EMAIL_ID)
    public WebElement email;

    @FindBy (id = PASSWORD_ID)
    public WebElement password;

    @FindBy (id = "loginButton")
    public WebElement loginButton;

    @FindBy (linkText = "Dalej")
    public WebElement Dalej;

    @FindBy (linkText = ZALOGUJ_TEXT)
    public WebElement Zaloguj;

    @FindBy (id = KONTAKT_ID)
    public WebElement Kontakt;

    @FindBy (id = LOGIN2_ID)
    public WebElement Login2;

    @FindBy (linkText = ZALOGUJ2_TEXT)
    public WebElement Zaloguj2;

    @Test
    public void test
            () throws InterruptedException {
        SeleniumHelper helper = new SeleniumHelper ();
        PageFactory.initElements (getDriver (), this);
        getDriver ().get ("https://www.orange.pl/zaloguj.phtml");
        getDriver ().findElement (By.id ("login-field")).sendKeys ("piotr.kramkowski@gmail.com");
        getDriver ().findElement (By.id ("login-box__button")).click ();
        getDriver ().findElement (By.id ("password")).sendKeys ("Pioneer123!");
        getDriver ().findElement (By.xpath ("//button[@id='loginButton']/span")).click ();
        getDriver ().quit ();



    }
}