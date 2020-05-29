package bindings;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    private WebDriver driver;
    WebElement element;
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
    public void valid_UserCredential(){

        System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get("http://www.store.demoqa.com");
        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
        driver.findElement(By.id("log")).sendKeys("testuser_3");
        driver.findElement(By.id("pwd")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
        try{
            element = driver.findElement (By.xpath(".//*[@id='account_logout']/a"));
        }catch (Exception e){
        }
        Assert.assertNotNull(element);
        System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Test
    public void inValid_UserCredential()
    {
        System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get("http://www.store.demoqa.com");
        driver.findElement(By.linkText("Dismiss")).click();
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.id("log")).sendKeys("testuser");
        driver.findElement(By.id("pwd")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
        try{
            element = driver.findElement (By.xpath(".//*[@id='account_logout']/a"));
        }catch (Exception e){
        }
        Assert.assertNotNull(element);
        System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    }}

