package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeLocalSuite extends WebDriverSuite {
	public static TestSuite suite() {
		WebDriverSuite suite = new ChromeLocalSuite();
		suite.addTest(new SimpleITest(suite.factory.chromeProvider(),suite.local));
		return suite;
	}
}
