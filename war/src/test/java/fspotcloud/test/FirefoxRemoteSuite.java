package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.Provider;

public class FirefoxRemoteSuite extends WebDriverSuite {
	public static TestSuite suite() {
		WebDriverSuite suite = new FirefoxRemoteSuite();
		String remoteUrl = WebDriverSuite.remote;
		Provider<WebDriver> provider = WebDriverSuite.factory.firefoxProvider();
		suite.addTest(new NavigationITest(provider, remoteUrl));
		suite.addTest(new NavigationITest(provider, remoteUrl));
		suite.addTest(new CloudcoverITest(provider, remoteUrl));
		suite.addTest(new MapperITest(provider, remoteUrl));
		return suite;
	}
}
