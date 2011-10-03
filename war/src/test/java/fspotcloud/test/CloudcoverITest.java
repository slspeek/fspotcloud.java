package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class CloudcoverITest extends SeleniumITestCase {

	
	public CloudcoverITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}
	
	public CloudcoverITest() {
	}

	@Override
	protected void runTest() throws Throwable {
		testRunModel();
		testRunServer();
	}

	public void testRunServer() throws InterruptedException {
		selenium.open("/cloudcover.html");
		selenium.waitForPageToLoad("30000");
		selenium.select("//div[@id='run-status']/div/table[1]/tbody/tr/td/table/tbody/tr/td[2]/select", "label=Model");
		selenium.click("//button[@type='button']");
		selenium.waitForPageToLoad("30000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("FINISHED")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
	}

	public void testRunModel() throws Exception {
		selenium.open("/cloudcover.html");
		selenium.waitForPageToLoad("30000");
		selenium.select("//div[@id='run-status']/div/table[1]/tbody/tr/td/table/tbody/tr/td[2]/select", "label=Server");
		selenium.click("//button[@type='button']");
		selenium.waitForPageToLoad("30000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("FINISHED")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
	}
}
