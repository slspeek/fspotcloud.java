package fspotcloud.test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;
import static fspotcloud.test.Sleep.sleepShort;
import javax.inject.Inject;
import org.junit.Rule;
import org.junit.Test;

public class DashboardITest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(FSCGuiceberryEnv.class);
    @Inject
    Selenium selenium;
    @Inject
    LoginBot login;

    @Test
    public void testImport() throws Exception {
        login.login();
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-update-button");
        sleepShort(9);
        selenium.open("/Dashboard.html");
        selenium.waitForPageToLoad("30000");
        sleepShort();
        //import Furniture		
        selenium.click("gwt-debug-import-tag-button");
        selenium.waitForPageToLoad("30000");
    }
}
