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
package com.googlecode.fspotcloud.server.model.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.user.UserManager;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.simplejpadao.SimpleDAONamedId;


public class UserGuiceBerryEnv extends GuiceBerryModule {
    @Override
    protected void configure() {
        super.configure();
        bind(Integer.class).annotatedWith(Names.named("maxDelete"))
            .toInstance(new Integer(100));
        install(new EntityModule("derby-test"));
        bind(SimpleDAONamedId.class).to(UserManager.class);
    }
}