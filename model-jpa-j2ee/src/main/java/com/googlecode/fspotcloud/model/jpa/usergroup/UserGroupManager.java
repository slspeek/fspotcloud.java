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
package com.googlecode.fspotcloud.model.jpa.usergroup;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.server.model.api.UserGroup;
import com.googlecode.fspotcloud.server.model.api.UserGroupDao;
import javax.persistence.EntityManager;


public class UserGroupManager extends UserGroupManagerBase<UserGroup, UserGroupEntity>
    implements UserGroupDao {
    @Inject
    public UserGroupManager(Provider<EntityManager> pmProvider) {
        super(UserGroupEntity.class, pmProvider, 1000);
    }

    @Override
    protected UserGroup newUserGroup() {
        return new UserGroupEntity();
    }

    protected Class<?extends UserGroup> getEntityClass() {
        return UserGroupEntity.class;
    }
}
