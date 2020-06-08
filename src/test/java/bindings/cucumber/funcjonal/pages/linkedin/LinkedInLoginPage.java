package bindings.cucumber.funcjonal.pages.linkedin;

import bindings.cucumber.funcjonal.pages.AbstractPage;
import bindings.cucumber.funcjonal.pages.orange.LoginPage;
import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInLoginPage  extends AbstractPage {


    private static final Logger LOGGER = LogManager.getLogger (LoginPage.class);
    private static final String EMAIL_ID = "Email or Phone";
    private static final String PASSWORD_ID = "password";
    private static final String DALEJ_TEXT = "Dalej";
    private static final String ZALOGUJ_TEXT = "Dalej";
    private static final String LOGINBUTTON_ID = "loginButton";
    private static final String KONTAKT_ID = "drawerLink-0";
    private static final String LOGIN2_ID = "emptyLogin";
    private static final String ZALOGUJ2_TEXT = "Zaloguj się";
    private static final String LOGIN = "piotr.kramkowski@gmail.com";
    private static final String PASSWORD = "Pioneer123!";


    @FindBy (linkText = EMAIL_ID  )
    public WebElement email;

    @FindBy (id = PASSWORD_ID  )
    public WebElement password;

    @FindBy (className = "btn__primary--large from__button--floating" )
    public WebElement loginButton;

    @FindBy (linkText ="Dalej")
    public   WebElement  Dalej;

    @FindBy (linkText = ZALOGUJ_TEXT)
    public   WebElement  Zaloguj;

    @FindBy (id =KONTAKT_ID)
    public   WebElement  Kontakt;

    @FindBy (id = LOGIN2_ID )
    public  WebElement Login2 ;



    public LinkedInLoginPage (final DriverFactory driverFactory) {
        PageFactory.initElements(driverFactory.getDriver(), this);
    }

    public void fillLogin (){ sendKeys (EMAIL_ID,LOGIN); }
    public void fillPassword (){ sendKeys (PASSWORD_ID,PASSWORD); }

    public void clickLoginButton (){clickUsingJavaScript (loginButton);}

    public void loginProcessLinked () {
        fillLogin ();
        fillPassword ();
        clickLoginButton ();

    }

    public void costam (){
        driverFactory.getDriver ().findElement (By.id ("username")).sendKeys ("piotr.kramkowski@gmail.com");

    }

    @Override
    protected String getPageName () {
        return "LinkedInLoginPage";
    }
}
