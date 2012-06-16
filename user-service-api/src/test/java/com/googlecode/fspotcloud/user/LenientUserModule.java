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

import com.google.inject.AbstractModule;
import com.googlecode.fspotcloud.user.inject.AbstractUserModule;
import com.googlecode.fspotcloud.user.inject.ServerAddress;


/**
 * User module for testing, no authentication checks done at all.
 *
 * @author steven
*/
public class LenientUserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class).to(LenientUserService.class);
        bind(IAdminPermission.class).to(AdminPermission.class);
        bind(ILoginMetaData.class).to(LoginMetaData.class);
        bind(ILoginMetaDataUpdater.class).to(LoginMetaDataUpdater.class);
        bind(String.class).annotatedWith(ServerAddress.class)
            .toInstance("http://localhost:8080/context");
    }
}
