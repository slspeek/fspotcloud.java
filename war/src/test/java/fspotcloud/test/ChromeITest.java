package fspotcloud.test;

import junit.framework.TestCase;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeITest extends TestCase {

	ChromeDriver driver;

	public void setUp() throws Exception {
		driver = new ChromeDriver();
	}

	public void testFirstI() throws Exception {
		driver.get("http://jfspotcloud.appspot.com/#TagViewingPlace:1:23");
	}
}
