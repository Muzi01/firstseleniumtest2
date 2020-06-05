package tests.legacy;

import bindings.driver.DriverFactory;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequestTest extends DriverFactory {


    public void CheckTvn() {




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

    public void Checkwp() {




        getDriver().get("http://wp.pl/");

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


    public void happiPL() {




        getDriver().get("https://www.hapipozyczki.pl/");

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

    public void crediteaEs() {




        getDriver().get("https://www.creditea.es/");

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
    public void credit24LT () {




        getDriver().get("https://credit24.lt/");
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
            closeDriver();

        }

    }



    public int returnStatusCode(URL url) {
        Response resp = given().

                when().

                get(url);

        return resp.getStatusCode();
    }



}
