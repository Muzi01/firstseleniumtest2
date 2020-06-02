package bindings.cucumber.funcjonal.pages.fields;

import bindings.driver.DriverFactory;
import bindings.cucumber.funcjonal.pages.JSExecutor;
import org.openqa.selenium.By;

public class Checkbox extends AbstractField {
  public Checkbox(final By locator, final DriverFactory driverFactory,
      final JSExecutor jSExecutor) {
    super(locator, driverFactory, jSExecutor);
  }

  @Override
  public void fillFormOperation(final String value) {
    if (!value.equalsIgnoreCase("No")) {
      this.click();
    }
  }
}
