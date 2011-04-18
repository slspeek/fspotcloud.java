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
		selenium.open("/");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[3]/div");
		Thread.sleep(900);
		selenium.click("//td[3]/div");
		Thread.sleep(900);
		selenium.click("//div[1]/div[2]/div[1]/div/div[2]");
		selenium.waitForPageToLoad("30000");

		selenium.click("//td[3]/div");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(900);
		selenium.click("//td[5]/div");
		Thread.sleep(100);
		assertTrue(selenium.isTextPresent("4 seconds."));
		selenium.open("/#ImageViewingPlace:1:27");
		selenium.waitForPageToLoad("30000");
		selenium.click("//td[1]/div/table/tbody/tr/td[1]/div");
		selenium.waitForPageToLoad("30000");
	}
}
