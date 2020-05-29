package cucumber.linkstatusverification;

import cucumber.api.java.en.Then;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

// todo @AT-1505: convert those step to fit new Http Client (new steps: ...generic.RestSteps)
public class RESTSteps {
    private final VerifyLinksActivity verifyLinksActivity;

    private final List<CookiesHolder> cookiesHolders;

    public RESTSteps(final VerifyLinksActivity verifyLinksActivity,

                     final List<CookiesHolder> cookiesHolders) {

        this.verifyLinksActivity = verifyLinksActivity;

        this.cookiesHolders = cookiesHolders;
    }


    @Then("^All links on page \"(.+)\" are active(?:!DEPRECATED!|)")
    public void verifyAllLinkAreActive(final String pageUrl)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {

        this.verifyLinksActivity.checkIfPageContainsActiveLinks(pageUrl);
    }
}
