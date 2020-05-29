package com.swtestacademy.webdriver;

import bindings.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequestTest extends Driver {

    @Test
    public void checkBrokenLink1s() {




        driver.get("http://google.pl/");

        List<String> hrefs = new ArrayList<String>();
        List<WebElement> anchors = driver.findElements(By.tagName("a"));

        for (WebElement anchor : anchors) {

            if ( anchor.getAttribute("href") != null )
                hrefs.add(anchor.getAttribute("href"));

        }

        for (String href : hrefs) {

            try {

                int responseCode = returnStatusCode(new URL(href));
                if ( responseCode != 200 ) {
                    System.out.println("The broken Link is " + href);

                }
                else {

                    System.out.println("The working Link is " + href);

                }
            }
            catch (Exception e) {
                System.out.println("URL: " + href + " returned " + e.getMessage());

            }

        }

    }


    public int returnStatusCode(URL url) {
        Response resp = given().

                when().

                get(url);

        return resp.getStatusCode();
    }

}
