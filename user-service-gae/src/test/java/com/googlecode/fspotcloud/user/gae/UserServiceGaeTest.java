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

import com.google.appengine.api.users.User;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;

/**
 * DOCUMENT ME!
 *
 * @author steven
*/
@RunWith(JukitoRunner.class)
public class UserServiceGaeTest {
    @Inject
    UserServiceGae userService;

    @Before
    public void setUp(HttpSession session, HttpServletRequest request) {
        when(request.getScheme()).thenReturn("http");
        when(request.getContextPath()).thenReturn("/context");
        when(request.getServerPort()).thenReturn(8080);
        when(request.getServerName()).thenReturn("localhost");
    }

    @Test
    public void isUserLoggedIn(
        com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.TRUE);
        Assert.assertTrue(userService.isUserLoggedIn());
    }

    @Test
    public void createLoginURL(
        com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLoginURL("http://localhost:8080/context/later"))
            .thenReturn("url");
        Assert.assertEquals("url", userService.createLoginURL("later"));
    }

    @Test
    public void createLogoutURL(
        com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLogoutURL("http://localhost:8080/context/later"))
            .thenReturn("url");
        Assert.assertEquals("url", userService.createLogoutURL("later"));
    }

    @Test
    public void isUserAdmin(com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.TRUE);
        when(delegate.isUserAdmin()).thenReturn(Boolean.TRUE);
        Assert.assertTrue(userService.isUserAdmin());
    }

    @Test
    public void email(com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.TRUE);

        User foo = new User("foo@bar.com", "");
        when(delegate.getCurrentUser()).thenReturn(foo);

        Assert.assertEquals(userService.getEmail(), "foo@bar.com");
    }

    @Test
    public void emailReturnsNull(
        com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.FALSE);
        Assert.assertNull(userService.getEmail());
    }
}
