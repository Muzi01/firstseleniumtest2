package tests.legacy;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.fail;

public class allegro_logowanie {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer ( );

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testAllegroLogowanie( ) {
        driver.get ( "https://allegro.pl/" );
        driver.findElement ( By.linkText ( "przejdź dalej" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='szukaj'])[1]/following::span[3]" ) ).click ( );
        driver.findElement ( By.linkText ( "zaloguj" ) ).click ( );
        driver.findElement ( By.id ( "username" ) ).clear ( );
        driver.findElement ( By.id ( "username" ) ).sendKeys ( "piotr.kramkowski@gmail.com" );
        driver.findElement ( By.id ( "password" ) ).clear ( );
        driver.findElement ( By.id ( "password" ) ).sendKeys ( "Pioneer123!" );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Zaloguj się przez'])[1]/following::span[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='p2k2003'])[1]/following::i[1]" ) ).click ( );
        driver.findElement ( By.linkText ( "kupione" ) ).click ( );
        driver.findElement ( By.linkText ( "Kategorie" ) ).click ( );
        driver.findElement ( By.linkText ( "Elektronika" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='AGD wolnostojące'])[3]/following::a[4]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='kategorie'])[1]/following::select[1]" ) ).click ( );
        new Select ( driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='kategorie'])[1]/following::select[1]" ) ) ).selectByVisibleText ( "cena: od najwyższej" );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Stan'])[1]/following::label[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Stan'])[1]/following::label[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='po zwrocie'])[1]/following::label[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='rodzaj oferty'])[1]/following::label[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='p2k2003'])[1]/following::i[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='szukaj'])[1]/following::span[3]" ) ).click ( );
        driver.findElement ( By.linkText ( "wyloguj" ) ).click ( );
    }

    @After
    public void tearDown( ) {
        driver.quit ( );
        String verificationErrorString = verificationErrors.toString ( );
        if (!"".equals ( verificationErrorString )) {
            fail ( verificationErrorString );
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement ( by );
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent( ) {
        try {
            driver.switchTo ( ).alert ( );
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText( ) {
        try {
            Alert alert = driver.switchTo ( ).alert ( );
            String alertText = alert.getText ( );
            if (acceptNextAlert) {
                alert.accept ( );
            } else {
                alert.dismiss ( );
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
