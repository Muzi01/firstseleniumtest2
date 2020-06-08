package tests.legacy;



import bindings.driver.Driver;
import bindings.driver.DriverFactory2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class allegro_logowanie2 extends DriverFactory2 {

    private static Logger logger = LogManager.getLogger(allegro_logowanie2.class);


    @Test
    public void testAllegroLogowanie() {

        getDriver ().get ("https://allegro.pl/");

    }
}