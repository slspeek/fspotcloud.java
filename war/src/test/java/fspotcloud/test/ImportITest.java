package fspotcloud.test;

import junit.framework.TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.thoughtworks.selenium.Selenium;

public class ImportITest extends TestCase {

	@SuppressWarnings("unused")
	final private WebDriver driver;
	final private Selenium selenium;
	@SuppressWarnings("unused")
	final private String baseURL;

	public ImportITest(WebDriver driver, String baseURL) {
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
		testImportTags();
	}

	public void testAdminI() throws Exception {
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
	}

	public void testImportTags() throws Exception {
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		selenium.click("//tr[3]/td[2]/button");
		selenium.waitForPageToLoad("30000");
		sleep(2000);
		selenium.click("//tr[4]/td[2]/button");
		selenium.waitForPageToLoad("30000");
		sleep(5000);
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		sleep(3000);

		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		sleep(3000);

		selenium.click("//tr[4]/td[2]/button");
		selenium.waitForPageToLoad("30000");
		sleep(15000);
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		sleep(3000);
		selenium.open("/TestHelper.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Import tag 1");
		selenium.waitForPageToLoad("30000");
		selenium.open("/TestHelper.html");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=Import tag 2");
		selenium.waitForPageToLoad("30000");

		selenium.open("/TestHelper.html");
		selenium.waitForPageToLoad("30000");

		selenium.waitForPageToLoad("30000");
		selenium.click("link=Import tag 3");
		selenium.waitForPageToLoad("30000");
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		sleep(15000);
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
