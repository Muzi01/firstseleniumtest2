package bindings.cucumber.funcjonal.pages;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import bindings.cucumber.funcjonal.pages.fields.AbstractField;
import bindings.cucumber.funcjonal.pages.fields.GuiTestParameters;
import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import bindings.cucumber.funcjonal.pages.orange.WaitStepsService;
import bindings.cucumber.funcjonal.Utils;
import bindings.cucumber.funcjonal.pages.JSExecutor;
import bindings.driver.DriverConfig;
import bindings.cucumber.funcjonal.pages.orange.WaitStepsService;



public abstract class AbstractPage {
    private static final Logger LOGGER = LogManager.getLogger(AbstractPage.class);
    private final Map<String, AbstractField> fields = new HashMap<>();

    @Autowired
    protected WaitStepsService waitStepsService;
    @Autowired
    private JSExecutor jSExecutor;
    @Autowired
    protected DriverFactory driverFactory;

    @Autowired
    protected PageElementsFactory pageElementsFactory;

    @Autowired
    protected GuiTestParameters guiTestParameters;

    @PostConstruct
    public void init() {
        setupFields();
    }


    protected void sendKeysIfAvailable(final WebElement webElement,
                                       final Optional<String> parameter) {
        parameter.ifPresent(par -> {
            clickField(webElement);
            webElement.sendKeys(parameter.get());
        });
    }

    protected void sendKeys(final WebElement webElement, final String text) {
        scrollToElement(webElement);
        clickField(webElement);
        webElement.sendKeys(text);
        Utils.safeWait(200);
    }

    /**
     * @deprecated Please create classes with POP pattern. This will be removed. Page handle by
     *             getPageName are deprecated.
     */
    @Deprecated
    protected abstract String getPageName();


    private AbstractField getField(final String fieldName) {
        if (!fields.containsKey(fieldName)) {
            throw new IllegalArgumentException("No such field! (" + fieldName + ")");
        }

        return fields.get(fieldName);
    }

    /**
     * @deprecated Please create classes with POP pattern. New fields in page classes should be given
     *             with @FindyBy annotation about field.
     */
    @Deprecated
    protected void setupFields() {
        // default do nothing
    }

    protected void addField(final String fieldName, final AbstractField field) {
        if (fields.containsKey(fieldName)) {
            throw new IllegalArgumentException("Field " + fieldName + " is already defined!");
        }

        fields.put(fieldName, field);
    }

    /**
     * Click on element
     *
     * @param fieldName - mapped id of element
     */
    public void click(final String fieldName) {
        getField(fieldName).click();
    }

    /**
     * Get information if element is displayed (on page)
     *
     * @param fieldName - mapped id of element
     * @return boolean
     */
    public boolean isDisplayed(final String fieldName) {
        return getField(fieldName).isDisplayed();
    }

    /**
     * Get Field
     *
     * @param fieldName - mapped id of element
     * @return Field
     */
    public AbstractField findField(final String fieldName) {
        return getField(fieldName);
    }

    /**
     * Perform: tries to click element then sendKeys operation on mapped field
     *
     * @param fieldName - mapped id of element
     * @param text - text to send
     */
    public void sendKeys(final String fieldName, final String text) {
        clickField(fieldName);
        getField(fieldName).sendKeys(text);
    }

    private void clickField(final String fieldName) {
        try {
            LOGGER.info("Will try click element: {}", fieldName);
            getField(fieldName).click();
            Utils.safeWait(200, "To set value into field in browser");
        } catch (final ElementNotInteractableException exception) {
            LOGGER
                    .warn(String.format("Could not click %s because of exception %s", fieldName, exception));
        }
    }

    private void clickField(final WebElement webElement) {
        try {
            webElement.click();
            Utils.safeWait(100, "To set value into field in browser");
        } catch (final ElementNotInteractableException exception) {
            LOGGER
                    .warn(String.format("Could not click element because of exception %s", exception));
        }
    }

    /**
     * Perform sendKeysJS operation on mapped field Value is sent directly to the field's 'value'
     * attribute
     *
     * @param fieldName - mapped id of element
     * @param text - text to send
     */
    public void sendKeysJS(final String fieldName, final String text) {
        final WebElement element = getField(fieldName).getElement();
        jSExecutor.setAttribute(element, "value", text);
    }

