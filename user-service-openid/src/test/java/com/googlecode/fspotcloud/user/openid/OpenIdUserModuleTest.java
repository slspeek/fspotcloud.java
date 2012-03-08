/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.user.openid;

import com.googlecode.fspotcloud.user.openid.OpenIdUserModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author steven
 */
public class OpenIdUserModuleTest {
    
    public OpenIdUserModuleTest() {
    }

     /**
     * Test of configure method, of class OpenIdUserModule.
     */
    @Test
    public void testConfigure() {
        Injector injector = Guice.createInjector(new OpenIdUserModule());
    }
}
