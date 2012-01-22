package fspotcloud.test;

import static fspotcloud.test.Sleep.sleepShort;
import static junit.framework.Assert.fail;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;

public class IntersectionDeleteITest {
	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(
			FSCGuiceberryEnv.class);

	@Inject
	Selenium selenium;

	@Inject
	LoginBot login;
	
	@Test
	public void testImport() throws Exception {
		login.login();
		pressButtonForComputers();
		pressButtonForPC();
		sleepShort(15);
		pressButtonForComputers();
		sleepShort(3);
		selenium.open("/#BasePlace:4:11:1:1");
		selenium.waitForPageToLoad("30000");
		sleepShort(3);
		String page = selenium.getText("gwt-debug-paging-label");
		if (!page.contains("1 of 14")) {
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
		selenium.click("gwt-debug-import-tag-button");
	}
}
