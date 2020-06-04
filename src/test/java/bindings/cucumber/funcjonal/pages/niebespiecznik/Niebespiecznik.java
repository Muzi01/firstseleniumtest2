package bindings.cucumber.funcjonal.pages.niebespiecznik;

import bindings.cucumber.funcjonal.pages.AbstractPage;
import bindings.cucumber.funcjonal.pages.orange.StoryProxyComponent;
import bindings.driver.DriverFactory;

import cucumber.api.java.en.And;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@StoryProxyComponent
public class Niebespiecznik extends AbstractPage {

    @FindBy (linkText = "niebe*Z*piecznik.pl")
    private WebElement Nienespiecznik;

    @FindBy (linkText = "Praca")
    private WebElement Praca;

    @FindBy (linkText = "Kontakt")
    private WebElement Kontakt;

    @FindBy (linkText = "Szkolenia")
    private WebElement Szkolenia;
    public Niebespiecznik(final DriverFactory driverFactory) {
        PageFactory.initElements(driverFactory.getDriver(), this);
    }


public void clickNiebespiecznik (){
        clickUsingJavaScript (Nienespiecznik);

}

    @And ("^I click niebespiecznik button$")
    public void I_click_niebespiecznik_button () throws Throwable {
        Nienespiecznik.click ();
    }

    @Override
    protected String getPageName () {
        return null;
    }
}