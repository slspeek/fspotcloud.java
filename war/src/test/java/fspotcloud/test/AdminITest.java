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
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.click("//tr[4]/td[2]/button");
		selenium.waitForPageToLoad("30000");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		selenium.click("//tr[4]/td[2]/button");
		selenium.waitForPageToLoad("30000");
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.open("/");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[3]/div");
		try {
			Thread.sleep(900);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.click("//td[3]/div");
		try {
			Thread.sleep(900);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.click("//div[1]/div[2]/div[1]/div/div[2]");
		selenium.waitForPageToLoad("30000");

		selenium.click("//td[3]/div");
		selenium.waitForPageToLoad("30000");

		try {
			Thread.sleep(900);
		} catch (Exception e) {
			// TODO: handle exception
		}
		selenium.click("//td[5]/div");
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertTrue(selenium.isTextPresent("4 seconds."));
		selenium.open("/#ImageViewingPlace:1:27");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[1]/div/table/tbody/tr/td[1]/div");
		selenium.waitForPageToLoad("30000");
		try {
			Thread.sleep(900);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
