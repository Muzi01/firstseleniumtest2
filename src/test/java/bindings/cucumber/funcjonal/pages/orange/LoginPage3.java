package bindings.cucumber.funcjonal.pages.orange;


import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage3 extends DriverFactory {
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
    private static final String ZALOGUJ_XPATH = "login-box__button";
    private static final String ZALOGUJ2_XPATH = "//button[@id='loginButton']/span";


    @FindBy (id = EMAIL_ID)
    public WebElement email;

    @FindBy (id = PASSWORD_ID)
    public WebElement password;

    @FindBy (xpath = "loginButton")
    public WebElement loginButton1;

    @FindBy (linkText = "Dalej")
    public WebElement Dalej;

    @FindBy (linkText = ZALOGUJ_TEXT)
    public WebElement Zaloguj;

    @FindBy (id = KONTAKT_ID)
    public WebElement Kontakt;

    @FindBy (id = LOGIN2_ID)
    public WebElement Login2;

    @FindBy (id = ZALOGUJ_XPATH)
    public WebElement loginButton;

    @FindBy (xpath = ZALOGUJ2_XPATH)
    public WebElement loginbutton2;

    public LoginPage3() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super ();
        PageFactory.initElements(getDriver(), this);
    }


    public void  openPage () {
        getDriver().get("https://www.orange.pl/zaloguj.phtml");
    }

    public void wpisanieLoginu(){
        email.sendKeys ("piotr.kramkowski@gmail.com");
        loginButton.click ();


    }

    public void wpisanieHasla (){
        password.sendKeys (PASSWORD);
        loginbutton2.click ();


    }
}