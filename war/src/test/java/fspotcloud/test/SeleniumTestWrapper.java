package fspotcloud.test;

import javax.inject.Inject;

import com.google.common.testing.TearDown;
import com.google.common.testing.TearDownAccepter;
import com.google.guiceberry.TestWrapper;
import com.thoughtworks.selenium.Selenium;

public class SeleniumTestWrapper implements TestWrapper {

	@Inject
	Selenium selenium;
	@Inject TearDownAccepter tearDownAccepter;
	
	public void toRunBeforeTest() {
		tearDownAccepter.addTearDown(new TearDown() {

			public void tearDown() throws Exception {
				System.out.println("selenium handle " +selenium);
				selenium.close();
				System.out.println("After closing selenium ");
			}
		});
		System.out.println("Ran testwrapper init");
	}
}
