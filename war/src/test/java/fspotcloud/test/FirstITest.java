package fspotcloud.test;
import junit.framework.TestCase;

import org.openqa.selenium.chrome.ChromeDriver;

public class FirstITest extends TestCase {
	
	ChromeDriver driver;
	
	public void setUp() throws Exception {
		driver = new ChromeDriver();
	}
	@Override
	protected void tearDown() throws Exception {
		driver.quit();
		super.tearDown();
	}
	public void testFirstI() throws Exception {
		driver.get("http://localhost:8080/");
	}
}
