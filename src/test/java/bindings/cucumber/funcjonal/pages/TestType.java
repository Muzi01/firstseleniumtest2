package bindings.cucumber.funcjonal.pages;

import java.util.Arrays;
import java.util.Collection;



public enum TestType {

    NON_FUNCTIONAL ("") {

        public <LaunchApplicationStepsImpl> void prepareForTestExecution (
                final LaunchApplicationStepsImpl launchApplicationStepsImpl) {
            // doNothing
        }
    };

    public final String tagValue;

    TestType (final String tagValue) {
        this.tagValue = tagValue;
    }

    public static TestType provideTestType (final Collection<String> testcaseTags) {
        return Arrays.stream (TestType.values ())
                .filter (testType -> testcaseTags.contains (testType.tagValue))
                .findFirst ()
                .orElse (TestType.NON_FUNCTIONAL);
    }
}
