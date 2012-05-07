/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import static com.googlecode.fspotcloud.test.Sleep.sleepShort;
import com.thoughtworks.selenium.Selenium;
import javax.inject.Inject;
import static junit.framework.Assert.fail;
import org.junit.Rule;
import org.junit.Test;


public class IntersectionDeleteITest {
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    Selenium selenium;
    @Inject
    ILogin login;

    @Test
    public void testImport() throws Exception {
        login.login();
        pressButtonForComputers();
        pressButtonForPC();
        sleepShort(15);
        pressButtonForComputers();
        sleepShort(3);
        selenium.open("/#BasePlace:4:11:1:1");
        selenium.waitForPageToLoad("30000");
        sleepShort(3);

        String page = selenium.getText("gwt-debug-paging-label");

        if (!page.contains("1 of 14")) {
            fail();
        }

        pressButtonForPC();
    }

    private void pressButtonForComputers() throws InterruptedException {
        selenium.open("/Dashboard.html#TagPlace:2");
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-import-tag-button");
    }

    private void pressButtonForPC() throws InterruptedException {
        selenium.open("/Dashboard.html#TagPlace:4");
        selenium.waitForPageToLoad("30000");
        selenium.click("gwt-debug-import-tag-button");
    }
}
