package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeRemoteSuite extends TestSuite {
	public static TestSuite suite() {
		TestSuite suite = new ChromeRemoteSuite();
		String remote =  "http://jfspotcloud.appspot.com";
		suite.addTest(new SimpleITest(new ChromeDriver(),remote));
	
		return suite;
	}
}
