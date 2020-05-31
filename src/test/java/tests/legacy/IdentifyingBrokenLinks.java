package tests.legacy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class IdentifyingBrokenLinks {
    private static Logger logger = LogManager.getLogger(IdentifyingBrokenLinks.class);

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void printBrokenLinks() {

        driver.get("https://www.wp.pl");
        List<WebElement> anchor_links = driver.findElements(By.linkText ("y"));

        //getting the urls of the links into another List
        List<String> link_urls = null;
        for (WebElement link : anchor_links) {
            //getting the status code of each link using GET method
            int statusCode = given().get(link.getAttribute("href")).getStatusCode();
            //verifying the status code
            if (statusCode != 200) {
                System.out.println(link.getText() + "-----" + "is broken");
                System.out.println("The status code of broken link is: "+given().get(link.getAttribute("href")).getStatusCode());
            }
        }

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}