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
            
package com.googlecode.fspotcloud.server.admin.integration;

import com.google.guiceberry.TestWrapper;
import com.google.inject.Singleton;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandManager;
import com.googlecode.fspotcloud.model.jpa.CachedModelModule;
import com.googlecode.simpleblobstore.gae.GaeSimpleBlobstoreModule;
import static org.mockito.Mockito.mock;
public class GaeNoAuthIntegrationIntegrationModule
    extends NoAuthPlaceHolderIntegrationModule {
    @Override
    public void configure() {
        super.configure();
        System.setProperty("appengine.orm.disable.duplicate.emf.exception",
            "true");
        install(new CachedModelModule(3));
        install(new GaeSimpleBlobstoreModule());
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);

        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
    }
}