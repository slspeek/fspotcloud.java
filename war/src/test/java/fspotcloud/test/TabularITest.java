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
		selenium.open("/#TagViewingPlace:1:7:1:1");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-image-view-0");
		selenium.waitForPageToLoad("30000");
		assertEquals("/image?id=4&thumb", selenium.getAttribute("//*[@id=\"gwt-debug-image-view-4\"]@src"));
		selenium.click("gwt-debug-image-view-4");
		selenium.waitForPageToLoad("30000");
		selenium.click("gwt-debug-pager-next-button");
		selenium.waitForPageToLoad("30000");
		assertEquals("/image?id=12", selenium.getAttribute("//*[@id=\"gwt-debug-image-view-74\"]@src"));
		selenium.click("gwt-debug-image-view-74");
	}
}
