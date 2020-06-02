package bindings.cucumber.funcjonal.pages.fields;

import bindings.cucumber.funcjonal.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import bindings.driver.DriverFactory;
import bindings.cucumber.funcjonal.pages.JSExecutor;
import java.util.List;


public abstract class AbstractField {
  private final Logger logger = LogManager.getLogger(getClass());
  private final By locator;
  private boolean useScroll;
  private boolean useMove;
  private boolean useClear;
  private boolean useJavaScript;
  protected DriverFactory driverFactory;
  protected JSExecutor jSExecutor;

  AbstractField(final By locator, final DriverFactory driverFactory, final JSExecutor jSExecutor) {
    this.locator = locator;
    useScroll = false;
    useMove = false;
    useClear = false;
    useJavaScript = false;
    this.driverFactory = driverFactory;
    this.jSExecutor = jSExecutor;

  }

  /**
   * Scroll to element using JavaScript function Set to false by default
   */
  public AbstractField setScrollToElement() {
    useScroll = true;
    return this;
  }

  /**
   * Move to element using Selenium action builder Set to false by default
   */
  public AbstractField setMoveToElement() {
    useMove = true;
    return this;
  }

  /**
   * Force to click on element using JavaScript, instead of build in Selenium Webdriver
   * 'webElement.click()' Set to false by default
   */
  public AbstractField clickUsingJavaScript() {
    useJavaScript = true;
    return this;
  }


  public AbstractField fillUsingJavaScript() {
    return clickUsingJavaScript();
  }

  /**
   * Perform click operation on element
   */
  public void click() {
    scrollToElement();
    moveToElement();
    clickElement();
  }

  /**
   * Perform clear operation on element
   */
  public AbstractField clear() {
    useClear = true;
    return this;
  }

  /**
   * Get current element
   *
   * @return WebElement
   */
  public WebElement getElement() {
    return driverFactory.getDriver().findElement(locator);
  }

  /**
   * Get list of matching element
   *
   * @return WebElement List
   */
  public List<WebElement> getElements() {
    return driverFactory.getDriver().findElements(locator);
  }

  /**
   * Get information if element is displayed (on page)
   *
   * @return boolean
   */
  public boolean isDisplayed() {
    try {
      return getElement().isDisplayed();

    } catch (final NoSuchElementException | ElementNotVisibleException e) {
      return false;
    }
  }

  /**
   * Get information if element is enabled (on page)
   *
   * @return boolean
   */
  public boolean isEnabled() {
    try {
      return getElement().isEnabled();

    } catch (final NoSuchElementException | ElementNotVisibleException e) {
      return false;
    }
  }

  /**
   * Get information if element is disabled (on page)
   *
   * @return boolean
   */
  public boolean isDisabled() {
    try {
      final boolean hasDisabledClass = getElement().getAttribute("class").contains("disabled");
      return !isEnabled() || hasDisabledClass;

    } catch (final NoSuchElementException | ElementNotVisibleException e) {
      return false;
    }
  }

  /**
   * Get information if element (checkbox) is selected (on page)
   *
   * @return boolean
   */
  public boolean isSelected() {
    try {
      return getElement().isSelected();

    } catch (final NoSuchElementException | ElementNotVisibleException e) {
      return false;
    }
  }


  /**
   * Perform 'webElement.sendKeys()' operation
   *
   * @param text to send
   */
  public void sendKeys(final String text) {
    final WebElement element = getElement();
    if (useClear) {
      element.clear();
    }
    if (useJavaScript) {
      jSExecutor.setAttribute(element, "value", text);
    } else {
      element.sendKeys(text);
    }

  }

  /**
   * Return field locator
   *
   * @return By
   */
  public By getFieldLocator() {
    return locator;
  }

  private void clickElement() {
    final WebElement element = getElement();
    Utils.safeWait(100);
    if (useJavaScript) {
      jSExecutor.executeScriptForElement("arguments[0].click();", element);
    } else {
      element.click();
    }
  }

  private void moveToElement() {
    if (useMove) {
      final Actions actions = new Actions(driverFactory.getDriver());
      actions.moveToElement(getElement());
      actions.perform();
    }
  }

  void scrollToElement() {
    if (useScroll) {
      final WebElement element = getElement();
      final String scrollElementIntoMiddle =
          "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
              + "var elementTop = arguments[0].getBoundingClientRect().top;"
              + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
      jSExecutor.executeScriptForElement(scrollElementIntoMiddle, element);
    }
  }

  public boolean containsText(final String text) {
    return getElement().getText().toLowerCase().contains(text.toLowerCase());
  }

  /**
   * Default operatiton executed using 'I fill form with values' cucumber step Method needs to be
   * overridden
   *
   * @param text to send (if needed)
   */
  public abstract void fillFormOperation(String text);

  void tryClickElement() {
    try {
      click();
      Utils.safeWait(300, "to set value into field in browser: " + getFieldLocator());
    } catch (final ElementNotInteractableException exception) {
      logger
          .warn(String.format("Could not click %s because of exception %s", getElement(),
              exception));
    }
  }

  /**
   * An operation of erasing characters from chosen field performed by repeating hitting BACKSPACE
   * key from the keyboard x times where x is determined by provided parameter
   *
   * @param charsNumber should be provided to determine the number of characters to erase
   */

  public void eraseChars(final int charsNumber) {
    final WebElement element = getElement();
    for (int i = 0; i < charsNumber; i++) {
      element.sendKeys(Keys.BACK_SPACE);
    }
  }
}
