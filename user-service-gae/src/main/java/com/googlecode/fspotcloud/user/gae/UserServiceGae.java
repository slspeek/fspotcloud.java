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

import com.google.inject.Provider;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


public class UserServiceGae implements com.googlecode.fspotcloud.user.UserService {
    @Inject
    com.google.appengine.api.users.UserService delegate;
    @Inject
    Provider<HttpServletRequest> requestProvider;

    private String toAbsoluteURL(String url) {
        HttpServletRequest request = requestProvider.get();
        String result = request.getScheme() + "://" + request.getServerName() +
            ":" + request.getServerPort() + request.getContextPath() + "/" +
            url;

        return result;
    }

    @Override
    public String createLoginURL(String string) {
        string = toAbsoluteURL(string);

        return delegate.createLoginURL(string);
    }

    @Override
    public String createLogoutURL(String string) {
        string = toAbsoluteURL(string);

        return delegate.createLogoutURL(string);
    }

    @Override
    public String getEmail() {
        if (isUserLoggedIn()) {
            return delegate.getCurrentUser().getEmail();
        } else {
            return null;
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return delegate.isUserLoggedIn();
    }

    @Override
    public boolean isUserAdmin() {
        return isUserLoggedIn() && delegate.isUserLoggedIn();
    }
}
