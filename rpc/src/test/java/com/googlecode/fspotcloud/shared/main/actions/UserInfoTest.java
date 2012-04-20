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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.shared.main.actions;

import com.googlecode.fspotcloud.shared.main.actions.UserInfo;

import junit.framework.TestCase;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
public class UserInfoTest extends TestCase {
    /** Test of isLoggedIn method, of class UserInfo. */
    UserInfo instance = new UserInfo(
            "foo@bar.com", true, true, "index.jsp?dest=",
            "index.jsp?action=logout&dest=");

    public UserInfoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");

        boolean expResult = true;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
    }


    /**
     * Test of createLogoutUrl method, of class UserInfo.
     */
    public void testCreateLogoutUrl() {
        System.out.println("createLogoutUrl");

        String dest = "dest";
        String expResult = "index.jsp?action=logout&dest=";
        String result = instance.createLogoutUrl();
        assertEquals(expResult, result);
    }


    /**
     * Test of createLoginUrl method, of class UserInfo.
     */
    public void testCreateLoginUrl() {
        System.out.println("createLoginUrl");

        String dest = "dest";
        String expResult = "index.jsp?dest=";
        String result = instance.createLoginUrl();
        assertEquals(expResult, result);
    }
}
