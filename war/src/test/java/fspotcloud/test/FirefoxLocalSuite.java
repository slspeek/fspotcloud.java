package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class FirefoxLocalSuite extends WebDriverSuite {

	public static TestSuite suite() {
		WebDriverSuite suite = new FirefoxLocalSuite();
		String local = WebDriverSuite.local;
		Provider<WebDriver> provider = WebDriverSuite.factory.firefoxProvider();
		//suite.addTest(new ImportITest(provider, local));
		suite.addTest(new ImportITest(provider, local));
		suite.addTest(new SimpleITest(provider, local));
		suite.addTest(new NavigationITest(provider, local));
		suite.addTest(new CloudcoverITest(provider, local));
		suite.addTest(new MapperITest(provider, local));
		return suite;
	}
}
