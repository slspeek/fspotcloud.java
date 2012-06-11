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
package com.googlecode.fspotcloud.user.gae;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.fspotcloud.user.LoginMetaData;
import com.googlecode.fspotcloud.user.LoginMetaDataUpdater;
import com.googlecode.fspotcloud.user.PostThirdPartyLoginWorker;
import javax.inject.Inject;


public class PostGoogleLoginWorker implements PostThirdPartyLoginWorker {
    @Inject
    private UserDao userDao;
    @Inject
    private LoginMetaDataUpdater metaDataUpdater;
    @Inject
    private UserService googleUserService;

    @Override
    public void doWork() {
        User gaeUser = googleUserService.getCurrentUser();

        if (gaeUser != null) {
            String email = gaeUser.getEmail();
            com.googlecode.fspotcloud.server.model.api.User user = userDao.findOrNew(email);
            metaDataUpdater.doUpdate(user, LoginMetaData.Type.GAE_LOGIN);
        }
    }
}