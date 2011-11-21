package fspotcloud.taskqueuedispatch.integration.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class TaskQueueDispatchITest extends SeleniumITestCase {

	public TaskQueueDispatchITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}

	public TaskQueueDispatchITest() {
	}

	public void runTest() throws Exception {
		testSimpleAction();
	}

	public void testSimpleAction() throws Exception {
		selenium.open("/test?name=David");
		selenium.waitForPageToLoad("30000");
		sleepShort(4);
		selenium.open("/test?name=Joshua");
		assertTrue(selenium.isTextPresent("Hello to you, David"));
		selenium.waitForPageToLoad("30000");
		sleepShort(4);
		selenium.open("/test?second=gNu");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Hello to you, Joshua"));
		sleepShort(4);
		selenium.open("/test");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("GNU"));
	}
}
