package fspotcloud.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Provider;

public class SimpleITest extends WebDriverITest {

	public SimpleITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}

	public SimpleITest() {
	}

	@Override
	public void runTest() throws Exception {
		testBaseURL();
	}

	public void testBaseURL() throws Exception {
		driver.get(baseURL+"/#TagViewingPlace:1:4");
		Thread.sleep(900);
		WebElement element = driver.findElement(By.id("gwt-debug-pager-first-button"));
		element.click();
		Thread.sleep(900);
		WebElement image = driver.findElement(By.id("gwt-debug-image-view"));
		assertEquals("/image?id=7", image.getAttribute("src"));
		element = driver.findElement(By.id("gwt-debug-pager-next-button"));
		element.click();
		image = driver.findElement(By.id("gwt-debug-image-view"));
		assertEquals("/image?id=5", image.getAttribute("src"));
		Thread.sleep(900);
		element = driver.findElement(By.id("gwt-debug-pager-next-button"));
		element.click();
		image = driver.findElement(By.id("gwt-debug-image-view"));
		assertEquals("/image?id=6", image.getAttribute("src"));
		Thread.sleep(900);
		element = driver.findElement(By.id("gwt-debug-pager-next-button"));
		element.click();
		image = driver.findElement(By.id("gwt-debug-image-view"));
		assertEquals("/image?id=4", image.getAttribute("src"));
		Thread.sleep(900);
	}
	
	
}
