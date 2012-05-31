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

import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.simplejpadao.SimpleDAOGenIdImpl;
import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;
import com.googlecode.simplejpadao.cacheddao.CachedSimpleDAOGenIdImpl;
import net.sf.jsr107cache.Cache;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;


public abstract class CachedUserManagerBase<T extends User, U extends T>
    extends CachedSimpleDAOGenIdImpl<User, U, Long> implements UserDao {
    private static final Logger log = Logger.getLogger(CachedUserManagerBase.class.getName());
    private final Provider<EntityManager> emProvider;
    private final Integer maxDelete;

    @Inject
    public CachedUserManagerBase(Class<U> entityType,
                                 Provider<EntityManager> emProvider,
                                 @Named("maxDelete")
                                 Integer maxDelete, Cache cache) {
        super(cache, entityType,
                    new SimpleDAOGenIdImpl<User, U, Long>(entityType, emProvider));
        this.emProvider = emProvider;
        this.maxDelete = maxDelete;
    }

    @Override
    public User tryToLogin(String email, String credentials) {
        List<User> tryToFind = findAllWhere(1, "email = ? AND credentials = ?",
                email, credentials);

        if (tryToFind.size() > 0) {
            return tryToFind.get(0);
        } else {
            return null;
        }
    }
}
