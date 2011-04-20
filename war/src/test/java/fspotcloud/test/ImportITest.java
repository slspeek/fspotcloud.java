package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class ImportITest extends SeleniumITest {

	public ImportITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}
	
	public ImportITest() {
	}
	
	@Override
	protected void runTest() throws Throwable {
		testImportTags();
	}

	public void loginDevAppServer() throws Exception {
		selenium.open("/Admin.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
	}

	public void testImportTags() throws Exception {
		loginDevAppServer();
		for (int second = 0;; second++) {
			if (second >= 5)
				fail("timeout");
			try {
				if (selenium.isTextPresent("ServerPhotoCount batch finished."))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		selenium.click("//tr[3]/td[2]/button");
		for (int second = 0;; second++) {
			if (second >= 5)
				fail("timeout");
			try {
				if (selenium.isTextPresent("Import tags was scheduled for the peer"))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		selenium.click("//tr[4]/td[2]/button");
		for (int second = 0;; second++) {
			if (second >= 5)
				fail("timeout");
			try {
				if (selenium.isTextPresent("Update photo metadata was scheduled for the peer"))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		selenium.open("/Admin.html");
		for (int second = 0;; second++) {
			if (second >= 5)
				fail("timeout");
			try {
				if (selenium.isTextPresent("ServerPhotoCount batch finished."))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		Thread.sleep(3000);
		selenium.open("/Admin.html");
		for (int second = 0;; second++) {
			if (second >= 5) fail("timeout");
			try { if ("33".equals(selenium.getText("//tr[2]/td[2]"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
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
		Thread.sleep(10000);
	}
}
