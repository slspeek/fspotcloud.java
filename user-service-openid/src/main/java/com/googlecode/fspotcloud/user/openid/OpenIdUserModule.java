/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.user.openid;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;

/**
 *
 * @author steven
 */
public class OpenIdUserModule extends ServletModule {

    protected void configureServlets() {
        bind(String.class).annotatedWith(AdminEmail.class).toInstance("slspeek@gmail.com");
        bind(com.googlecode.fspotcloud.user.UserService.class).to(com.googlecode.fspotcloud.user.openid.OpenIdUserService.class);
    }
}
