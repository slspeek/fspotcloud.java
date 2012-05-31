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
package com.googlecode.fspotcloud.model.jpa.gae.user;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoManagerBase;
import com.googlecode.fspotcloud.model.jpa.user.CachedUserManagerBase;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.sf.jsr107cache.Cache;


public class UserManager extends CachedUserManagerBase<User, UserEntity>
    implements UserDao {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoManagerBase.class.getName());

    @Inject
    public UserManager(Provider<EntityManager> pmProvider, Cache cache) {
        super(UserEntity.class, pmProvider, 1000, cache);
    }

    protected Class<?extends User> getEntityClass() {
        return UserEntity.class;
    }
}
