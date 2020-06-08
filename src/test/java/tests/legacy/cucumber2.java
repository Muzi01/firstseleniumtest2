package tests.legacy;


import bindings.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class cucumber2 extends DriverFactory {
    @FindBy(linkText = "niebe*Z*piecznik.pl")
    private WebElement Nienespiecznik;

    @FindBy(linkText = "Praca")
    private WebElement Praca;

    @FindBy(linkText = "Kontakt")
    private WebElement Kontakt;

    @FindBy(linkText = "Szkolenia")
    private WebElement Szkolenia;

    public cucumber2 () throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    }

    @Test
    public void nightstick() throws InterruptedException {

        PageFactory.initElements(getDriver(), this);
        getDriver().get("https://niebespiecznik.pl/");
        Nienespiecznik.click( );
        Thread.sleep(1000);
        Praca.click( );

        Kontakt.click( );
        Szkolenia.click( );





    }
}