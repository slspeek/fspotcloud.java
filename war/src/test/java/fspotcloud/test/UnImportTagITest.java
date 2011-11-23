package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class UnImportTagITest extends SeleniumITestCase {

	public UnImportTagITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
		setName("DashboardITest");
	}

	public UnImportTagITest() {
		setName("DashboardITest");
	}

	public void loginDevAppServer() throws Exception {
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
	}

	public void testUnImport() throws Exception {
		loginDevAppServer();
		// unimport Furniture
		selenium.click("gwt-debug-import-tag-button");
		selenium.waitForPageToLoad("30000");
		sleepShort(6);
		selenium.click("gwt-debug-count-photos-button");
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if ("0".equals(selenium.getText("gwt-debug-photo-count-label")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		} 

	}

}
