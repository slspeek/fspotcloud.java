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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;


public class SimpleSignUpITest {
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    SignUpPage page;

    @Test
    public void signUp() throws Exception {
        page.open();
        page.fillForm("rms@fsf.org", "ihp", "rms");
        page.signUp();
        sleepShort(2);
        page.verifySuccess();
        page.open();
        page.fillForm("rms@fsf.org", "ihp", "rms");
        page.signUp();
        page.verifyFailure();
        sleepShort(2);
        page.open();
        page.fillForm("moog@bb.org", "nsa", "moog");
        page.signUp();
        sleepShort(2);
        page.verifySuccess();
    }
}