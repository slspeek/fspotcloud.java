package fspotcloud.test;

import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.thoughtworks.selenium.Selenium;

public class NavigationITest extends TestCase {

	@SuppressWarnings("unused")
	final private WebDriver driver;
	final private Selenium selenium;
	@SuppressWarnings("unused")
	final private String baseURL;

	public NavigationITest(WebDriver driver, String baseURL) {
		this.driver = driver;
		this.baseURL = baseURL;
		selenium = new WebDriverBackedSelenium(driver, baseURL);
	}

	@Override
	protected void tearDown() throws Exception {
		selenium.stop();
		super.tearDown();
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}
	
	@Override
	protected void runTest() throws Throwable {
		testSomeNavigation();
	}

	public void testSomeNavigation() throws Exception {
		selenium.open("/");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[3]/div");
		sleep(900);
		selenium.click("//td[3]/div");
		sleep(900);
		selenium.click("//div[1]/div[2]/div[1]/div/div[2]");
		selenium.waitForPageToLoad("30000");

		selenium.click("//td[3]/div");
		selenium.waitForPageToLoad("30000");
		sleep(900);
		selenium.click("//td[5]/div");
		sleep(100);
		assertTrue(selenium.isTextPresent("4 seconds."));
		selenium.open("/#ImageViewingPlace:1:27");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[1]/div/table/tbody/tr/td[1]/div");
		selenium.waitForPageToLoad("30000");
	}
}
