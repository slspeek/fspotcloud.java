package fspotcloud.test;

import javax.inject.Inject;

import com.thoughtworks.selenium.Selenium;

public class NoLoginBot implements ILogin{
	
	@Inject 
	Selenium selenium;
	
	public void login() throws Exception {
		selenium.open("/Dashboard.html");
		selenium.waitForPageToLoad("30000");
//		selenium.click("isAdmin");
//		selenium.click("action");
//		selenium.waitForPageToLoad("30000");
	}

}
