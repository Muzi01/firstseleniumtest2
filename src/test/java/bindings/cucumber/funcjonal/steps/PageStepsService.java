package bindings.cucumber.funcjonal.steps;

import bindings.cucumber.funcjonal.pages.JSExecutor;
import bindings.cucumber.funcjonal.pages.ModalWindow;
import bindings.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.jvnet.hk2.annotations.Service;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static bindings.cucumber.funcjonal.pages.AbstractPage.containsText;

@Service
public class PageStepsService {

    private static final Logger LOGGER = LogManager.getLogger (PageStepsService.class);
    private static final String LAST = "last";
    private static final String FIRST = "first";
    private final PageFactory pageFactory;
    private final DriverFactory driverFactory;
    private final JSExecutor jSExecutor;
    private final ModalWindow modalWindow;


    public PageStepsService (final PageFactory pageFactory,
                             final DriverFactory driverFactory,
                             final JSExecutor jSExecutor,
                             final ModalWindow modalWindow) {
        this.pageFactory = pageFactory;
        this.driverFactory = driverFactory;
        this.jSExecutor = jSExecutor;
        this.modalWindow = modalWindow;
    }

    public void pageContainsText (final String textPattern) {
        containsText (driverFactory, textPattern);
    }

    void pageSourceContains (final String textPattern) {
        final boolean isContained =
                pageFactory.getCurrentPage ().pageSourceContainsText (textPattern);
        Assert.assertTrue ("Pattern not found in page source: " + textPattern, isContained);
    }

    public void sectionContains (final String section, final String textPattern) {
        final boolean isDisplayed = pageFactory.getCurrentPage ().containsText (section,
                customerDataHolder.getTestData (textPattern));
        Assert.assertTrue (
                "In " + section + " not found: " + customerDataHolder.getTestData (textPattern),
                isDisplayed);
    }
    public void elementContainsText (final String element, final String textPattern) {
        final boolean isDisplayed = pageFactory.getCurrentPage ()
                .elementContainsTextInScript (customerDataHolder.getTestData (element), textPattern);
        Assert.assertTrue (
                textPattern + " not found in element: " + customerDataHolder.getTestData (element),
                isDisplayed);
    }
        void pageContainsValues(final DataTable dataTable) {
            final List<String> elements = dataTable.asList(String.class);
            elements.forEach(this::pageContains);
        }
}

