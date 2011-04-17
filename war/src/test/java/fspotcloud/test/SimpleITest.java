package fspotcloud.test;

import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;

public class SimpleITest extends TestCase {

	final WebDriver driver;
	final String baseURL;

	public SimpleITest(WebDriver driver, String baseURL) {
		this.driver = driver;
		this.baseURL = baseURL;
	}

	@Override
	public void runTest() throws Exception {
		testBaseURL();
	}

	@Override
	protected void tearDown() throws Exception {
		driver.quit();
		super.tearDown();
	}

	public void testBaseURL() throws Exception {
		driver.get(baseURL);
	}
}
