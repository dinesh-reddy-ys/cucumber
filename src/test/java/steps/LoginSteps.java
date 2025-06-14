package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.herokuapp.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import tests.Hooks;

/**
 * Step definitions for Login feature
 * Contains implementation of all scenarios related to login functionality
 */
public class LoginSteps {

    // Core components
    private WebDriver driver;
    private LoginPage loginPage;
    
    // Test data and constants
    private int loginAttempts = 0;
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "admin123";

    /**
     * Constructor initializes WebDriver and LoginPage instances
     * Gets driver instance from Hooks class
     */
    public LoginSteps() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    // Basic Login Scenario Steps

    /**
     Successful login with valid credentials
     **/
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.navigateToLoginPage();
    }
    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();
    }
    @Then("I should be logged in")
    public void i_should_be_logged_in() {
       
       System.out.println("Login Successful");
    }
   }






