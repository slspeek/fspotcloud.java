package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class SimpleITest extends WebDriverITest {

	public SimpleITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}
	
	@Override
	public void runTest() throws Exception {
		testBaseURL();
	}

	public void testBaseURL() throws Exception {
		driver.get(baseURL);
	}
}
