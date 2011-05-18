package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeRemoteSuite extends WebDriverSuite {
	public static TestSuite suite() {
		WebDriverSuite suite = new ChromeRemoteSuite();
		suite.addTest(new SimpleITest(suite.factory.chromeProvider(),suite.remote));
		return suite;
	}
}
