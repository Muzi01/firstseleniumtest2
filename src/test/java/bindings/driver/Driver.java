package bindings.driver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class Driver {

    public static WebDriver driver;

    @BeforeClass
    public static void setupClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        DriverManagerType chrome = DriverManagerType.CHROME;
        WebDriverManager.getInstance(chrome).setup();
        Class<?> chromeClass =  Class.forName(chrome.browserClass());
        driver = (WebDriver) chromeClass.newInstance();
    }
}
