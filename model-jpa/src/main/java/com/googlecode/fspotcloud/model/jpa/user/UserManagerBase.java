/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.model.jpa.user;

import static com.google.common.collect.Lists.newArrayList;
import com.googlecode.fspotcloud.server.model.api.*;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.fspotcloud.shared.main.PhotoInfoStore;
import com.googlecode.fspotcloud.shared.main.TagNode;
import com.googlecode.simplejpadao.SimpleDAOGenIdImpl;
import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class UserManagerBase<T extends User, U extends T>
    extends SimpleDAOGenIdImpl<User, U, Long> implements UserDao {
    private static final Logger log = Logger.getLogger(UserManagerBase.class.getName());

    @Inject
    public UserManagerBase(Class<U> entityType,
        Provider<EntityManager> emProvider, @Named("maxDelete")
    Integer maxDelete) {
        super(entityType, emProvider);
    }

    protected void detach(User user) {
        user.setUserGroups(newArrayList(user.getUserGroups()));
        user.setPeers(newArrayList(user.getPeers()));
    }

    protected abstract User newUser();

    protected abstract Class<?extends User> getEntityClass();
}
