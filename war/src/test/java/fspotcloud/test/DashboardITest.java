package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class DashboardITest extends SeleniumITestCase {

	public DashboardITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
		setName("DashboardITest");
	}

	public DashboardITest() {
		setName("DashboardITest");
	}

	@Override
	protected void runTest() throws Throwable {
		testImport();
	}

	public void loginDevAppServer() throws Exception {
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
	}
	public void testImport() throws Exception {
		loginDevAppServer();
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-update-button");
		sleepShort(3);
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		//import Furniture		
		selenium.click("gwt-debug-import-tag-button");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("gwt-debug-load-images-button");
		selenium.click("gwt-debug-count-photos-button");
		sleepShort(4);
		sleepShort();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { selenium.open("/Dashboard.html");
			selenium.waitForPageToLoad("10000");
			if ("28".equals(selenium.getText("gwt-debug-photo-count-label"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
	}

}
