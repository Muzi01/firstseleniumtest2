package tests.legacy;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import bindings.driver.DriverFactory;
import org.testng.annotations.Test;

public class VerifyLinks extends DriverFactory {
    public static int invalidLink;
    String currentLink;
    String temp;

    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
    }


}


