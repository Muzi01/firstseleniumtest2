package tests.legacy;

import bindings.driver.Driver2;
import bindings.driver.DriverFactory;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class GetRequestTest2 extends Driver2 {

    public GetRequestTest2 () throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    }

    @Test
    public void CheckTvn() {




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
