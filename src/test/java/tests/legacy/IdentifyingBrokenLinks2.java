package tests.legacy;

import bindings.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class IdentifyingBrokenLinks2 extends Driver {


    @Test
    public void printBrokenLinks() {

        driver.get("https://www.google.pl");
        List<WebElement> anchor_links = driver.findElements(By.tagName("g"));

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