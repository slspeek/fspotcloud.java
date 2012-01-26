package fspotcloud.test;

import static fspotcloud.test.Sleep.sleepShort;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;

public class ApplicationActionsITest {

	@Rule
	public GuiceBerryRule guiceBerry = new GuiceBerryRule(FSCGuiceberryEnv.class);
	
	@Inject
	Selenium selenium;
	
	@Test
	public void showPopups() throws Exception {
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
	}
}
