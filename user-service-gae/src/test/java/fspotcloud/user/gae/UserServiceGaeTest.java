/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.user.gae;

import com.google.appengine.api.users.User;
import javax.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Assert;
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

    @Test
    public void isUserLoggedIn(com.google.appengine.api.users.UserService delegate) {
        when(delegate.isUserLoggedIn()).thenReturn(Boolean.TRUE);
        Assert.assertTrue(userService.isUserLoggedIn());
    }

    @Test
    public void createLoginURL(com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLoginURL("later")).thenReturn("url");
        Assert.assertEquals(userService.createLoginURL("later"), "url");
    }

    @Test
    public void createLogoutURL(com.google.appengine.api.users.UserService delegate) {
        when(delegate.createLogoutURL("later")).thenReturn("url");
        Assert.assertEquals(userService.createLogoutURL("later"), "url");
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
