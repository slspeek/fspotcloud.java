package com.googlecode.fspotcloud.server.model.test.gae;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.gae.photo.PhotoManager;
import com.googlecode.fspotcloud.server.model.test.GaeLocalDatastoreTestWrapper;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.simplejpadao.SimpleDAONamedId;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

public class PhotoGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        install(new EntityModule("gae-test"));
        bind(SimpleDAONamedId.class).to(PhotoManager.class);
    }

    @Provides
    public Cache get() {
        try {
            Cache cache;
            Map props = new HashMap();
            props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(props);
            return cache;
        } catch (CacheException ex) {
            Logger.getLogger(TagGuiceBerryEnv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
