package fspotcloud.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.google.inject.Provider;
import com.thoughtworks.selenium.Selenium;

public class SeleniumITest extends WebDriverITest {

	protected Selenium selenium;
	
	public SeleniumITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}
	
	public SeleniumITest() {
		super();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		selenium = new WebDriverBackedSelenium(driver, baseURL);
	}

	@Override
	protected void tearDown() throws Exception {
		selenium.stop();
		super.tearDown();
	}
	
}
