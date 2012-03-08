package com.googlecode.fspotcloud.user.gae;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class UserModuleGae extends AbstractModule {

    @Override
    protected void configure() {
        bind(com.googlecode.fspotcloud.user.UserService.class).to(com.googlecode.fspotcloud.user.gae.UserServiceGae.class);
    }
    
    @Provides
    com.google.appengine.api.users.UserService getService() {
        return UserServiceFactory.getUserService();
    }
}
