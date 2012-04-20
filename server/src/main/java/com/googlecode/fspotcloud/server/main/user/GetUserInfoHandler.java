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
package com.googlecode.fspotcloud.server.main.user;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.shared.main.actions.GetUserInfo;
import com.googlecode.fspotcloud.shared.main.actions.UserInfo;
import com.googlecode.fspotcloud.user.UserService;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class GetUserInfoHandler extends SimpleActionHandler<GetUserInfo, UserInfo> {
    private final UserService userService;

    @Inject
    public GetUserInfoHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserInfo execute(GetUserInfo action, ExecutionContext context)
        throws DispatchException {
        UserInfo info = new UserInfo(
                userService.getEmail(), userService.isUserAdmin(),
                userService.isUserLoggedIn(),
                userService.createLoginURL(action.getDestinationUrl()),
                userService.createLogoutURL(action.getDestinationUrl()));

        return info;
    }
}
