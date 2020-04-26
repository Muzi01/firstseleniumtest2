package com.swtestacademy.webdriver;


import bindings.driver.Driver;
import bindings.driver.SeleniumHelper;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class cucumber2 extends Driver {
 @FindBy (linkText ="niebe*Z*piecznik.pl"  )
 private WebElement Nienespiecznik;

 @FindBy (linkText ="Praca")
 private  WebElement  Praca;

 @FindBy (linkText = "Kontakt")
 private WebElement Kontakt ;

 @FindBy (linkText = "Szkolenia")
 private  WebElement Szkolenia;

    @Test
    public void niebespiecznik( )  {
        SeleniumHelper helper = new SeleniumHelper();
        PageFactory.initElements(driver,this);
        driver.get ( "https://niebespiecznik.pl/" );
        Nienespiecznik.click();
        Praca.click();
        helper.takeScrenshoot();
        Kontakt.click();
        Szkolenia.click();

    }





}
