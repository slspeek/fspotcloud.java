package com.googlecode.fspotcloud.test;

import static com.googlecode.fspotcloud.test.Sleep.sleepShort;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;

public class DeleteITest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(
            SeleniumGuiceBerryEnv.class);
    @Inject
    Selenium selenium;
    @Inject
    ILogin login;

    @Test
    public void testClear() throws Exception {
        login.login();
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-delete-all-tags-button");
        sleepShort();

        selenium.open("/Dashboard.html");
        selenium.waitForPageToLoad("30000");
        sleepShort();
        sleepShort();
    }
}
