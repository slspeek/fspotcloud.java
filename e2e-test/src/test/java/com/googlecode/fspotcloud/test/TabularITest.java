package com.googlecode.fspotcloud.test;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;

public class TabularITest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(
            SeleniumGuiceBerryEnv.class);
    @Inject
    Selenium selenium;

    @Test
    public void testTabular() throws Exception {
        selenium.open("/");
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-image-view-4x0");
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-image-view-0x0");
        selenium.waitForPageToLoad("30000");

        selenium.open("/#BasePlace:1:12:2:2:false:true");
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-back");
        selenium.waitForPageToLoad("30000");
        Assert.assertEquals("image?id=6&thumb", selenium.getAttribute("//*[@id=\"gwt-debug-image-view-0x1\"]@src"));
    }
}
