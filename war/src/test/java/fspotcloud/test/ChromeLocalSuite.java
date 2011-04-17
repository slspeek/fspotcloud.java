package fspotcloud.test;

import junit.framework.TestSuite;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeLocalSuite extends TestSuite {
	public static TestSuite suite() {
		TestSuite suite = new ChromeLocalSuite();
		String local =  "http://localhost:8080";
		suite.addTest(new SimpleITest(new ChromeDriver(),local));
		return suite;
	}
}
