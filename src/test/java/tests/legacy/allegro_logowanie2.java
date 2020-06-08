package tests.legacy;



import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class allegro_logowanie2 extends DriverFactory {

    private static final Logger logger = LogManager.getLogger(allegro_logowanie2.class);


    @Test
    public void testAllegroLogowanie() {

        getDriver ().get ("https://allegro.pl/");

    }
}