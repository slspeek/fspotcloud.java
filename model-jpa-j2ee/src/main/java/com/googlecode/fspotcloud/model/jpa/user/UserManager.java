package com.googlecode.fspotcloud.model.jpa.user;

import com.googlecode.fspotcloud.server.model.api.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class UserManager extends UserManagerBase<User, UserEntity> {

    @Inject
    public UserManager(Provider<EntityManager> emProvider, @Named("maxDelete") Integer maxDelete) {
        super(UserEntity.class, emProvider, maxDelete);
    }

    @Override
    protected User newUser() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Class<? extends User> getEntityClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
