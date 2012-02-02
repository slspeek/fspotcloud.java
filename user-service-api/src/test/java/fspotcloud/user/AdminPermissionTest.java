/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.user;

import javax.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class AdminPermissionTest {
    

    @Inject AdminPermission instance;
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of chechAdminPermission method, of class AdminPermission.
     */
    @Test
    public void testChechAdminPermission(UserService mock) {
        when(mock.isUserAdmin()).thenReturn(Boolean.TRUE);
        instance.chechAdminPermission();
    }
    
    @Test(expected=SecurityException.class)
    public void testChechAdminPermissionNotInRole(UserService mock) {
        when(mock.isUserAdmin()).thenReturn(Boolean.FALSE);
        instance.chechAdminPermission();
    }
    
    
}
