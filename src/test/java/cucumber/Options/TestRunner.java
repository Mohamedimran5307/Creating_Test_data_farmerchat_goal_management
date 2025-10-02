package cucumber.Options;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepDefinitions"},
    plugin = {
        "json:target/jsonReports/cucumber-report.json",
        "html:target/cucumber-html-reports",
        "pretty"
    },
    monochrome = true,
    publish = true,
    tags = "@Ask_Text_query"
)
public class TestRunner {
}

