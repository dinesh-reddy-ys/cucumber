package com.herokuapp.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	By username = By.xpath("//input[@placeholder='Username']");
    By password = By.xpath("//input[@placeholder='Password']");
    By loginBtn = By.xpath("//button[@type='submit']");
    
    public void enterUsername(String user) {
    	WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    	usernameField.sendKeys(user);
    }
    public void enterPassword(String pass) {
    	WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
    	passwordField.sendKeys(pass);
    }
    public void clickLogin() {
    	WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginButton.click();
    }

	

}
