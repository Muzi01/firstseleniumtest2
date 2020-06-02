package bindings.cucumber.funcjonal.pages.fields;
import bindings.driver.DriverFactory;
import bindings.driver.DriverConfig;
import bindings.cucumber.funcjonal.pages.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Field extends AbstractField {
  public Field(final By locator, final DriverFactory driverFactory,
      final JSExecutor jSExecutor) {
    super(locator, driverFactory, jSExecutor);
  }

  @Override
  public void fillFormOperation(final String text) {
    this.tryClickElement();
    final WebDriverWait wait =
        new WebDriverWait(this.driverFactory.getDriver(), DriverConfig.MAX_PAGE_LOAD_TIME);
    wait.until(
        ExpectedConditions.not(
            ExpectedConditions.stalenessOf(this.getElement())));

    this.sendKeys(text);
  }
}
