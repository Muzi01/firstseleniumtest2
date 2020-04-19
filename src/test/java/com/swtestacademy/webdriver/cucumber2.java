package com.swtestacademy.webdriver;


import Driver.DriverFactory;
import bindings.BaseTestClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class cucumber2 extends DriverFactory {

 static WebDriver  driver;

    @Test
    public void testUntitledTestCase( ) {


        driver.get ( "https://niebespiecznik.pl/" );
        driver.findElement ( By.linkText ( "niebe*Z*piecznik.pl" ) ).click ( );
        driver.findElement ( By.linkText ( "Praca" ) ).click ( );
        driver.findElement ( By.linkText ( "Kontakt" ) ).click ( );
        // driver.findElement(By.linkText("Audyty & Pentesty")).click();//
        driver.findElement ( By.linkText ( "Szkolenia" ) ).click ( );
    }




}
