package fspotcloud.test;

import javax.inject.Inject;

import com.thoughtworks.selenium.Selenium;

public class LoginBot {

    @Inject
    Selenium selenium;

    public void login() throws Exception {
        selenium.open("/");
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-login");
        selenium.waitForPageToLoad("30000");
        selenium.click("isAdmin");
        selenium.click("action");
        selenium.waitForPageToLoad("30000");
        selenium.open("/Dashboard.html");
    }
}
