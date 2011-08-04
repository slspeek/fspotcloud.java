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
		selenium.waitForPageToLoad("20000");
		selenium.click("gwt-debug-delete-all-tags-button");
		sleepShort();
		selenium.click("gwt-debug-delete-all-photos-button");
		sleepShort();
		selenium.click("gwt-debug-reset-meta-data-button");
		sleepShort();
		selenium.click("gwt-debug-count-photos-button");
		sleepShort(4);
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("5000");
		sleepShort();
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("5000");
		assertEquals("0", selenium.getText("//td[2]/div/table/tbody[2]/tr[1]/td[2]/div"));
		sleepShort();
	}
}
