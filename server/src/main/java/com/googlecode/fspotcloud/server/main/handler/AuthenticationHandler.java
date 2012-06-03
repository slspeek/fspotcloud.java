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
package com.googlecode.fspotcloud.server.main.handler;

import static com.google.common.collect.Lists.newArrayList;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.fspotcloud.shared.main.AuthenticationAction;
import com.googlecode.fspotcloud.shared.main.AuthenticationResult;
import com.googlecode.fspotcloud.user.ISessionEmail;
import com.googlecode.fspotcloud.user.UserService;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class AuthenticationHandler extends SimpleActionHandler<AuthenticationAction, AuthenticationResult> {
    private final UserService userService;
    private final UserDao userDao;
    private final Provider<ISessionEmail> sessionEmailProvider;

    @Inject
    public AuthenticationHandler(UserService userService, UserDao userDao,
        Provider<ISessionEmail> sessionEmailProvider) {
        this.userService = userService;
        this.userDao = userDao;
        this.sessionEmailProvider = sessionEmailProvider;
    }

    @Override
    public AuthenticationResult execute(AuthenticationAction action,
        ExecutionContext context) throws DispatchException {
        if (!"".equals(action.getUserName())) {
            User user = userDao.find(action.getUserName());

            if (user != null) {
                if (action.getPassword().equals(user.getCredentials())) {
                    user.touchLastLoginTime();
                    userDao.save(user);

                    ISessionEmail sessionEmail = sessionEmailProvider.get();
                    sessionEmail.setEmail(user.getEmail());

                    return new AuthenticationResult(true);
                }
            }
        }

        return new AuthenticationResult(false);
    }
}
