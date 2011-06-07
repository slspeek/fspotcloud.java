package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class DashboardITest extends SeleniumITest {

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
		selenium.click("gwt-debug-import-tags-button");
		sleepShort();
		selenium.click("gwt-debug-update-button");
		sleepShort(3);
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		selenium.click("//div[3]/div[2]/div/div[4]/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/img");
		sleepShort();
		selenium.click("//div[1]/div[3]/div/div/div[2]");
		sleepShort();

		selenium.click("//div[3]/div[1]/div/div[2]");
		sleepShort();

		selenium.click("gwt-debug-import-tag-button");
		sleepShort();
		
		selenium.click("//div/div/div/div[1]/div[3]/div");
		sleepShort();

		selenium.click("gwt-debug-import-tag-button");
		sleepShort();

		selenium.click("//div[4]/div/div/div[2]");
		sleepShort();

		selenium.click("gwt-debug-import-tag-button");
		sleepShort();

		selenium.click("//div[1]/div[1]/div/div/div[2]");
		sleepShort();
		selenium.click("gwt-debug-import-tag-button");
		sleepShort();

	}

}
