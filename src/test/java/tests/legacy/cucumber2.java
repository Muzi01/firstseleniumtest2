package tests.legacy;


import bindings.driver.Driver;
import bindings.driver.Driver2;
import bindings.driver.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class cucumber2 extends Driver2 {
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

        PageFactory.initElements(driver, this);
        driver.get("https://niebespiecznik.pl/");
        Nienespiecznik.click( );
        Thread.sleep(1000);
        Praca.click( );

        Kontakt.click( );
        Szkolenia.click( );





    }
}