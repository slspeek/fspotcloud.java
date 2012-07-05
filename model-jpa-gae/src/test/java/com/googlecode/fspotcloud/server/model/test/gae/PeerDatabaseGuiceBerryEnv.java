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
            
package com.googlecode.fspotcloud.server.model.test.gae;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.CachedPeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseEntity;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseManagerBase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.test.GaeLocalDatastoreTestWrapper;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.simplejpadao.SimpleDAONamedId;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PeerDatabaseGuiceBerryEnv extends GuiceBerryModule {
    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete"))
            .toInstance(new Integer(100));
        install(new EntityModule("gae-test"));
        bind(SimpleDAONamedId.class).to(CachedPeerDatabaseManager.class);
        bind(new TypeLiteral<PeerDatabaseManagerBase<PeerDatabase, PeerDatabaseEntity>>() {
            }).to(PeerDatabaseManager.class);
    }

    @Provides
    public Cache get() {
        try {
            Cache cache;
            Map props = new HashMap();
            props.put(GCacheFactory.EXPIRATION_DELTA, 3600);

            CacheFactory cacheFactory = CacheManager.getInstance()
                                                    .getCacheFactory();
            cache = cacheFactory.createCache(props);

            return cache;
        } catch (CacheException ex) {
            Logger.getLogger(TagGuiceBerryEnv.class.getName())
                  .log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
