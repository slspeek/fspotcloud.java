package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxLocalSuite extends TestSuite {
	public static TestSuite suite() {
		TestSuite suite = new FirefoxLocalSuite();
		String local =  "http://localhost:8080";
		//suite.addTest(new SimpleITest(new FirefoxDriver(),local));
		suite.addTest(new ImportITest(new FirefoxDriver(),local));
		suite.addTest(new SimpleITest(new FirefoxDriver(),local));
		suite.addTest(new CloudcoverITest(new FirefoxDriver(), local));
		return suite;
	}
}
