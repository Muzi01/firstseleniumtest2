package bindings.cucumber.linkstatusverification;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static bindings.cucumber.linkstatusverification.Buffer.get;

// todo @AT-1505: convert those step to fit new Http Client (new steps: ...generic.RestSteps)
public class RESTSteps {
    private final VerifyLinksActivity verifyLinksActivity;
    private final RESTStepsImpl restStepsImpl;
    private final List<CookiesHolder> cookiesHolders;

    public RESTSteps(final VerifyLinksActivity verifyLinksActivity,
                     final RESTStepsImpl restStepsImpl,
                     final List<CookiesHolder> cookiesHolders) {

        this.verifyLinksActivity = verifyLinksActivity;
        this.restStepsImpl = restStepsImpl;
        this.cookiesHolders = cookiesHolders;
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I set up common headers:(?:!DEPRECATED!|)$")
    public void setupHeaders(final DataTable dataTable) {

        this.restStepsImpl.setupHeaders(dataTable);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I call (PUT|POST) method (.+) with body parameters:(?:!DEPRECATED!|)$")
    public void callMethodWithBody(final String methodType, final String method,
                                   final DataTable dataTable)
            throws IOException {

        this.restStepsImpl.callMethodWithBody(methodType, method, dataTable);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I call (PUT|POST) request to (.+) where I change \"(.+)\" to \"(.+)\" with body parameters:(?:!DEPRECATED!|)$")
    public void callMethodWithChangedUrlAndWithBody(final String methodType, final String method,
                                                    final String forRepleace, final String param, final DataTable dataTable) throws IOException {

        this.restStepsImpl.callMethodWithChangedUrlAndWithBody(methodType, method, forRepleace, param,
                dataTable);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I call (PUT|POST|GET) method (.+) with body parameter (.+)(?:!DEPRECATED!|)$")
    public void callMethodWithParam(final String methodType, final String method, final String param)
            throws IOException {

        this.restStepsImpl.callMethodWithParam(methodType, method, param);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I post form to (.+) with body parameters:(?:!DEPRECATED!|)$")
    public void sendFormData(final String address, final DataTable dataTable) throws IOException {

        this.restStepsImpl.sendFormData(address, dataTable);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I send an empty (POST|PUT|GET) request to (.+)(?:!DEPRECATED!|)$")
    public void sendRequest(final String methodType, final String url) throws IOException {

        this.restStepsImpl.sendRequest(methodType, url);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I send an empty request (POST|PUT|GET) to (.+) where I change \"(.+)\" to \"(.+)\"(?:!DEPRECATED!|)$")
    public void sendEmptyRequestWithChangedUrl(final String methodType, final String url,
                                               final String forReplace,
                                               final String param) throws IOException {

        this.restStepsImpl.sendEmptyRequestWithChangedUrl(methodType, url, forReplace, param);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @And("^I'm sending an empty request (POST|PUT|GET) to (.+) where I change \"(.+)\" to \"(.+)\" until response attribute (.+) equals \"(.+)\"(?:!DEPRECATED!|)$")
    public void sendUntilAttributeValueAsExpected(
            final String methodType, final String url, final String forReplace,
            final String param, final String attribute, final String expected) {

        this.restStepsImpl
                .sendUntilAttributeValueAsExpected(methodType, url, forReplace, param, attribute,
                        expected);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I send an empty (POST|PUT|GET) request to (.+) without redirect(?:!DEPRECATED!|)$")
    public void sendRequestWoRedirect(final String methodType, final String url) throws IOException {

        this.restStepsImpl.sendRequestWoRedirect(methodType, url);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I send a (POST|PUT|GET) request to (.+) with request parameters:(?:!DEPRECATED!|)$")
    public void sendRequestWithParams(final String methodType, final String url,
                                      final DataTable dataTable)
            throws IOException {

        this.restStepsImpl.sendRequestWithParams(methodType, url, dataTable);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I send a (POST|PUT|GET) request to (.+) with request parameters without redirect:(?:!DEPRECATED!|)$")
    public void sendRequestWithParamsWoRedirect(
            final String methodType, final String url, final DataTable dataTable)
            throws IOException {

        this.restStepsImpl.sendRequestWithParamsWoRedirect(methodType, url, dataTable);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @Then("^HTTP Response status code is \"(\\d+)\"(?:!DEPRECATED!|)$")
    public void assertResponseCode(final int expectedCode) throws IOException {

        this.restStepsImpl.assertResponseCode(expectedCode);
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I (?:store|save) REST response (.+) attribute (.+) as (.+)(?:!DEPRECATED!|)$")
    public void saveRestResponseAttribute(final String response, final String attribute,
                                          final String key) {

        Buffer.set(key, this.restStepsImpl.getAttributeFromRestResponse(attribute, response, false));
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @When("^I (?:store|save) REST numeric response (.+) attribute (.+) as (.+)(?:!DEPRECATED!|)$")
    public void saveRestNumericResponseAttribute(
            final String response, final String attribute, final String key) {

        Buffer.set(key, this.restStepsImpl.getAttributeFromRestResponse(attribute, response, true));
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @Then("^Response attribute (.+) equals \"(.+)\"(?:!DEPRECATED!|)$")
    public void responseAttributeErrorMsgEquals(final String attribute, final String value) {
        Assert.assertEquals(get(value), get(attribute));
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @Then("^Response attribute (.+) not equals to \"(.+)\"(?:!DEPRECATED!|)$")
    public void responseAttributeErrorMsgNotEqualsToValue(final String attribute,
                                                          final String value) {
        Assert.assertNotEquals(get(value), get(attribute));
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @And("^I remove Cookies from session(?:!DEPRECATED!|)$")
    public void iRemoveCookiesFromSession() {

        for (final CookiesHolder cookiesHolder : this.cookiesHolders) {
            cookiesHolder.clear();
        }
    }

    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     *             BDD standard rules or define a new one according to those rules
     *
     */
    @Deprecated
    @Then("^All links on page \"(.+)\" are active(?:!DEPRECATED!|)")
    public void verifyAllLinkAreActive(final String pageUrl)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {

        this.verifyLinksActivity.checkIfPageContainsActiveLinks(pageUrl);
    }


}
