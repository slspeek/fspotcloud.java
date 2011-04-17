package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxRemoteSuite extends TestSuite {
	public static TestSuite suite() {
		TestSuite suite = new FirefoxRemoteSuite();
		String remote = "http://jfspotcloud.appspot.com";
		suite.addTest(new SimpleITest(new FirefoxDriver(), remote));
		suite.addTest(new NavigationITest(new FirefoxDriver(), remote));
		return suite;
	}
}
