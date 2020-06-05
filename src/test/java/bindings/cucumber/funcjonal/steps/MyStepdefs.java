package bindings.cucumber.funcjonal.steps;

import bindings.cucumber.funcjonal.pages.linkedin.LinkedInLoginPage;
import bindings.cucumber.funcjonal.pages.orange.LoginPage;
import bindings.cucumber.funcjonal.pages.orange.LoginPage2;
import bindings.cucumber.funcjonal.pages.orange.WaitStepsService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.legacy.GetRequestTest;


public class MyStepdefs {


    private final PageNavigator pageNavigator;
    private final LoginPage loginPage;
    private final LinkedInLoginPage linkedInLoginPage;
    private final WaitStepsService waitStepsService;
    private final LoginPage2 loginPage2 ;
    private final GetRequestTest getRequestTest;
    public MyStepdefs(final PageNavigator pageNavigator, LoginPage loginPage, LinkedInLoginPage linkedInLoginPage, WaitStepsService waitStepsService, LoginPage2 loginPage2, GetRequestTest getRequestTest) {
        this.pageNavigator = pageNavigator;
        this.loginPage = loginPage;
        this.linkedInLoginPage = linkedInLoginPage;
        this.waitStepsService = waitStepsService;
        this.loginPage2 = loginPage2;
        this.getRequestTest = getRequestTest;
    }





    @When("^I wait for element \"(.*)\"$")
    public void waitForElement(final String fieldName) {
        this.waitStepsService.waitForElement(fieldName);
    }

    @Then("Fill email address to login")
    public void fillEmailAddressToLogin  () throws  Throwable {loginPage.loginProcess ();
    }


    @Then ("He fills Login box")
    public void heFillsLoginBox () throws Throwable{loginPage.fillLogin ();
    }

    @And("Click next button")
    public void clickNextButton () throws Throwable{loginPage.clickDalejButton ();
    }

    @And ("Fill Password")
    public void fillPassword ()throws Throwable {loginPage.fillPassword ();
    }

    @And ("Click Login button")
    public void clickLoginButton () throws Throwable{loginPage.clickDalejButton ();
    }


    @Then ("^User is login to linked in$")
    public void userIsLoginToLinkedIn () {linkedInLoginPage.loginProcessLinked ();
    }

    @Given("^User fill login$")
    public void userFillLogin () throws Throwable {this.linkedInLoginPage.costam ();
    }
    @And ("He is login to System")
    public void heIsLoginToSystem () throws Throwable { loginPage2.test ();
    }

    @And ("^Fill His username$")
    public void fillHisUsername () {loginPage.fillLogin ();

    }

    @And ("^Fill His password$")
    public void fillHisPassword () {loginPage.fillPassword ();
    }

    @Given("^System checks links on tvn(\\d+)$")
    public void systemChecksLinksOnTvn(int arg0) {getRequestTest.CheckTvn();
    }

    @Given ("^System Check lins on  wp\\.pl$")
    public void systemCheckLinsOnWpPl () {getRequestTest.Checkwp ();
    }

    @Given ("^System Check lins on  happipozyczki$")
    public void systemCheckLinsOnHappipozyczki () {getRequestTest.happiPL ();
    }

    @Given ("^System Check lins on  creditea es$")
    public void systemCheckLinsOnCrediteaEs () {getRequestTest.crediteaEs ();
    }

    @Given ("^System Check lins on  credit(\\d+)\\.lt$")
    public void systemCheckLinsOnCreditLt (int arg0) {getRequestTest.credit24LT ();
    }
}
