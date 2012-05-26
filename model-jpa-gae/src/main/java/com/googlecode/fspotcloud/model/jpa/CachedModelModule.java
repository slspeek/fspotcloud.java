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
package com.googlecode.fspotcloud.model.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.gae.GaeCacheProvider;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.gae.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.gae.tag.TagManager;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.simplejpadao.EntityModule;
import net.sf.jsr107cache.Cache;


public class CachedModelModule extends AbstractModule {
    private int maxDelete;

    public CachedModelModule(int maxDelete) {
        this.maxDelete = maxDelete;
    }

    @Override
    protected void configure() {
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class)
            .in(Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete"))
            .toInstance(maxDelete);
        bind(Cache.class).toProvider(GaeCacheProvider.class); //.in(Singleton.class);
        install(new EntityModule("gae"));
    }
}
