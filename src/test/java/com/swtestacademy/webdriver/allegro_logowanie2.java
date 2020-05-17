package com.swtestacademy.webdriver;



import bindings.driver.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class allegro_logowanie2 extends Driver {

    private static Logger logger = LogManager.getLogger(allegro_logowanie2.class);


    @Test
    public void testAllegroLogowanie() {

        driver.get("https://allegro.pl/");

    }
}