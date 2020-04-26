package bindings.driver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static final Logger LOGGER = LogManager.getLogger(Driver.class);
    public static WebDriver driver;
    private Dimension resolution = new Dimension(1440, 900);

    @BeforeClass
    public static void setupClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        DriverManagerType chrome = DriverManagerType.CHROME;
        WebDriverManager.getInstance(chrome).setup();
        Class<?> chromeClass = Class.forName(chrome.browserClass());
        driver = (WebDriver) chromeClass.newInstance();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().timeouts().implicitlyWait(DriverConfig.MAX_OBJECT_TIMEOUT,
                TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DriverConfig.MAX_PAGE_LOAD_TIME,
                TimeUnit.SECONDS);
        LOGGER.info("Starting Chrome driver");
        LOGGER.info(
                "Chrome webdriver version: {}", WebDriverManager.chromedriver().getDownloadedVersion());


        }
    @AfterClass
    public void tearDown () {
        driver.quit();
    }}
