package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class FirefoxLocalSuite extends WebDriverSuite {

	public static TestSuite suite() {
		WebDriverSuite suite = new FirefoxLocalSuite();
		String localUrl = WebDriverSuite.local;
		Provider<WebDriver> provider = WebDriverSuite.factory.firefoxProvider();
		//suite.addTest(new ImportITest(provider, local));
		suite.addTest(new ImportITest(provider, localUrl));
		suite.addTest(new SimpleITest(provider, localUrl));
		suite.addTest(new NavigationITest(provider, localUrl));
		suite.addTest(new CloudcoverITest(provider, localUrl));
		suite.addTest(new MapperITest(provider, localUrl));
		return suite;
	}
}
