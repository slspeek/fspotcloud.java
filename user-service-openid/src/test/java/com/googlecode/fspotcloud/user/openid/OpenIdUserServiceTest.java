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
package com.googlecode.fspotcloud.user.openid;

import com.google.inject.Inject;
import com.google.inject.Scopes;
import com.google.inject.internal.ImmutableList;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;

import com.googlecode.fspotcloud.user.openid.AdminEmail;
import com.googlecode.fspotcloud.user.openid.OpenIdUserService;

import net.jcip.annotations.Immutable;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
@RunWith(JukitoRunner.class)
public class OpenIdUserServiceTest {
    @Inject
    OpenIdUserService instance;

    @Before
    public void setUp(HttpSession session, HttpServletRequest request) {
        List<String> emails = ImmutableList.of("foo@bar.com");
        when(session.getAttribute("email")).thenReturn(emails);
        when(request.getScheme()).thenReturn("http");
        when(request.getContextPath()).thenReturn("/context");
        when(request.getServerPort()).thenReturn(8080);
        when(request.getServerName()).thenReturn("localhost");
    }


    @After
    public void tearDown() {
    }


    /**
     * Test of createLoginURL method, of class OpenIdUserService.
     */
    @Test
    public void testCreateLoginURL() {
        String destinationURL = "dest";
        String expResult = "index.jsp?dest=http://localhost:8080/context/dest";
        String result = instance.createLoginURL(destinationURL);
        assertEquals(expResult, result);
    }


    /**
     * Test of createLogoutURL method, of class OpenIdUserService.
     */
    @Test
    public void testCreateLogoutURL() {
        String destinationURL = "dest";
        String expResult = "index.jsp?logout=true&dest=http://localhost:8080/context/dest";
        String result = instance.createLogoutURL(destinationURL);
        assertEquals(expResult, result);
    }


    /**
     * Test of getEmail method, of class OpenIdUserService.
     *
     * @param session DOCUMENT ME!
     */
    @Test
    public void testGetEmailNull(HttpSession session) {
        when(session.getAttribute("email")).thenReturn(null);

        String expResult = null;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetEmailFoo() {
        String expResult = "foo@bar.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }


    /**
     * Test of isUserLoggedIn method, of class OpenIdUserService.
     */
    @Test
    public void testIsUserLoggedIn() {
        boolean expResult = true;
        boolean result = instance.isUserLoggedIn();
        assertEquals(expResult, result);
    }


    /**
     * Test of isUserAdmin method, of class OpenIdUserService.
     */
    @Test
    public void testIsUserAdmin() {
        System.out.println("isUserAdmin");

        boolean expResult = false;
        boolean result = instance.isUserAdmin();
        assertEquals(expResult, result);
    }


    /**
     * Test of isUserAdmin method, of class OpenIdUserService.
     *
     * @param session DOCUMENT ME!
     */
    @Test
    public void testIsUserIsAdmin(HttpSession session) {
        List<String> emails = ImmutableList.of("slspeek@gmail.com");
        when(session.getAttribute("email")).thenReturn(emails);

        boolean expResult = true;
        boolean result = instance.isUserAdmin();
        assertEquals(expResult, result);
    }

    public static class Module extends JukitoModule {
        protected void configureTest() {
            //install(new OpenIdUserModule());
            bind(String.class).annotatedWith(AdminEmail.class)
                .toInstance("slspeek@gmail.com");
        }
    }
}
