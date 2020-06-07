package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue= {"bindings"},
        plugin = { "pretty" },
        tags = {"@TEST-88653"},
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @DataProvider (parallel = true)

    public Object [][] scenarios () {
        return super.scenarios();
    }

}