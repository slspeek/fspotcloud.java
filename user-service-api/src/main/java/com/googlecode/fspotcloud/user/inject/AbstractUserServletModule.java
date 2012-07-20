package com.googlecode.fspotcloud.user.inject;

import com.google.inject.servlet.ServletModule;
import com.googlecode.fspotcloud.user.emailconfirmation.ConfirmationServlet;

public class AbstractUserServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();
        serve("/confirm").with(ConfirmationServlet.class);
    }
}
