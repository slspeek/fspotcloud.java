package com.googlecode.fspotcloud.test;

import com.thoughtworks.selenium.Selenium;
import javax.inject.Inject;

public class NoLoginBot implements ILogin {

    @Inject
    Selenium selenium;

    @Override
    public void login() throws Exception {
        selenium.open("/Dashboard.html");
        selenium.waitForPageToLoad("30000");
    }
}
