package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class NavigationITest extends SeleniumITest {

	public NavigationITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}
	
	public NavigationITest() {
		super();
	}
	
	@Override
	protected void runTest() throws Throwable {
		testSomeNavigation();
	}

	public void testSomeNavigation() throws Exception {
		selenium.open("/#TagViewingPlace:10:26");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		selenium.click("//td[3]/div");
		sleepShort();
		selenium.click("//td[3]/div");
		sleepShort();
		selenium.click("//div[3]/div[1]/div/div[2]");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		selenium.click("//td[3]/div");
		selenium.waitForPageToLoad("30000");
		sleepShort();
		selenium.click("//td[5]/div");
		sleepShort();
		assertTrue(selenium.isTextPresent("5.333 seconds."));
		selenium.open("/#ImageViewingPlace:12:25");
		sleepShort();
		selenium.click("gwt-debug-pager-last-button");
		sleepShort();
		selenium.click("gwt-debug-pager-previous-button");
		sleepShort();
		selenium.click("gwt-debug-pager-previous-button");
	}
}
