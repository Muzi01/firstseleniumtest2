package steps;


import cucumber.api.java.Before;
import ulties.RestAssuredExtension;

import static com.sun.java.swing.action.ActionManager.utilities;

public class TestInitialize {

    @Before
    public void TestSetup(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}