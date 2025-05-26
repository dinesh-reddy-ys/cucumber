package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
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
     * Navigate to application URL and verify login page is displayed
     */
    @Given("User is on the login screen")
    public void userIsOnLoginScreen() {
        driver.get("https://opensource-demo.orangehrmlive.com");
        driver.manage().window().maximize();
        Assert.assertTrue("Login page is not displayed", loginPage.isLoginPageDisplayed());
    }

    /**
     * Enter predefined valid username and password
     */
    @When("User enters valid username and password")
    public void userEntersValidCredentials() {
        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
    }

    /**
     * Click the login button to submit credentials
     */
    @When("User clicks on login button")
    public void userClicksLoginButton() {
        loginPage.clickLogin();
    }

    /**
     * Verify successful navigation to home page
     */
    @Then("User should be navigated to home page")
    public void userShouldBeNavigatedToHomePage() {
        Assert.assertTrue("Home page is not displayed", loginPage.isHomePageDisplayed());
    }

    /**
     * Verify welcome message is displayed after successful login
     */
    @Then("User should see welcome message")
    public void userShouldSeeWelcomeMessage() {
        Assert.assertTrue("Welcome message is not displayed", loginPage.isWelcomeMessageDisplayed());
    }

    // Invalid Login Scenario Steps

    /**
     * Enter only valid username
     */
    @When("User enters valid username")
    public void userEntersValidUsername() {
        loginPage.enterUsername(VALID_USERNAME);
    }

    /**
     * Enter invalid password for testing error scenarios
     */
    @When("User enters invalid password")
    public void userEntersInvalidPassword() {
        loginPage.enterPassword("wrongpassword");
    }

    /**
     * Enter invalid username for testing error scenarios
     */
    @When("User enters invalid username")
    public void userEntersInvalidUsername() {
        loginPage.enterUsername("invaliduser");
    }

    /**
     * Enter only valid password
     */
    @When("User enters valid password")
    public void userEntersValidPassword() {
        loginPage.enterPassword(VALID_PASSWORD);
    }

    /**
     * Verify error message matches expected message
     * @param expectedMessage The expected error message text
     */
    @Then("User should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals("Error message doesn't match", expectedMessage, actualError);
    }

    /**
     * Verify user remains on login page after failed attempt
     */
    @Then("User should remain on login screen")
    public void userShouldRemainOnLoginScreen() {
        Assert.assertTrue("Login page is not displayed", loginPage.isLoginPageDisplayed());
    }

    // Empty Credentials Scenario Steps

    /**
     * Attempt login without entering any credentials
     */
    @When("User clicks on login button without entering credentials")
    public void userClicksLoginButtonWithoutCredentials() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
    }

    /**
     * Verify username field validation message
     */
    @Then("User should see validation message for username field")
    public void userShouldSeeUsernameValidation() {
        Assert.assertTrue("Username validation message not displayed", 
            loginPage.isUsernameValidationDisplayed());
    }

    /**
     * Verify password field validation message
     */
    @Then("User should see validation message for password field")
    public void userShouldSeePasswordValidation() {
        Assert.assertTrue("Password validation message not displayed", 
            loginPage.isPasswordValidationDisplayed());
    }

    // Account Lock Scenario Steps

    /**
     * Set up locked account state for testing
     */
    @Given("User account is locked")
    public void userAccountIsLocked() {
        loginPage.setAccountLocked(true);
    }

    /**
     * Verify account reset option is available
     */
    @Then("User should see option to reset account")
    public void userShouldSeeResetOption() {
        Assert.assertTrue("Reset account option not displayed", 
            loginPage.isResetOptionDisplayed());
    }

    // Password Reset Scenario Steps

    /**
     * Click on specified link text
     * @param linkText The text of the link to click
     */
    @When("User clicks on {string} link")
    public void userClicksOnLink(String linkText) {
        loginPage.clickLink(linkText);
    }

    /**
     * Verify navigation to password reset page
     */
    @Then("User should be redirected to password reset page")
    public void userShouldBeRedirectedToPasswordResetPage() {
        Assert.assertTrue("Password reset page not displayed", 
            loginPage.isPasswordResetPageDisplayed());
    }

    // Remember Me Functionality Steps

    /**
     * Check remember me checkbox
     * @param checkboxLabel The label of checkbox to check
     */
    @When("User checks {string} checkbox")
    public void userChecksCheckbox(String checkboxLabel) {
        loginPage.checkRememberMe();
    }

    /**
     * Verify successful login
     */
    @Then("User should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        Assert.assertTrue("Login was not successful", loginPage.isLoginSuccessful());
    }

    /**
     * Verify credentials are remembered after page refresh
     */
    @Then("User credentials should be remembered for next login")
    public void userCredentialsShouldBeRemembered() {
        driver.navigate().refresh();
        Assert.assertTrue("Credentials were not remembered", 
            loginPage.areCredentialsRemembered());
    }

    // Account Lock After Multiple Attempts Steps

    /**
     * Attempt login with invalid credentials multiple times
     * @param times Number of login attempts to make
     */
    @When("User enters invalid credentials {int} times")
    public void userEntersInvalidCredentialsTimes(int times) {
        for (int i = 0; i < times; i++) {
            loginPage.enterUsername("wronguser");
            loginPage.enterPassword("wrongpass");
            loginPage.clickLogin();
            loginAttempts++;
        }
    }

    /**
     * Verify account is temporarily locked
     */
    @Then("User account should be temporarily locked")
    public void userAccountShouldBeTemporarilyLocked() {
        Assert.assertTrue("Account is not locked after multiple attempts", 
            loginPage.isAccountTemporarilyLocked());
    }

    // Special Characters Handling Steps

    /**
     * Enter username containing special characters
     */
    @When("User enters username with special characters")
    public void userEntersUsernameWithSpecialChars() {
        loginPage.enterUsername("user@#$%");
    }

    /**
     * Enter password containing special characters
     */
    @When("User enters password with special characters")
    public void userEntersPasswordWithSpecialChars() {
        loginPage.enterPassword("pass@#$%");
    }

    // Role-based Login Steps

    /**
     * Enter specific username and password combination
     * @param username Username to enter
     * @param password Password to enter
     */
    @When("User enters {string} and {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    /**
     * Verify user has correct role permissions
     * @param role Expected user role
     */
    @Then("User should be logged in with {string} permissions")
    public void userShouldBeLoggedInWithPermissions(String role) {
        Assert.assertTrue("User role is not correct", 
            loginPage.verifyUserRole(role));
    }

    /**
     * Verify role-specific dashboard is displayed
     * @param role User role to check dashboard for
     */
    @And("User should see {string} specific dashboard")
    public void userShouldSeeDashboard(String role) {
        Assert.assertTrue("Role-specific dashboard not displayed", 
            loginPage.isDashboardDisplayedForRole(role));
    }
}