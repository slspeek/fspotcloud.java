package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class ApplicationActionsITest extends SeleniumITestCase {

	public ApplicationActionsITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}

	public ApplicationActionsITest() {
	}

	@Override
	protected void runTest() throws Throwable {
		testTabular();
	}


	public void testTabular() throws Exception {
		selenium.open("/");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-about");
		sleepShort();
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-about");
		selenium.waitForPageToLoad("30000");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-help");
		sleepShort();
		selenium.click("//div[3]/div");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-fullscreen");
		selenium.waitForPageToLoad("30000");
	}
}
