

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.Cucumber;
//import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        //format = {"pretty", "html:target/Destination"},
        features={"src/test/features/TestScript_1.feature"},
        glue = "Payload"
)
public class testRunner {

}
