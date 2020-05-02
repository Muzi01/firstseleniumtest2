import bindings.driver.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Niebespiecznik extends Driver {

    @FindBy (linkText = "niebe*Z*piecznik.pl")
    private WebElement Nienespiecznik;

    @FindBy (linkText = "Praca")
    private WebElement Praca;

    @FindBy (linkText = "Kontakt")
    private WebElement Kontakt;

    @FindBy (linkText = "Szkolenia")
    private WebElement Szkolenia;


    @Given ("^I am on Niebespiecznik  page$")
    public void I_am_on_Niebespiecznik_page () throws Throwable {
        driver.get ("https://niebespiecznik.pl/");
    }

    @And ("^I click niebespiecznik button$")
    public void I_click_niebespiecznik_button () throws Throwable {
        Nienespiecznik.click ();
    }
}