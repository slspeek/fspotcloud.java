package com.googlecode.fspotcloud.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestScoped;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;
import com.thoughtworks.selenium.Selenium;
import javax.inject.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class EmptyGuiceBerryEnv extends GuiceBerryModule {

        @Override
    protected void configure() {
        super.configure();

    }
}
