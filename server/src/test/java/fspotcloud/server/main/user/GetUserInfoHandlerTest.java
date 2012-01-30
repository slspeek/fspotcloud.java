/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.server.main.user;

import com.google.inject.Inject;
import fspotcloud.shared.main.actions.GetUserInfo;
import fspotcloud.shared.main.actions.UserInfo;
import fspotcloud.user.UserService;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
        
public class GetUserInfoHandlerTest {
   GetUserInfoHandler handler;
   
   @Mock UserService service;
   
   @BeforeMethod public void mockit() {
       MockitoAnnotations.initMocks(this);
       handler = new GetUserInfoHandler(service);
   }
   
   @Test public void execute() throws DispatchException {
       when(service.isUserLoggedIn()).thenReturn(Boolean.TRUE);
       when(service.isUserAdmin()).thenReturn(Boolean.FALSE);
       when(service.createLoginURL("later")).thenReturn("login_url");
       when(service.createLogoutURL("later")).thenReturn("logout_url");
       when(service.getEmail()).thenReturn("foo@bar.com");
       
       UserInfo info = handler.execute(new GetUserInfo(), null);
       Assert.assertEquals("foo@bar.com", info.getEmail());
       Assert.assertTrue(info.isLoggedIn());
   }
   
    @Test public void execute2() throws DispatchException {
       when(service.isUserLoggedIn()).thenReturn(Boolean.FALSE);
       when(service.isUserAdmin()).thenReturn(Boolean.FALSE);
       when(service.createLoginURL("later")).thenReturn("login_url");
       when(service.createLogoutURL("later")).thenReturn("logout_url");
       when(service.getEmail()).thenReturn(null);
       UserInfo info = handler.execute(new GetUserInfo(), null);
       Assert.assertFalse(info.isLoggedIn());
   }
   
}
