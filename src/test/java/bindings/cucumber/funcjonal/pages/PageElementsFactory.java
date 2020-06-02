package bindings.cucumber.funcjonal.pages;

import bindings.cucumber.funcjonal.pages.fields.SelectField;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import bindings.driver.DriverFactory;
import bindings.cucumber.funcjonal.pages.fields.Button;
import bindings.cucumber.funcjonal.pages.fields.AbstractField;
import bindings.cucumber.funcjonal.pages.fields.Checkbox;
import bindings.cucumber.funcjonal.pages.fields.Field;
import bindings.cucumber.funcjonal.pages.fields.Slider;
@Component
public class PageElementsFactory {
    private final DriverFactory driverFactory;
    private final JSExecutor jSExecutor;

    public PageElementsFactory(final DriverFactory driverFactory,
                               final JSExecutor jSExecutor) {
        this.driverFactory = driverFactory;
        this.jSExecutor = jSExecutor;
    }


    public Field aField(final By by) {
        return new Field(by, this.driverFactory, this.jSExecutor);
    }

    public Button aButton(final By by) {
        return new Button(by, this.driverFactory, this.jSExecutor);
    }

    public Checkbox aCheckbox(final By by) {
        return new Checkbox(by, this.driverFactory, this.jSExecutor);
    }

    public SelectField aSelectField(final By by) {
        return new SelectField(by, this.driverFactory, this.jSExecutor);
    }

    public Slider createSlider(final By by) {
        return new Slider(by, this.driverFactory, this.jSExecutor);
    }
}
