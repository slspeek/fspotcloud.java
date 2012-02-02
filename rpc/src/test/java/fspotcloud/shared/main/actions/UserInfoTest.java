/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.shared.main.actions;

import junit.framework.TestCase;

/**
 *
 * @author steven
 */
public class UserInfoTest extends TestCase {
    
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
    /**
     * Test of isLoggedIn method, of class UserInfo.
     */
        UserInfo instance = new UserInfo("foo@bar.com", true, true, "index.jsp?dest=", "index.jsp?action=logout&dest=");
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
