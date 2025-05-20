package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	
	public static WebDriver driver;
	
	
	@Before
	public void setUp() {
		// Initialize WebDriver here
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
	}
	
	@After
	public void tearDown() {
		// Quit WebDriver here
		if (driver != null) {
			driver.quit();
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	

}
