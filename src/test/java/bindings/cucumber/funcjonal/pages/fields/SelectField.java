package bindings.cucumber.funcjonal.pages.fields;

import bindings.driver.DriverConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import bindings.driver.DriverFactory;
import bindings.cucumber.funcjonal.pages.JSExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectField extends AbstractField {
  private static final Logger LOGGER = LogManager.getLogger(SelectField.class);

  private boolean hiddenElement;
  private boolean dynamicElement;
  private boolean randomValue;
  private String valueLocator;

  public SelectField(final By locator, final DriverFactory driverFactory,
      final JSExecutor jSExecutor) {
    super(locator, driverFactory, jSExecutor);
    this.hiddenElement = false;
    this.dynamicElement = false;
    this.randomValue = false;
  }

  public SelectField setDynamicElement() {
    this.dynamicElement = true;
    return this;
  }

  public SelectField setRandomValue() {
    this.randomValue = true;
    return this;
  }

  public SelectField selectHiddenValue(final String valueLocator) {
    this.hiddenElement = true;
    this.valueLocator = valueLocator;
    return this;
  }

  @Override
  public void fillFormOperation(final String value) {
    if (this.dynamicElement) {
      handleDynamicElement();
    }

    if (this.hiddenElement) {
      selectByValueOnHiddenElement(value);

    } else if (this.randomValue) {
      selectRandomOption();

    } else {
      new Select(this.getElement()).selectByValue(value);
    }
  }

  private void selectByValueOnHiddenElement(final String value) {
    this.tryClickElement();
    this.valueLocator = this.valueLocator.replace("#drop_value#", value);
    new WebDriverWait(this.driverFactory.getDriver(), DriverConfig.MAX_PAGE_LOAD_TIME)
        .until(ExpectedConditions.elementToBeClickable(
            this.getElement().<WebElement>findElement(By.xpath(this.valueLocator))));

    this.scrollToElement();

    tryClickingUntilTimeout();
  }

  private void tryClickingUntilTimeout() {
    final long timeout = System.currentTimeMillis() + DriverConfig.MAX_PAGE_LOAD_TIME * 1000;
    while (System.currentTimeMillis() < timeout) {
      try {
        this.getElement().findElement(By.xpath(this.valueLocator)).click();
        break;

      } catch (final WebDriverException e) {
        LOGGER.warn("Unable to select element: " + this.valueLocator + " Retrying");
        this.sleep();
      }
    }
  }

  private void handleDynamicElement() {
    final long timeout = System.currentTimeMillis() + DriverConfig.MAX_PAGE_LOAD_TIME * 1000;
    while (System.currentTimeMillis() < timeout) {
      try {
        this.getElement().click();
        break;

      } catch (final StaleElementReferenceException | ElementNotVisibleException
          | NoSuchElementException e) {
        LOGGER.warn("Unable to locate element: " + this.getFieldLocator() + " Retrying");
        this.sleep();
      }
    }
  }

  private void selectRandomOption() {
    final List<WebElement> options = new Select(this.getElement()).getOptions();
    final int minIndex = 1; // 0 == empty value
    final int maxIndex = options.size() - 1;
    final int randomIndex = ThreadLocalRandom.current().nextInt(minIndex, maxIndex + 1);
    new Select(this.getElement()).selectByIndex(randomIndex);
  }

  private void sleep() {
    try {
      Thread.sleep(DriverConfig.PAGE_SLEEP_DURATION);

    } catch (final InterruptedException interruptedException) {
      LOGGER.error(Arrays.toString(interruptedException.getStackTrace()));
      Thread.currentThread().interrupt();
    }
  }
}
