package com.swtestacademy.webdriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test2 {

    //We should add @Test annotation that JUnit will run below method
    @Test
    //Start to write our test method. It should ends with "Test"
    public void firefoxTest( ) {
        System.setProperty ( "webdriver.chrome.driver" , "C:\\1\\chromedriver.exe" );
        //Step 1- Driver Instantiation: Instantiate driver object as FirefoxDriver
        WebDriver driver = new ChromeDriver( );

        //Step 2- Navigation: Open a website
        driver.navigate( ).to( "https://www.teknosa.com/" );

        //Step 3- Assertion: Check its title is correct
        //assertEquals method Parameters: Message, Expected Value, Actual Value
        Assert.assertEquals( "Title check failed!", "Teknosa Alışveriş Sitesi - Herkes İçin Teknoloji", driver.getTitle( ) );

        //Step 4- Close Driver
        driver.close( );

        //Step 5- Quit Driver
        driver.quit( );

    }
}