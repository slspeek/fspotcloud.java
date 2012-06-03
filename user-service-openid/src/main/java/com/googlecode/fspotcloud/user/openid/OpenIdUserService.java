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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.user.openid;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.user.ISessionEmail;
import com.googlecode.fspotcloud.user.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
public class OpenIdUserService implements UserService {
    public static final String EMAIL = "email";
    @Inject
    Provider<ISessionEmail> sessionEmailProvider;
    @Inject
    @AdminEmail
    String adminEmail;
    @Inject
    Provider<HttpServletRequest> requestProvider;

    @Override
    public String createLoginURL(String destinationURL) {
        destinationURL = toAbsoluteURL(destinationURL);

        return "index.jsp?dest=" + destinationURL;
    }

    @Override
    public String createLogoutURL(String destinationURL) {
        destinationURL = toAbsoluteURL(destinationURL);

        return "index.jsp?logout=true&dest=" + destinationURL;
    }

    private String toAbsoluteURL(String url) {
        HttpServletRequest request = requestProvider.get();
        String result = request.getScheme() + "://" + request.getServerName() +
            ":" + request.getServerPort() + request.getContextPath() + "/" +
            url;

        return result;
    }

    @Override
    public String getEmail() {
        return sessionEmailProvider.get().getEmail();
    }

    @Override
    public boolean isUserLoggedIn() {
        return getEmail() != null;
    }

    @Override
    public boolean isUserAdmin() {
        return adminEmail.equals(getEmail());
    }
}
