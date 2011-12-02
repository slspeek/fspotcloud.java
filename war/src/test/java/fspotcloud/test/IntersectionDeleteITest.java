package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class IntersectionDeleteITest extends SeleniumITestCase {

	public IntersectionDeleteITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
		setName("IntersectionDeleteITest");
	}

	public IntersectionDeleteITest() {
		setName("IntersectionDeleteITest");
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
		pressButtonForComputers();
		pressButtonForPC();
		sleepShort(15);
		pressButtonForComputers();
		sleepShort(3);
		selenium.open("/#BasePlace:4:11:1:1");
		selenium.waitForPageToLoad("30000");
		sleepShort(3);
		String page = selenium.getText("gwt-debug-paging-label");
		if (! page.contains("1 of 14")) {
			fail();
		}
		pressButtonForPC();
	}

	private void pressButtonForComputers() throws InterruptedException {
		selenium.open("/Dashboard.html#TagPlace:2");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-import-tag-button");
	}
	private void pressButtonForPC() throws InterruptedException {
		selenium.open("/Dashboard.html#TagPlace:4");
		selenium.waitForPageToLoad("30000");
		//import Furniture		
		selenium.click("gwt-debug-import-tag-button");
	}
}
