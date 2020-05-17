package bindings.driver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static final Logger LOGGER = LogManager.getLogger(Driver.class);
    public static WebDriver driver;
    public final Logger LOG = LogManager.getLogger (this.getClass ());
    private Dimension resolution = new Dimension(1440, 900);

    @BeforeClass

    public static void setupClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        DriverManagerType chrome = DriverManagerType.CHROME;
        WebDriverManager.getInstance(chrome).setup();
        Class<?> chromeClass = Class.forName(chrome.browserClass());
        driver = (WebDriver) chromeClass.newInstance();
        LOGGER.info ("Driver Initated");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().timeouts().implicitlyWait(DriverConfig.MAX_OBJECT_TIMEOUT,
                TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DriverConfig.MAX_PAGE_LOAD_TIME,
                TimeUnit.SECONDS);

        }
    @AfterClass
    public void tearDown () {
        driver.quit();
    }}
