package bindings.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static tests.legacy.FunctionalTest.setup;

public class DriverFactory2 {

    private static WebDriver driverInstance;

   @BeforeClass
    public static WebDriver getDriver () {
        if (driverInstance == null) {
            WebDriverManager.chromedriver ().setup ();
            driverInstance = new ChromeDriver ();
            driverInstance.manage ().deleteAllCookies ();
            driverInstance.manage ().timeouts ().implicitlyWait (15, TimeUnit.SECONDS);
        }

        return driverInstance;
    }

    @After
    public void teardown () {
        if (driverInstance != null) {
            driverInstance.quit ();
        }
    }
}