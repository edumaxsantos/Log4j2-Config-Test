package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:output/html", "json:output/cucumber.json", "junit:output/cucumber.xml"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        dryRun = false,
        strict = true
)
public class Runner {
}
