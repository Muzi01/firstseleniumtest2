package bindings.cucumber.pages.orange;

import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@StoryProxyComponent
public class LoginPage extends DriverFactory {
    private static final Logger LOGGER = LogManager.getLogger (LoginPage.class);

    @FindBy (id ="login-field"  )
    public WebElement email;

    @FindBy (linkText ="Dalej")
    public   WebElement  Dalej;

    @FindBy (linkText ="Zaloguj")
    public   WebElement  Zaloguj;

    @FindBy (id ="drawerLink-0")
    public   WebElement  Kontakt;

    @FindBy (id = "emptyLogin")
    public  WebElement Login2 ;

    @FindBy (linkText ="\n" +
            "\t\t\t\t\t\t\t\t\t\t\tZaloguj się\n" +
            "\t\t\t\t\t\t\t\t\t\t")
    public   WebElement  Zaloguj2;
    public WebDriver driver;

    public LoginPage (final DriverFactory driverFactory) {

        PageFactory.initElements (driverFactory.getDriver(),this);

    }


    public void email () {
        email.sendKeys("piotr.kramkowski@gmail.com");
    }
    public void Kontakt() {
        Kontakt.click ();
    }
    public void Dalej (){
        Dalej.click ();
        }

    public void Zaloguj (){
        Zaloguj.click ();
    }

    public void Login2 (){
        Login2.sendKeys ("Pioneer123!");
        }

        public  void  Zaloguj2 (){
        Zaloguj2.click ();
    }
    }

