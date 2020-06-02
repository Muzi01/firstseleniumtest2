package bindings.cucumber.funcjonal.pages.fields;


import org.openqa.selenium.By;
import bindings.driver.DriverFactory;
import bindings.cucumber.funcjonal.pages.JSExecutor;

public class Button extends AbstractField {

  public Button(final By by, final DriverFactory driverFactory, final JSExecutor jSExecutor) {
    super(by, driverFactory, jSExecutor);
  }

  @Override
  public void fillFormOperation(final String text) {
    this.click();
  }
}
