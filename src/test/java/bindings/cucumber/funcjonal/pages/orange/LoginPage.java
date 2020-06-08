package bindings.cucumber.funcjonal.pages.orange;

import bindings.cucumber.funcjonal.pages.AbstractPage;
import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@StoryProxyComponent
public class LoginPage  extends AbstractPage {
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

    @FindBy (id = "login-field"  )
    public WebElement email;

    @FindBy (id = PASSWORD_ID  )
    public WebElement password;

    @FindBy (xpath ="//button[@id='loginButton']/span")
    public WebElement Dalej2;

    @FindBy (id ="login-box__button")
    public   WebElement  Dalej;

    @FindBy (linkText = ZALOGUJ_TEXT)
    public   WebElement  Zaloguj;

    @FindBy (id =KONTAKT_ID)
    public   WebElement  Kontakt;

    @FindBy (id = LOGIN2_ID )
    public  WebElement Login2 ;

    @FindBy (linkText =ZALOGUJ2_TEXT)
    public   WebElement  Zaloguj2;

    public LoginPage(final DriverFactory driverFactory) {
        PageFactory.initElements(driverFactory.getDriver(), this);
    }


    public void fillLogin (){
        sendKeys (email,"piotr.kramkowski@gmail.com");
        clickUsingJavaScript (Dalej);
    }
    public void clickDalejButton (){
        clickUsingJavaScript (Dalej); }

    public void fillPassword (){
        sendKeys (PASSWORD_ID,PASSWORD);
        clickUsingJavaScript (Dalej2);
    }




    public void loginProcess (){
        fillLogin ();
        clickDalejButton ();
        fillPassword ();

    }

    @Override
    protected String getPageName () {
        return "LoginPage";
    }
}

