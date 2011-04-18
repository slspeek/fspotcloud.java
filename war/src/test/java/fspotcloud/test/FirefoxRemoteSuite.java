package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxRemoteSuite extends WebDriverSuite {
	public static TestSuite suite() {
		WebDriverSuite suite = new FirefoxRemoteSuite();
		suite.addTest(new SimpleITest(suite.factory.firefoxProvider(), suite.remote));
		suite.addTest(new NavigationITest(suite.factory.firefoxProvider(), suite.remote));
		suite.addTest(new CloudcoverITest(suite.factory.firefoxProvider(), suite.remote));
		return suite;
	}
}
