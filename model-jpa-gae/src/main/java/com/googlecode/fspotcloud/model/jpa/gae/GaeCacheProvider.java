package com.googlecode.fspotcloud.model.jpa.gae;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.inject.Provider;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GaeCacheProvider implements Provider<Cache> {
    @Override
    public Cache get() {
        try {
            Cache cache;
            Map props = new HashMap();
            props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(props);
            return cache;
        } catch (CacheException ex) {
            Logger.getLogger(GaeCacheProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
