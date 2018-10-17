package com.swtestacademy.webdriver;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ddd {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before

    public void setUp() {
        System.setProperty ( "webdriver.chrome.driver" , "C:\\1\\chromedriver.exe" );
        driver = new ChromeDriver ();
        baseUrl = "https://www.katalon.com/";
        driver.manage ( ).timeouts ( ).implicitlyWait ( 1 , TimeUnit.SECONDS );
    }

    @Test
    public void testUntitledTestCase() {
        driver.get("https://niebespiecznik.pl/");
        driver.findElement(By.linkText("niebe*Z*piecznik.pl")).click();
        driver.findElement(By.linkText("Praca")).click();
        driver.findElement(By.linkText("Kontakt")).click();
       // driver.findElement(By.linkText("Audyty & Pentesty")).click();//
        driver.findElement(By.linkText("Szkolenia")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
