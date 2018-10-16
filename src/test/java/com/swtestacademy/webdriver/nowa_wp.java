package com.swtestacademy.webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class nowa_wp {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer ( );

    @Before
    public void setUp( ) {
        System.setProperty ( "webdriver.chrome.driver" , "C:\\1\\chromedriver.exe" );
        driver = new ChromeDriver ( );
        baseUrl = "https://www.katalon.com/";
        driver.manage ( ).timeouts ( ).implicitlyWait ( 30 , TimeUnit.SECONDS );
    }

    @Test
    public void testUntitledTestCase( ) {
        driver.get ( "https://www.wp.pl/" );
        driver.findElement ( By.linkText ( "Serwisy" ) ).click ( );
        driver.findElement ( By.linkText ( "Ranking lekarzy" ) ).click ( );
        driver.findElement ( By.linkText ( "Prof. dr hab. Jarosław Leszczyszyn" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='ZAKTUALIZUJ SWÓJ KALENDARZ'])[1]/following::input[2]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='ZAKTUALIZUJ SWÓJ KALENDARZ'])[1]/following::input[2]" ) ).clear ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='ZAKTUALIZUJ SWÓJ KALENDARZ'])[1]/following::input[2]" ) ).sendKeys ( "wrocław" );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='ZAKTUALIZUJ SWÓJ KALENDARZ'])[1]/following::button[1]" ) ).click ( );
        driver.findElement ( By.linkText ( "Maciej NowickiZweryfikowany profil - informacje o lekarzu zostały sprawdzone i potwierdzone przez pracowników abcZdrowie" ) ).click ( );
        driver.findElement ( By.linkText ( "pytania do lekarzy" ) ).click ( );
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
        driver.findElement ( By.linkText ( "Czy w podanej sytuacji jest konieczność wykonania testu na HPV?" ) ).click ( );
        driver.findElement ( By.linkText ( "Kalkulatory" ) ).click ( );
        driver.findElement ( By.linkText ( "Aktywność fizyczna" ) ).click ( );
        driver.findElement ( By.linkText ( "Ciąża i dziecko" ) ).click ( );
        driver.findElement ( By.linkText ( "Fora i grupy wsparcia" ) ).click ( );
        driver.findElement ( By.id ( "sqinput" ) ).click ( );
        driver.findElement ( By.id ( "sqinput" ) ).clear ( );
        driver.findElement ( By.id ( "sqinput" ) ).sendKeys ( "Captivity" );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Zaloguj'])[1]/following::button[1]" ) ).click ( );
        driver.findElement ( By.linkText ( "Ważne tematy" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Doppelherz - Bo szkoda życia na pauzę'])[1]/following::span[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='Dolegliwości jelitowe a kwas masłowy'])[1]/following::span[1]" ) ).click ( );
        driver.findElement ( By.xpath ( "(.//*[normalize-space(text()) and normalize-space(.)='REKLAMA'])[2]/following::span[1]" ) ).click ( );
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
