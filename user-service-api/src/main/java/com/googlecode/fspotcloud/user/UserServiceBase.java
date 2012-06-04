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
package com.googlecode.fspotcloud.user;

import com.google.inject.Provider;
import com.googlecode.fspotcloud.user.ISessionEmail;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


public abstract class UserServiceBase implements com.googlecode.fspotcloud.user.UserService {
    @Inject
    Provider<HttpServletRequest> requestProvider;
    @Inject
    Provider<ISessionEmail> sessionEmailProvider;

    protected String toAbsoluteURL(String url) {
        HttpServletRequest request = requestProvider.get();
        String result = request.getScheme() + "://" + request.getServerName() +
            ":" + request.getServerPort() + request.getContextPath() + "/" +
            url;

        return result;
    }

    private String getSessionEmail() {
        return sessionEmailProvider.get().getEmail();
    }

    @Override
    public String getEmail() {
        return getSessionEmail();
    }

    @Override
    public boolean isUserLoggedIn() {
        return getSessionEmail() != null;
    }

    protected String getPostThirdPartyLoginURL() {
        return toAbsoluteURL("post-login");
    }

    protected String getPostThirdPartyLogoutURL() {
        return toAbsoluteURL("FSpotCloud.html");
    }
}
