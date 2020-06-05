package tests.legacy;

import io.cucumber.junit.CucumberOptions;

// NOSONAR - this is an example of usage

@CucumberOptions(
        features = {"src/test/features"},
        glue = {"com.ipfdigital.core.cucumber", "com.pl.ipf.stepdefinitions",
                "com.ipfdigital.steps", "com.ipfdigital.screenplay"},
        plugin = {"pretty", "html:target/cukes"},
        tags = {"@TEST-88653"}) // use needed tags combination with cucumber specification ex.
// @critical and @pl

public class ManualTestRunner {





}
