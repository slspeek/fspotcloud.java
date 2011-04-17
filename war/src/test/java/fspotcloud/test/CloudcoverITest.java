package fspotcloud.test;

import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class CloudcoverITest extends TestCase {

	@SuppressWarnings("unused")
	final private WebDriver driver;
	final private Selenium selenium;
	@SuppressWarnings("unused")
	final private String baseURL;

	public CloudcoverITest(WebDriver driver, String baseURL) {
		this.driver = driver;
		this.baseURL = baseURL;
		selenium = new WebDriverBackedSelenium(driver, baseURL);
	}

	public CloudcoverITest() {
		this(new FirefoxDriver(), "http://localhost:8080");
	}
	
	@Override
	protected void tearDown() throws Exception {
		selenium.stop();
		super.tearDown();
	}
	
	@Override
	protected void runTest() throws Throwable {
		testRunModel();
		testRunServer();
	}

	public void testRunModel() throws Exception {
		selenium.open("/cloudcover.html");
		selenium.waitForPageToLoad("30000");
	}

	public void testRunServer() throws Exception {
		selenium.open("/cloudcover.html");
		selenium.waitForPageToLoad("30000");
	}
}
