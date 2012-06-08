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

import com.google.common.testing.TearDown;
import com.google.common.testing.TearDownAccepter;
import com.google.guiceberry.TestWrapper;
import com.thoughtworks.selenium.Selenium;

import javax.inject.Inject;


public class SeleniumTestWrapper implements TestWrapper {
    @Inject
    Selenium selenium;
    @Inject
    TearDownAccepter tearDownAccepter;

    public void toRunBeforeTest() {
        tearDownAccepter.addTearDown(new TearDown() {
                public void tearDown() throws Exception {
                    selenium.close();
                }
            });
    }
}
