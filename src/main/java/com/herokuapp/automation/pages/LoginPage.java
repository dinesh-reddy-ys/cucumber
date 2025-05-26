package com.herokuapp.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object class representing the Login Page of the application.
 * Contains all elements and methods related to login functionality.
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT_SECONDS = 10;

    // Web Elements with descriptive locators
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(className = "error-message")
    private WebElement errorMessage;

    @FindBy(id = "welcome-message")
    private WebElement welcomeMessage;

    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckbox;

    /**
     * Constructor initializes the page elements and WebDriverWait
     * @param driver WebDriver instance to interact with browser
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters username in the username field
     * @param username String value to be entered as username
     */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /**
     * Enters password in the password field
     * @param password String value to be entered as password
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the login button to submit credentials
     */
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    /**
     * Verifies if login page is currently displayed
     * @return boolean indicating if login page elements are visible
     */
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            return usernameField.isDisplayed() && passwordField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifies if home page is displayed after successful login
     * @return boolean indicating successful navigation to home page
     */
    public boolean isHomePageDisplayed() {
        try {
            // Add implementation to verify specific home page elements
            return wait.until(ExpectedConditions.urlContains("/dashboard"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves error message text from error message element
     * @return String containing the error message
     */
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    /**
     * Checks the Remember Me checkbox if not already selected
     */
    public void checkRememberMe() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    /**
     * Verifies if welcome message is displayed after successful login
     * @return boolean indicating if welcome message is visible
     */
    public boolean isWelcomeMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if username validation message is displayed
     * @return boolean indicating if username validation message is present
     */
    public boolean isUsernameValidationDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().contains("Username is required");
    }

    /**
     * Checks if password validation message is displayed
     * @return boolean indicating if password validation message is present
     */
    public boolean isPasswordValidationDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().contains("Password is required");
    }

    /**
     * Sets the account locked status (for testing purposes)
     * @param isLocked boolean indicating if account should be locked
     */
    public void setAccountLocked(boolean isLocked) {
        // Implementation depends on application's account locking mechanism
        // This might involve API calls or database updates
    }

    /**
     * Verifies if reset password option is displayed
     * @return boolean indicating if reset option is visible
     */
    public boolean isResetOptionDisplayed() {
        // Implementation to check for password reset link/button visibility
        return false;
    }

    /**
     * Clicks on a specified link by text
     * @param linkText text of the link to be clicked
     */
    public void clickLink(String linkText) {
        // Implementation to find and click link by text
        // Consider using By.linkText() or By.partialLinkText()
    }

    /**
     * Verifies if password reset page is displayed
     * @return boolean indicating if password reset page is loaded
     */
    public boolean isPasswordResetPageDisplayed() {
        // Implementation to verify password reset page elements
        return false;
    }

    /**
     * Checks if credentials are remembered after page refresh
     * @return boolean indicating if credentials are auto-filled
     */
    public boolean areCredentialsRemembered() {
        try {
            // Check if username field is pre-filled
            return !usernameField.getAttribute("value").isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifies if account is temporarily locked
     * @return boolean indicating if account is locked
     */
    public boolean isAccountTemporarilyLocked() {
        // Implementation to check account lock status
        return false;
    }

    /**
     * Verifies user role after login
     * @param role expected user role
     * @return boolean indicating if user has correct role
     */
    public boolean verifyUserRole(String role) {
        // Implementation to verify user role from UI elements or API
        return false;
    }

    /**
     * Checks if role-specific dashboard is displayed
     * @param role user role to verify dashboard for
     * @return boolean indicating if correct dashboard is displayed
     */
    public boolean isDashboardDisplayedForRole(String role) {
        // Implementation to verify role-specific dashboard elements
        return false;
    }

    /**
     * Verifies if login was successful
     * @return boolean indicating successful login
     */
    public boolean isLoginSuccessful() {
        try {
            return isHomePageDisplayed() && isWelcomeMessageDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}