package fspotcloud.test;

import junit.framework.TestCase;

import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class AdminITest extends TestCase {

	FirefoxDriver driver;
	Selenium selenium;

	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		// Create the Selenium implementation
		selenium = new WebDriverBackedSelenium(driver, "http://localhost:8080");
	}
	
	

	@Override
	protected void tearDown() throws Exception {
		selenium.stop();
		super.tearDown();
	}



	public void testAdminI() throws Exception {
		selenium.open("http://localhost:8080/Admin.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
	}
}
