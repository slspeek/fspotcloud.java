package fspotcloud.test;

import junit.framework.TestSuite;

public class WebDriverSuite extends TestSuite {
	public static WebDriverProviderFactory factory = new WebDriverProviderFactory();
	public static String local = System.getProperty("local", "http://localhost:8080");
	public static String remote = System.getProperty("remote", "http://jfspotcloud.appspot.com");
}
