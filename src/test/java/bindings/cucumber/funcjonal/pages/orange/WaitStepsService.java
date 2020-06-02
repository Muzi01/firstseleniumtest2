package bindings.cucumber.funcjonal.pages.orange;


import bindings.cucumber.funcjonal.pages.PageFactory;
import bindings.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;



import bindings.driver.DriverFactory;
import bindings.driver.DriverConfig;
import java.time.Duration;


@Service
public class WaitStepsService {

    private static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(WaitStepsService.class);


    private final PageFactory pageFactory;
    private final DriverFactory driverFactory;

    public WaitStepsService(
                            final PageFactory pageFactory, final DriverFactory driverFactory) {

        this.pageFactory = pageFactory;
        this.driverFactory = driverFactory;
    }



    public void waitForElement(final String fieldName) {
        final ExpectedCondition<WebElement> condition =
                ExpectedConditions.elementToBeClickable(
                        this.pageFactory.getCurrentPage().findField(fieldName).getFieldLocator());
        waitUntilCondition(condition);
    }

    public void waitForElement(final WebElement webElement) {
        final ExpectedCondition<WebElement> condition =
                ExpectedConditions.elementToBeClickable(webElement);
        waitUntilCondition(condition);
    }



    public void waitForElementIfNotFoundClickButton(final String fieldName,
                                                    final String elementToClick) {
        final By locator = this.pageFactory.getCurrentPage().findField(fieldName).getFieldLocator();
        try {
            waitUntilCondition(ExpectedConditions.elementToBeClickable(locator));
        } catch (final TimeoutException e) {
            this.pageFactory.getCurrentPage().click(elementToClick);
            waitUntilCondition(ExpectedConditions.elementToBeClickable(locator));
        }
    }



    public void waitForElement(final String fieldName, final String attribute) {
        new WebDriverWait(
                this.driverFactory.getDriver(), DriverConfig.MAX_PAGE_LOAD_TIME)
                .until(ExpectedConditions.attributeToBeNotEmpty(
                        this.pageFactory.getCurrentPage().findField(fieldName).getElement(), attribute));
    }

    public void waitForElementToDisappear(final String fieldName) {
        final ExpectedCondition<Boolean> condition =
                ExpectedConditions
                        .invisibilityOf(this.pageFactory.getCurrentPage().findField(fieldName).getElement());
        waitUntilCondition(condition);
    }

    public void waitUntilUrlContains(final String urlPart) {
        final ExpectedCondition<Boolean> condition =
                ExpectedConditions.urlContains(urlPart);
        waitUntilCondition(condition);
    }

    public void waitForAjax() {
        new WebDriverWait(this.driverFactory.getDriver(), DriverConfig.MAX_PAGE_LOAD_TIME)
                .until(webDriver -> (long) ((JavascriptExecutor) webDriver)
                        .executeScript("return jQuery.active") == 0);
    }

    private <T> void waitUntilCondition(final ExpectedCondition<T> condition) {
        new FluentWait<>(this.driverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(DriverConfig.MAX_PAGE_LOAD_TIME))
                .pollingEvery(Duration.ofSeconds(DriverConfig.POLLING_TIME))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(webDriver -> {
                    LOGGER.info("Waiting to match expected condition for WebElement...");
                    return condition.apply(webDriver);
                });
    }
}
