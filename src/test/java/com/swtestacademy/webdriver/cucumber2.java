package com.swtestacademy.webdriver;


import bindings.driver.Driver;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class cucumber2 extends Driver {


    @Test
    public void testUntitledTestCase( )  {


        driver.get ( "https://niebespiecznik.pl/" );
        driver.findElement ( By.linkText ( "niebe*Z*piecznik.pl" ) ).click ( );
        driver.findElement ( By.linkText ( "Praca" ) ).click ( );

        driver.findElement ( By.linkText ( "Kontakt" ) ).click ( );
        driver.findElement ( By.linkText ( "Szkolenia" ) ).click ( );
        driver.quit();
    }




}
