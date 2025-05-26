package steps;

import org.openqa.selenium.WebDriver;
import com.herokuapp.automation.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.Hooks;

public class LoginSteps  {

	WebDriver driver;
	LoginPage loginPage;

	public LoginSteps() {
		this.driver = Hooks.getDriver();
	}
    
	@Given("User on the login screen")
	public void appIsLaunched() throws Exception {
		
		
		System.out.println("launching the app");
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		System.out.println("User is on the login screen");

	}

	@When("User enter login details and login")
	public void enterCredentials() {
		// Add logic to interact with login elements
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
		
	}

	@Then("User navigate to home page")
	public void verifyHomeScreen() {
		// Add logic to verify successful login
		System.out.println("User is on the home page");
	}

}
