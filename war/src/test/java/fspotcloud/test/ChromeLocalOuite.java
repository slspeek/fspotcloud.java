package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeLocalOuite extends WebDriverSuite {
	public static TestSuite suite() {
		WebDriverSuite suite = new ChromeLocalOuite();
		suite.addTest(new SimpleITest(suite.factory.chromeProvider(),suite.local));
		return suite;
	}
}
