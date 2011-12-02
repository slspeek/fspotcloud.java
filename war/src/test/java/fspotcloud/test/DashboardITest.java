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
		sleepShort(9);
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		//import Furniture		
		selenium.click("gwt-debug-import-tag-button");
		selenium.waitForPageToLoad("30000");
	}
}
