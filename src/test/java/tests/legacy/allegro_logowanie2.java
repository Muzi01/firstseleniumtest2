package tests.legacy;



import bindings.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.junit.Assert.fail;

public class allegro_logowanie2 extends Driver {

    private static Logger logger = LogManager.getLogger(allegro_logowanie2.class);


    @Test
    public void testAllegroLogowanie() {

        driver.get("https://allegro.pl/");

    }
}