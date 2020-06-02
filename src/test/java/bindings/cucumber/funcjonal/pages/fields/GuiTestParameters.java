package bindings.cucumber.funcjonal.pages.fields;

import java.util.Collection;


import bindings.cucumber.funcjonal.pages.StoryProxyComponent;
import bindings.cucumber.funcjonal.pages.TestType;

@StoryProxyComponent
public class GuiTestParameters {
    private TestType testType = TestType.NON_FUNCTIONAL;

    public TestType getTestType() {
        return testType;
    }

    public void setupTestType(final Collection<String> tags) {
        testType = TestType.provideTestType(tags);
    }

}
