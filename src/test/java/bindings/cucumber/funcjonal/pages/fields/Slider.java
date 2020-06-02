package bindings.cucumber.funcjonal.pages.fields;
import bindings.driver.DriverFactory;
import bindings.cucumber.funcjonal.pages.JSExecutor;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Slider extends AbstractField {
  private boolean updateAttribute;
  private boolean moveToPosition;

  public Slider(final By locator, final DriverFactory driverFactory,
      final JSExecutor jSExecutor) {
    super(locator, driverFactory, jSExecutor);
    this.updateAttribute = false;
    this.moveToPosition = false;
  }

  /**
   * Use this method when slider has "style="left: PERCENT_VALUE%;" construction. Usable for all
   * Hapi markets.
   */
  public Slider useUpdateAttribute() {
    this.updateAttribute = true;
    return this;
  }

  public Slider setMoveToPosition() {
    this.moveToPosition = true;
    return this;
  }

  @Override
  public void fillFormOperation(final String percent) {
    if (this.updateAttribute) {
      moveSlider(percent);
    } else if (this.moveToPosition) {
      moveToPosition(percent);
    } else {
      throw new IllegalStateException("Slider has no flag added to mapping");
    }
  }

  private void moveSlider(final String percent) {
    final String styleValue = String.format("left: %s%%;", Integer.parseInt(percent));
    this.jSExecutor.setAttribute(this.getElement(), "style", styleValue);
  }

  private void moveToPosition(final String percent) {
    final String sliderXPath = this.getFieldLocator().toString().split(": ")[1];

    final int offset = (percent.equals("100")) ? 2 : 1;
    final List<WebElement> sliderPositions =
        this.getElement().findElements(By.xpath(sliderXPath + "/li"));
    final int targetInList = (Integer.parseInt(percent) * (sliderPositions.size() - offset)) / 100;
    final WebElement position = sliderPositions.get(targetInList);
    try {
      position.click();
    } catch (final WebDriverException e) {
      LogManager.getLogger(Slider.class)
          .info("Position not changed, because it was hidden behind the Slider handle");
    }
  }
}
