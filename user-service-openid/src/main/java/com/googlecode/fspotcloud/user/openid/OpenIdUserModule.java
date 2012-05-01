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
package com.googlecode.fspotcloud.user.openid;

import com.googlecode.fspotcloud.user.inject.AbstractUserModule;


public class OpenIdUserModule extends AbstractUserModule {
    private final String adminEmail;

    public OpenIdUserModule(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    protected void configureServlets() {
        super.configureServlets();
        bind(String.class).annotatedWith(AdminEmail.class).toInstance(
            adminEmail);
        bind(com.googlecode.fspotcloud.user.UserService.class)
            .to(com.googlecode.fspotcloud.user.openid.OpenIdUserService.class);
    }
}
