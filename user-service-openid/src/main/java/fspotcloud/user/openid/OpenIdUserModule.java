/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.user.openid;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;

/**
 *
 * @author steven
 */
public class OpenIdUserModule extends ServletModule {

    protected void configureServlets() {
        bind(String.class).annotatedWith(AdminEmail.class).toInstance("slspeek@gmail.com");
        bind(fspotcloud.user.UserService.class).to(fspotcloud.user.openid.OpenIdUserService.class);
    }
}
