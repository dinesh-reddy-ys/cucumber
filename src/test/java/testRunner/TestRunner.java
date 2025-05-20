package testRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)	
@CucumberOptions(
	features = "features", // Path to feature files
	glue = {"steps","tests"},                // Package containing step definitions
	plugin = {"pretty", "html:target/cucumber-reports.html"}, // Report generation
	monochrome = true                      // Makes console output readable
	                     // Runs scenarios with this tag
)
public class TestRunner {
	// This class will be empty. It is used only as a holder for the above annotations
	static {
		System.out.print("Helloo");
	}
	@Test
	public void testRunner() {
		System.out.println("Test Runner Executed");
	}
	
}
	


