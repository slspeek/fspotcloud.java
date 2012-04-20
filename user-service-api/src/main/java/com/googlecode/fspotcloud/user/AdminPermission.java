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
package com.googlecode.fspotcloud.user;

import com.googlecode.fspotcloud.user.UserService;

import javax.inject.Inject;
import javax.inject.Provider;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
public class AdminPermission {
    private final Provider<UserService> userServiceProvider;

    @Inject
    public AdminPermission(Provider<UserService> userServiceProvider) {
        this.userServiceProvider = userServiceProvider;
    }

    public void chechAdminPermission() {
        UserService userService = userServiceProvider.get();

        if (!userService.isUserAdmin()) {
            throw new SecurityException("User is not logged in as admin");
        }
    }
}
