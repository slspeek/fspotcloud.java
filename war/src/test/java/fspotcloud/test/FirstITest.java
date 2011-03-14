package fspotcloud.test;
import junit.framework.TestCase;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstITest extends TestCase {
	
	FirefoxDriver driver;
	
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}
	public void testFirstI() throws Exception {
		driver.get("http://jfspotcloud.appspot.com/#TagViewingPlace:3:5");
	}
}
