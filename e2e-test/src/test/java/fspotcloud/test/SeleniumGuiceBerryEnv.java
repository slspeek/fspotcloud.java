package fspotcloud.test;

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

public class SeleniumGuiceBerryEnv extends GuiceBerryModule {

    @Provides
    @TestScoped
    Selenium getSelenium(@Named("baseUrl") String baseUrl) {
        WebDriver driver;
        String userChoice = System.getProperty("fspotcloud.test.webdriver");
        if (userChoice != null) {
            driver = new FirefoxDriver();
        } else {
            driver = new HtmlUnitDriver();
            ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        }
        return new WebDriverBackedSelenium(driver, baseUrl);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(SeleniumTestWrapper.class);
    }
}
