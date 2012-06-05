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
package com.googlecode.fspotcloud.user.gae;

import com.googlecode.fspotcloud.user.ISessionEmail;
import com.googlecode.fspotcloud.user.LoginMetaData;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

/**
 * DOCUMENT ME!
 *
 * @author steven
*/
@RunWith(JukitoRunner.class)
public class UserServiceGaeTest {
    public static final String FOO_FSF_ORG = "foo@fsf.org";
    @Inject
    UserServiceGae userService;
    @Inject
    LoginMetaData metaData;

    @Before
    public void setUp(HttpSession session, HttpServletRequest request) {
        metaData.setEmail(FOO_FSF_ORG);
        when(request.getScheme()).thenReturn("http");
        when(request.getContextPath()).thenReturn("/context");
        when(request.getServerPort()).thenReturn(8080);
        when(request.getServerName()).thenReturn("localhost");
    }

    @Test
    public void isUserLoggedInRegular() {
        Assert.assertTrue(userService.isUserLoggedIn());
    }

    @Test
    public void emailInRegular() {
        assertEquals(FOO_FSF_ORG, userService.getEmail());
    }

    @Test
    public void createLoginURL(
        com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLoginURL("http://localhost:8080/context/post-login"))
            .thenReturn("url");
        Assert.assertEquals("url", userService.getThirdPartyLoginURL());
    }

    @Test
    public void createLogoutURL(
        com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLogoutURL(
                "http://localhost:8080/context/FSpotCloud.html"))
            .thenReturn("url");
        Assert.assertEquals("url", userService.getThirdPartyLogoutURL());
    }

    @Test
    public void isUserAdmin(com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.TRUE);
        when(delegate.isUserAdmin()).thenReturn(Boolean.TRUE);
        Assert.assertTrue(userService.isUserAdmin());
    }

    @Test
    public void emailReturnsNull(
        com.google.appengine.api.users.UserService delegate) {
        metaData.setEmail(null);
        Assert.assertNull(userService.getEmail());
    }
}
