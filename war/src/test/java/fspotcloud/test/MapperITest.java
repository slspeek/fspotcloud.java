package fspotcloud.test;

import org.openqa.selenium.WebDriver;

import com.google.inject.Provider;

public class MapperITest extends SeleniumITest {

	public MapperITest(Provider<WebDriver> provider, String baseURL) {
		super(provider, baseURL);
	}

	public MapperITest() {
	}

	public void runTest() throws Exception {
		testPhotoCountMapper();
	}

	public void testPhotoCountMapper() throws Exception {
		selenium.open("/mapreduce/status");
		selenium.waitForPageToLoad("30000");
		selenium.click("isAdmin");
		selenium.click("action");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(600);
		selenium.select("//div[@id='launch-control']/select",
				"label=Photo Counter Mapper");
		selenium.click("//div[@id='launch-container']/form[2]/input[2]");
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				selenium.open("/mapreduce/status");

				if (selenium.isTextPresent("Cleanup"))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		selenium.click("link=Detail");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("28"));
		// assertTrue(selenium.isTextPresent("18"));
	}
}
