package bindings.cucumber.funcjonal.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import bindings.driver.DriverFactory;


@Service
public class JSExecutor {

    private final DriverFactory driverFactory;

    public JSExecutor(final DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    public void executeScript(final String script) {
        final JavascriptExecutor javascriptExecutor =
                (JavascriptExecutor) this.driverFactory.getDriver();
        javascriptExecutor.executeScript(script);
    }

    public void executeScriptForElement(final String script, final WebElement element) {
        final JavascriptExecutor javascriptExecutor =
                (JavascriptExecutor) this.driverFactory.getDriver();
        javascriptExecutor.executeScript(script, element);
    }

    public void setAttribute(final WebElement element, final String attributeName,
                             final String attributeValue) {
        final JavascriptExecutor javascriptExecutor =
                (JavascriptExecutor) this.driverFactory.getDriver();
        final String jsStatement =
                String.format("arguments[0].setAttribute('%s', '%s')", attributeName, attributeValue);
        javascriptExecutor.executeScript(jsStatement, element);
    }

    public Object executeSriptAndReturnObject(final String script) {
        final JavascriptExecutor javascriptExecutor =
                (JavascriptExecutor) this.driverFactory.getDriver();
        return javascriptExecutor.executeScript(script);
    }

}
