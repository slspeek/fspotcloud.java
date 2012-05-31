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

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.gae.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.gae.tag.TagManager;
import com.googlecode.fspotcloud.model.jpa.gae.user.UserManager;
import com.googlecode.fspotcloud.server.model.api.PeerDatabaseDao;
import com.googlecode.fspotcloud.server.model.api.PhotoDao;
import com.googlecode.fspotcloud.server.model.api.TagDao;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.simplejpadao.EntityModule;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;


public class GaeModelGuiceBerryEnv extends GuiceBerryModule {
    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        install(new ModelModule());
    }
}


class ModelModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PhotoDao.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabaseDao.class).to(PeerDatabaseManager.class)
            .in(Singleton.class);
        bind(TagDao.class).to(TagManager.class).in(Singleton.class);
        bind(UserDao.class).to(UserManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete"))
            .toInstance(new Integer(100));
        install(new EntityModule("gae-test"));
        System.out.println("Test ModelModule configured.");
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
            Logger.getLogger(ModelModule.class.getName())
                  .log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
