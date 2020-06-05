package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue= {"bindings"},
        plugin = { "pretty" },
        tags = {"@links"},
        monochrome = true
)

public class TestRunner {

}