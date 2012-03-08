/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.user.openid;

import com.googlecode.fspotcloud.user.openid.OpenIdUserService;
import com.googlecode.fspotcloud.user.openid.AdminEmail;
import com.google.inject.Inject;
import com.google.inject.Scopes;
import com.google.inject.internal.ImmutableList;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.jcip.annotations.Immutable;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;

/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class OpenIdUserServiceTest {

    public static class Module extends JukitoModule {

        protected void configureTest() {
            //install(new OpenIdUserModule());
            bind(String.class).annotatedWith(AdminEmail.class).toInstance("slspeek@gmail.com");
           
        }
        
        
    }
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
     */
    @Test
    public void testIsUserIsAdmin(HttpSession session) {
        List<String> emails = ImmutableList.of("slspeek@gmail.com");
        when(session.getAttribute("email")).thenReturn(emails);
        boolean expResult = true;
        boolean result = instance.isUserAdmin();
        assertEquals(expResult, result);
    }
}
