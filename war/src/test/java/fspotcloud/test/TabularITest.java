package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class TabularITest extends SeleniumITest {

	public TabularITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}

	public TabularITest() {
	}

	@Override
	protected void runTest() throws Throwable {
		testTabular();
	}


	public void testTabular() throws Exception {
		selenium.open("/");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-image-view-4x0");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-image-view-0x0");
		selenium.waitForPageToLoad("30000");
		
		selenium.open("/#BasePlace:1:12:2:2:false");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-pager-previous-button");
		selenium.waitForPageToLoad("30000");
		assertEquals("/image?id=6&thumb", selenium.getAttribute("//*[@id=\"gwt-debug-image-view-0x1\"]@src"));
	}
}
