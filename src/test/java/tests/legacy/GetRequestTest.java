package tests.legacy;

import bindings.driver.Driver;
import bindings.driver.DriverFactory;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequestTest extends DriverFactory {

    @Test
    public void checkBrokenLink1s() {




        getDriver().get("http://tvn24.pl/");

        List<String> hrefs = new ArrayList<String>();
        List<WebElement> anchors = getDriver().findElements(By.tagName("a"));

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
