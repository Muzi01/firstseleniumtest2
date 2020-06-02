package bindings.cucumber.funcjonal.steps;

import bindings.cucumber.funcjonal.pages.orange.LoginPage;
import bindings.driver.DriverFactory;
import cucumber.api.java.en.And;
import sun.management.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTable;

public class LoginPageSteps {



    @And ("^Fill email adrress to login$")
    public void fillEmailAddressToLogin (LoginPage loginPage) {loginPage.loginProcess ();


        }
    }



