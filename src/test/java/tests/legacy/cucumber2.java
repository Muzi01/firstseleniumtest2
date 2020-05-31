package tests.legacy;


import bindings.driver.Driver;
import bindings.driver.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class cucumber2 extends Driver {
    @FindBy(linkText = "niebe*Z*piecznik.pl")
    private WebElement Nienespiecznik;

    @FindBy(linkText = "Praca")
    private WebElement Praca;

    @FindBy(linkText = "Kontakt")
    private WebElement Kontakt;

    @FindBy(linkText = "Szkolenia")
    private WebElement Szkolenia;

    @Test
    public void nightstick() throws InterruptedException {
        SeleniumHelper helper = new SeleniumHelper( );
        PageFactory.initElements(driver, this);
        driver.get("https://niebespiecznik.pl/");
        Nienespiecznik.click( );
        Thread.sleep(1000);
        Praca.click( );
        helper.takeScrenshoot( );
        Kontakt.click( );
        Szkolenia.click( );





    }
}