package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class DeleteITest extends SeleniumITestCase {

	public DeleteITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
		setName("DashboardITest");
	}

	public DeleteITest() {
		setName("DashboardITest");
	}

	@Override
	protected void runTest() throws Throwable {
		testClear();
	}

	public void loginDevAppServer() throws Exception {
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
	}

	public void testClear() throws Exception {
		loginDevAppServer();
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-delete-all-tags-button");
		sleepShort();
		
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		sleepShort();
	}
}
