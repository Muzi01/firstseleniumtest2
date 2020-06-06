package runners;


import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:reports/cucumber-html-report",
                "json:reports/cucumber.json",
                "pretty"},
        tags = {"@login"},
        features = {"src/test/resources"},
        glue = {"bindings.cucumber.functional"}
)

public class MyRunner {

}