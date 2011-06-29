package fspotcloud.test;

import junit.framework.TestSuite;

public class ChromeLocalOuite extends WebDriverSuite {
	public static TestSuite suite() {
		WebDriverSuite suite = new ChromeLocalOuite();
		suite.addTest(new TabularITest(suite.factory.chromeProvider(),suite.local));
		return suite;
	}
}
