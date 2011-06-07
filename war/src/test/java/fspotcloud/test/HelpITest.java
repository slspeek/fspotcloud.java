package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class HelpITest extends SeleniumITest {

	public HelpITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}

	public HelpITest() {
	}

	public void testOpenHelp() throws Exception {
		selenium.open("/#TagViewingPlace:11:10");
		selenium.waitForPageToLoad("5000");
		selenium.keyPress("gwt-debug-pager-last-button","h");
		selenium.waitForPageToLoad("5000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Start slideshow".equals(selenium.getText("//tr[7]/td[3]/span"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}


	}
}
