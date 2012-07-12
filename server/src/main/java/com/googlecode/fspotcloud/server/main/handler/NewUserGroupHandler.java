/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it
                and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free
                Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is
                distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied
                warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public
                License for more details.
 * You should have received a copy of the GNU General Public License
 * along
                with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330,
                Boston, MA 02111-1307, USA.
 *
 */

package com.googlecode.fspotcloud.server.main.handler;

import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.model.api.UserGroup;
import com.googlecode.fspotcloud.server.model.api.UserGroupDao;
import com.googlecode.fspotcloud.shared.main.GetUserGroupResult;
import com.googlecode.fspotcloud.shared.main.NewUserGroupAction;
import com.googlecode.fspotcloud.shared.main.UserGroupInfo;
import com.googlecode.fspotcloud.user.UserService;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class NewUserGroupHandler extends SimpleActionHandler<NewUserGroupAction, GetUserGroupResult> {
    private final UserGroupDao userGroupDao;
    private final UserService userService;

    @Inject
    public NewUserGroupHandler(UserGroupDao userGroupDao,
                               UserService userService) {
        this.userGroupDao = userGroupDao;
        this.userService = userService;
    }

    @Override
    public GetUserGroupResult execute(NewUserGroupAction action,
                                      ExecutionContext context) throws DispatchException {
        UserGroupInfo info = null;
        if (userService.isUserLoggedIn()) {
            String userName = userService.getEmail();
            UserGroup newGroup = userGroupDao.newEntity();
            newGroup.setOwner(userName);
            newGroup.setDescription("No description");
            newGroup.setName("New group");
            userGroupDao.save(newGroup);
            info = GetUserGroupHandler.get(newGroup);
        }
        return new GetUserGroupResult(info);
    }
}
