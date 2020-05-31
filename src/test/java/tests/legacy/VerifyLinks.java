package tests.legacy;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import bindings.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerifyLinks extends Driver {
    public static int invalidLink;
    String currentLink;
    String temp;

    public static void main(String[] args) throws IOException {
        // Launch The Browser

        // Enter Url
        driver.get("https://www.gov.pl/");

        // Get all the links url
        List<WebElement> ele = driver.findElements(By.tagName("a"));
        System.out.println("size:" + ele.size());
        boolean isValid = false;
        for (int i = 0; i < ele.size(); i++) {
            // System.out.println(ele.get(i).getAttribute("href"));
            isValid = getResponseCode(ele.get(i).getAttribute("href"));
            if (isValid) {
                System.out.println("ValidLinks:"
                        + ele.get(i).getAttribute("href"));
            } else {
                System.out.println("InvalidLinks:"
                        + ele.get(i).getAttribute("href"));
            }
        }

    }

    public static boolean getResponseCode(String urlString) {
        boolean isValid = false;
        try {
            URL u = new URL(urlString);
            HttpURLConnection h = (HttpURLConnection) u.openConnection();
            h.setRequestMethod("GET");
            h.connect();
            System.out.println(h.getResponseCode());
            if (h.getResponseCode() != 404) {
                isValid = true;
            }
        } catch (Exception e) {

        }
        return isValid;
    }

}


