package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class DeleteITest extends SeleniumITest {

	public DeleteITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
		setName("DashboardITest");
	}

	public DeleteITest() {
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
		selenium.waitForPageToLoad("2000");
		selenium.click("gwt-debug-delete-all-tags-button");
		sleepShort();
		selenium.click("gwt-debug-delete-all-photos-button");
		sleepShort();
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("5000");
		sleepShort();
	}
}
