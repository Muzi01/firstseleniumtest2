package bindings.driver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Driver2 {
    public static WebDriver driver;
    public static final Logger LOGGER = LogManager.getLogger(Driver2.class);


    public  Driver2 () throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DriverManagerType chrome = DriverManagerType.CHROME;
        WebDriverManager.getInstance (chrome).setup ();
        Class<?> chromeClass = Class.forName (chrome.browserClass ());
        driver = (WebDriver) chromeClass.newInstance ();
        LOGGER.info ("Driver Initated");
        driver.manage ().deleteAllCookies ();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DriverConfig.MAX_OBJECT_TIMEOUT,
                TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DriverConfig.MAX_PAGE_LOAD_TIME,
                TimeUnit.SECONDS);

    }

    @AfterClass
    public void tearDown () {
        driver.quit ();
        Driver2.restetDriver();
    }

    private static void restetDriver () {

    }
}