    /**
     * Perform sendKeysInSequence operation on mapped field Method sends one character at a time until
     * the end of string simulating user writing into field
     *
     * @param fieldName - mapped id of element
     * @param text - text to send
     */
    public void sendKeysInSequence(final String fieldName, final String text) {
        final WebElement element = getField(fieldName).getElement();
        element.clear();
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(String.valueOf(text.charAt(i)));
            Utils.safeWait(200);
        }
    }

    /**
     * Perform select by value from list
     *
     * @param fieldName - mapped id of element
     * @param value - value to select
     */
    public void selectFromList(final String fieldName, final String value) {
        final Select list = new Select(getField(fieldName).getElement());
        list.selectByValue(value);
    }

    protected void selectFromList(final WebElement webElement, final String value) {
        final Select list = new Select(webElement);
        list.selectByValue(value);
    }

    /**
     * Perform select by value from list
     *
     * @param fieldName - mapped id of element
     * @param position - position to select
     */
    public void selectPosition(final String fieldName, final int position) {
        getField(fieldName).getElements().get(position).click();
    }

    /**
     * Search if provided pattern is vivible on page
     *
     * @param text - pattern to search
     * @return boolean
     */
    public boolean containsText(final String text) {
        final By by = By.xpath("//*[contains(., '" + text + "')]");
        return !driverFactory.getDriver().findElements(by).isEmpty();
    }

    public static boolean containsText(final DriverFactory driverFactory, final String text) {
        final By by = By.xpath("//*[contains(., '" + text + "')]");
        return !driverFactory.getDriver().findElements(by).isEmpty();
    }

    public boolean pageSourceContainsText(final String text) {
        return driverFactory.getDriver().getPageSource().contains(text);
    }

    public boolean containsText(final String section, final String text) {
        final By by = By.xpath("//" + section + "[contains(., '" + text + "')]");
        return (!driverFactory.getDriver().findElements(by).isEmpty());
    }

    public boolean elementContainsTextInScript(final String element, final String text) {
        final By by = By.xpath("//" + element + "//script[contains(@src , '" + text + "')]");
        return (!driverFactory.getDriver().findElements(by).isEmpty());
    }

    /**
     * Perform default operation on field. Needed for Cucumber 'I fill form with values:' step
     *
     * @param element
     */


    public void clickUsingJavaScript(final WebElement element) {
        jSExecutor.executeScriptForElement("arguments[0].click();", element);
    }

    public void selectFromDropdown(final WebElement webElement, final String value) {
        new Select(webElement).selectByValue(value);
    }

    protected void selectByValueOnHiddenElement(final WebElement element, final String value,
                                                final String hiddenValue) {
        scrollToElement(element);
        element.click();
        final String locator = hiddenValue.replace("#drop_value#", value);
        final WebElement elementToBeSelected = driverFactory.getDriver().findElement(By.xpath(locator));
        new WebDriverWait(driverFactory.getDriver(), DriverConfig.MAX_PAGE_LOAD_TIME)
                .until(ExpectedConditions.elementToBeClickable(
                        elementToBeSelected));
        elementToBeSelected.click();
    }

    protected void closeNotMandatoryPopupIfExist(final WebElement iframe,
                                                 final WebElement closePopupElement) {
        if (switchToIFrame(iframe)) {
            Utils.safeWait(1000, "Switching to iframe");
            clickIfExist(closePopupElement);
            switchToDefaultContent();
        }
    }

    private void clickIfExist(final WebElement closePopupElement) {
        try {
            closePopupElement.click();
        } catch (final NoSuchElementException e) {
            LOGGER.warn("Did not found expected element, {}", e.getMessage());
        }
    }

    private boolean switchToIFrame(final WebElement webElement) {
        try {
            driverFactory.getDriver().switchTo().frame(webElement);
            return true;
        } catch (final NoSuchElementException e) {
            LOGGER.warn("Did not found expected iFrame, {}", e.getMessage());
            return false;
        }
    }

    private void switchToDefaultContent() {
        driverFactory.getDriver().switchTo().parentFrame();
        driverFactory.getDriver().switchTo().defaultContent();
    }

    protected void scrollToElement(final WebElement element) {
        final String scrollElementIntoMiddle =
                "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                        + "var elementTop = arguments[0].getBoundingClientRect().top;"
                        + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        jSExecutor.executeScriptForElement(scrollElementIntoMiddle, element);
    }

    protected void clickIfExists(final WebElement webElement) {
        try {
            webElement.click();
        } catch (final NoSuchElementException e) {
            LOGGER.warn("Field not present, omitting.");
        }
    }

    protected boolean isElementOnPage(final WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (final NoSuchElementException e) {
            return false;
        }
    }


}
