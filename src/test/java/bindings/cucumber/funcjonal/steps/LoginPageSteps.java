package bindings.cucumber.funcjonal.steps;

import bindings.cucumber.funcjonal.pages.orange.LoginPage;
import io.cucumber.java.en.And;


public class LoginPageSteps {



    @And("^Fill email adrress to login$")
    public void fillEmailAddressToLogin (LoginPage loginPage) {loginPage.loginProcess ();


        }
    }



