package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class FirefoxLocalSuite extends WebDriverSuite {

	public static TestSuite suite() {
		WebDriverSuite suite = new FirefoxLocalSuite();
		String localUrl = WebDriverSuite.local;
		Provider<WebDriver> provider = WebDriverSuite.factory.firefoxProvider();
		suite.addTest(new DashboardITest(provider, localUrl));
		suite.addTest(new ApplicationActionsITest(provider, localUrl));
		suite.addTest(new TabularITest(provider, localUrl));
		if (System.getProperty("nodelete", "false").equals("false")) {
			suite.addTest(new DeleteITest(provider, localUrl));
		}
		suite.addTest(new CloudcoverITest(provider, localUrl));
		return suite;
	}
}
