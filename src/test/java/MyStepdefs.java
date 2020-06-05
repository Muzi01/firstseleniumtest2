import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class MyStepdefs {
    @Given ("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor (String url) throws Throwable {
    given().contentType (ContentType.JSON);
    }

    @And ("^I perform GET for post number \"([^\"]*)\"$")
    public void iPerformGETForPostNumber (String postNumber) throws Throwable {
      when ().get (String.format ("http://localhost:3000/posts/%s",postNumber) ).
              then ().body ("author", Is.is ("Karthik KK"));
    }

    @Then ("^I should see the autor name as \"([^\"]*)\"$")
    public void iShouldSeeTheAutorNameAs (String arg0) throws Throwable {
      then.
    }
}
