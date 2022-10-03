package kg.proquality.e2e;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/testArchitecture/resources/features",
        glue = {"kg.proquality.e2e"}
        , tags = "not @WIP"
)
public class CucumberRunner {
}
