package bindings.cucumber.funcjonal.pages.travel;

import bindings.driver.DriverFactory;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage  extends DriverFactory {

    private static final Logger LOGGER = LogManager.getLogger (RegisterPage.class);
    private static final String FIRSTNAME_NAME = "firstname";
    private static final String LASTNAME_NAME = "lastname";
    private static final String PHONE_NAME = "phone";
    private static final String EMAIL_NAME = "email";
    private static final String PASSWORD_NAME = "password";
    private static final String CONFIRM_NAME= "confirmpassword";
    private static final String ZALOGUJ_XPATH = "//button[@type='submit']";
    Fairy fairy = Fairy.create ();
    Person person  = fairy.person ();


    @FindBy (name = FIRSTNAME_NAME)
    public WebElement firstname;

    @FindBy (name = LASTNAME_NAME)
    public WebElement lastname;

    @FindBy (name = PHONE_NAME)
    public WebElement phone;

    @FindBy (name = EMAIL_NAME)
    public WebElement email;

    @FindBy (name =PASSWORD_NAME  )
    public WebElement password;

    @FindBy (name = CONFIRM_NAME)
    public WebElement confirmpassword;

    @FindBy (xpath = ZALOGUJ_XPATH)
    public WebElement loginButton;



    public RegisterPage () throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        PageFactory.initElements(getDriver(), this);
    }


    public void registracionprocses () {
        firstname.sendKeys (person.getFirstName ());
        lastname.sendKeys (person.getLastName ());
        phone.sendKeys (person.getTelephoneNumber ());
        email.sendKeys (person.getEmail ());
        password.sendKeys ("Qwerty123");
        confirmpassword.sendKeys ("Qwerty123");
        loginButton.click ();
        getDriver().quit ();


    }
        public void  openPage () {
            getDriver().get("http://www.kurs-selenium.pl/demo/register");

        }


    }
