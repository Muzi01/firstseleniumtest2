package bindings.cucumber.funcjonal.pages;

import bindings.cucumber.funcjonal.steps.PageStepsService;
import bindings.driver.DriverFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class PageSteps {

    private final PageFactory pageFactory;
    private final DriverFactory driverFactory;
    private final PageStepsService pageStepsImpl;

    public PageSteps (final PageStepsService pageStepsImpl, final PageFactory pageFactory,
                      final DriverFactory driverFactory) {
        this.pageStepsImpl = pageStepsImpl;
        this.pageFactory = pageFactory;
        this.driverFactory = driverFactory;
    }

    @When ("^Page contains \"(.*)\"$")
    public void pageContains (final String textPattern) {
        this.pageStepsImpl.pageContains (textPattern);
    }

    @When ("^Page source contains \"(.*)\"$")
    public void pageSourceContains (final String textPattern) {
        this.pageStepsImpl.pageSourceContains (textPattern);
    }

    @When ("^Section \"(.+)\" contains \"(.*)\"$")
    public void sectionContains (final String section, final String textPattern) {
        this.pageStepsImpl.sectionContains (section, textPattern);
    }

    @Then ("^Script in element \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void elementContainsText (final String element, final String textPattern) {
        this.pageStepsImpl.elementContainsText (element, textPattern);
    }

    @When ("^Page contains value \"(.*)\"$")
    public void pageContainsValue (final String textPattern) {
        this.pageStepsImpl.pageContainsValue (textPattern);
    }

    @When ("^Page contains values:$")
    public void pageContainsValues (final DataTable dataTable) {
        this.pageStepsImpl.pageContainsValues (dataTable);
    }

    @When ("^Page does not contain \"(.*)\"$")
    public void pageDoesNotContain (final String textPattern) {
        this.pageStepsImpl.pageDoesNotContain (textPattern);
    }

    @When ("^Page source does not contain \"(.*)\"$")
    public void pageSourceDoesNotContain (final String textPattern) {
        this.pageStepsImpl.pageSourceDoesNotContain (textPattern);
    }

    @When ("^Table contains (.*) \"(.*)\"$")
    public void tableContainsElements (final String elementsAmount, final String tableElementName) {
        this.pageStepsImpl.tableContainsElements (elementsAmount, tableElementName);
    }

    @Given ("^I am on \"(.*)\" page$")
    public void setupPage (final String pageIdentifier) {
        this.pageFactory.open (pageIdentifier);
    }

    @When ("^Navigate to \"(.+)\" url$")
    public void navigateTo (final String url) {
        this.driverFactory.getDriver ().navigate ().to (Buffer.getStringValue (url));
    }

    @When ("^Navigate to subpage \"(.+)\" of the current environment$")
    public void navigateToSubPageForGivenEnv (final String subPage) {
        this.pageStepsImpl.navigateToSubPageForGivenEnv (subPage);
    }

    @When ("^I click element \"(.*)\"$")
    public void click (final String fieldName) {
        this.pageFactory.getCurrentPage ().click (fieldName);
    }

    @When ("^I set slider \"(.*)\" to position \"(.*)\"$")
    public void setSliderPosition (final String fieldName, final int position) {
        this.pageFactory.getCurrentPage ().selectPosition (fieldName, position);
    }

    @When ("^I close \"(.*)\" popup$")
    public void closeModal (final String fieldName) throws InterruptedException {
        this.pageStepsImpl.closeModal (fieldName);
    }

    @When ("^I fill field \"(.*)\" with \"(.*)\"$")
    public void fillField (final String fieldName, final String text) {
        this.pageFactory.getCurrentPage ().sendKeys (fieldName,
                this.customerDataHolder.getTestData (text));
    }

    @When ("^I fill field \"(.*)\" with \"(.*)\" using JS$")
    public void fillFieldJS (final String fieldName, final String text) {
        this.pageFactory.getCurrentPage ().sendKeysJS (fieldName,
                this.customerDataHolder.getTestData (text));
    }

    @When ("^I fill field \"(.*)\" with \"(.*)\" in sequence$")
    public void fillFieldInSequence (final String fieldName, final String text) {
        this.pageFactory.getCurrentPage ().sendKeysInSequence (fieldName,
                this.customerDataHolder.getTestData (text));
    }

    @When ("^I remove last character from field \"(.*)\"$")
    public void removeCharacter (final String fieldName) {
        this.pageFactory.getCurrentPage ().sendKeys (fieldName, "\uE003");
    }

    @When ("^I mark all characters in field \"(.*)\"$")
    public void markAllinField (final String fieldName) {
        this.pageFactory.getCurrentPage ().sendKeys (fieldName, Keys.chord (Keys.CONTROL, "a"));
    }

    @When ("^I fill form with values:$")
    public void fillForm (final DataTable dataTable) {
        this.pageStepsImpl.fillForm (dataTable);
    }

    @When ("^(?:He|Customer) fills form with values:$")
    public void customerFillsForm (final DataTable dataTable) {
        this.pageStepsImpl.customerFillsForm (dataTable);
    }


    @When ("^The elements are visible:$")
    public void theElementsAreVisible (final DataTable dataTable) {
        this.pageStepsImpl.theElementsAreVisible (dataTable);
    }

    @When ("^I select dropdown value \"(.*)\" from \"(.*)\"$")
    public void selectValueFromList (final String value, final String listElement) {
        this.pageStepsImpl.selectValueFromList (value, listElement);
    }

    @When ("^I switch to frame id: \"(.*)\"$")
    public void switchToFrame (final String frameId) {
        this.pageStepsImpl.switchToFrame (frameId);
    }

    @When ("^I switch to frame \"(.*)\"$")
    public void switchToFrameWithoutId (final String fieldName) {
        this.pageStepsImpl.switchToFrameWithoutId (fieldName);
    }

    @When ("^I switch to default Page Content$")
    public void switchToDefaultContent () {
        this.pageStepsImpl.switchToDefaultContent ();
    }

    @When ("^Using \"(.*)\" I upload file \"(.*)\"$")
    public void uploadFile (final String fieldElement, final String fileName) {
        this.pageStepsImpl.uploadFile (fieldElement, fileName);
    }

    @When ("^Refresh Page Content$")
    public void refreshPage () {
        this.driverFactory.getDriver ().navigate ().refresh ();
    }

    @When ("^Reload Page")
    public void reloadPage () {
        final WebDriver driver = this.driverFactory.getDriver ();
        driver.navigate ().to (driver.getCurrentUrl ());
    }

    @When ("^Reload page until it contains \"(.+)\"")
    public void reloadPageUntilItContains (final String text) {
        this.pageStepsImpl.reloadPageUntilItContains (text);
    }

    @When ("^Reload page until it contains element \"(.+)\"")
    public void reloadPageUntilItContainsElement (final String element) {
        this.pageStepsImpl.reloadPageUntilItContainsElement (element);
    }

    @Given ("^Click element \"(.*)\" if exists$")
    public void clickIfExists (final String fieldName) {
        this.pageStepsImpl.clickIfExists (fieldName);
    }

    @Then ("^I switch to active window$")
    public void switchToActiveWindow () {
        this.pageStepsImpl.switchToActiveWindow ();
    }

    @When ("^I set slider \"(.*)\" to \"(.*)\" %$")
    public void moveSlider (final String fieldName, final String offset) {
        this.pageFactory.getCurrentPage ().performDefaultOperation (fieldName, offset);
    }

    @Then ("^The element \"([^\"]*)\" is visible$")
    public void iShouldSee (final String fieldName) {
        this.pageStepsImpl.iShouldSee (fieldName);
    }

    @Then ("^The element \"([^\"]*)\" is not visible$")
    public void iShouldNotSee (final String fieldName) {
        this.pageStepsImpl.iShouldNotSee (fieldName);
    }

    @Then ("^The element \"([^\"]*)\" is enabled")
    public void shouldBeEnabled (final String fieldName) {
        this.pageStepsImpl.shouldBeEnabled (fieldName);
    }

    @Then ("^The element \"([^\"]*)\" is disabled$")
    public void shouldBeDisabled (final String fieldName) {
        this.pageStepsImpl.shouldBeDisabled (fieldName);
    }

    @Then ("^The element \"([^\"]*)\" is checked")
    public void shouldBeChecked (final String fieldName) {
        this.pageStepsImpl.shouldBeChecked (fieldName);
    }

    @Then ("^The element \"([^\"]*)\" is not checked")
    public void shouldNotBeChecked (final String fieldName) {
        this.pageStepsImpl.shouldNotBeChecked (fieldName);
    }

    @Then ("^Field \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void fieldContainsText (final String fieldName, final String pattern) {
        this.pageStepsImpl.fieldContainsText (fieldName, pattern);
    }

    @When ("^I scroll page to the \"(top|bottom)\"")
    public void scrollPageToTopOrBottom (final String side) {
        this.pageStepsImpl.scrollPageToTopOrBottom (side);
    }

    @When ("^I click \"(\\d+)\" times element \"([^\"]*)\"$")
    public void iClickTimesElement (final int i, final String elementName) {
        this.pageStepsImpl.iClickTimesElement (i, elementName);
    }

    @Then ("^I select \"(last|first|random)\" available day from \"([^\"]*)\" calendar for \"(HAPI_ES|HAPI_PL|CREDIT24_FI|CREDIT24_EE|CREDIT24_LT|SVING_EE|CREDIT24_LV)\"$")
    public void iSelectAvailableDayFrom (final String decision, final String calendarName,
                                         final String market)
            throws InterruptedException {
        this.pageStepsImpl.iSelectAvailableDayFrom (decision, calendarName, market);
    }

    @Then ("^The element \"([^\"]*)\" contains text \"([^\"]*)\"$")
    public void theElementContainsText (final String fieldName, final String text) {
        this.pageStepsImpl.theElementContainsText (fieldName, text);
    }

    @Then ("^The element \"([^\"]*)\" contains multiline text:$")
    public void theElementContainsMultilineText (final String fieldName, final String text) {
        this.pageStepsImpl.theElementContainsMultilineText (fieldName, text);
    }

    @When ("^I check manifest \"(.+)\" contents - assert \"(.+)\"$")
    public void checkManifestContents (final String url, final String assertString)
            throws IOException {
        this.pageStepsImpl.checkManifestContents (url, assertString);
    }








    @Then ("^(?:He|Customer) does not see \"(.*)\" text copy$")
    public void customerDoesNotSeeMessage (final String message) {
        pageDoesNotContain (message);
    }

    @And ("^(?:He|Customer) clicks \"(.*)\" field?$")
    public void customerClicksField (final String fieldName) {
        this.pageFactory.getCurrentPage ().click (fieldName);
    }


    /**
     * @deprecated please do not reuse this steps, find an equivalent step created with the IPFD-AT
     * BDD standard rules or define a new one according to those rules
     */
    @Deprecated
    @And ("^(?:He|Customer) clears \"(.*)\" field(?:!DEPRECATED!|)$")
    public void customerClearField (final String fieldName) {
        final WebElement field = this.pageFactory.getCurrentPage ().findField (fieldName).getElement ();
        field.clear ();
    }
}