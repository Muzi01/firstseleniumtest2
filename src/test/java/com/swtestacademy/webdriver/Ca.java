package com.swtestacademy.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Ca {
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
    public void testCa( ) {
        driver.get ( "https://www.credit-agricole.pl/wylogowano/oferta" );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Menu informacje o banku'])[1]/following::a[1]" ) ).click ( );
        driver.findElement ( By.linkText ( "RODO" ) ).click ( );
        driver.findElement ( By.linkText ( "Credit Agricole Bank Polskao RODO" ) ).click ( );
        driver.findElement ( By.linkText ( "Kariera" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Poznaj obszary'])[1]/following::div[2]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='specjalistów zaangażowanych w realizację 50 projektów rocznie'])[1]/following::a[12]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='specjalistów zaangażowanych w realizację 50 projektów rocznie'])[1]/following::a[12]" ) ).click ( );
        driver.findElement ( By.linkText ( "Testerzy" ) ).click ( );
        driver.findElement ( By.linkText ( "Sprawdź pozostałe oferty w tym obszarze" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Poznaj nas'])[1]/following::div[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Obszary działalności'])[1]/following::div[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Poznaj nas'])[1]/preceding::img[3]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Proces rekrutacji'])[1]/following::div[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Wybierz swoje.'])[1]/following::span[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Finanse'])[1]/following::span[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Oferty pracy'])[1]/following::section[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='zachodniopomorskie'])[1]/following::button[1]" ) ).click ( );
        driver.findElement ( By.linkText ( "Specjalista Administrator Urządzeń Aktywnych" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='© 2015 Credit Agricole Bank Polska S.A. Wszelkie prawa zastrzeżone.'])[1]/following::i[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "//header" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Poznaj nas'])[1]/preceding::img[3]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Poznaj nas'])[1]/preceding::img[3]" ) ).click ( );
        driver.close ( );
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
