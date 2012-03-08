/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.user.gae;

import com.googlecode.fspotcloud.user.gae.UserServiceGae;
import com.google.appengine.api.users.User;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

/**
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
    public void isUserLoggedIn(com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.TRUE);
        Assert.assertTrue(userService.isUserLoggedIn());
    }

    @Test
    public void createLoginURL(com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLoginURL("http://localhost:8080/context/later")).thenReturn("url");
        Assert.assertEquals("url", userService.createLoginURL("later"));
    }

    @Test
    public void createLogoutURL(com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLogoutURL("http://localhost:8080/context/later")).thenReturn("url");
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
    public void emailReturnsNull(com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.FALSE);
        Assert.assertNull(userService.getEmail());
    }
}
