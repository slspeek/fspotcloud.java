package fspotcloud.server.model;
import java.util.HashMap;
import java.util.Map;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.inject.Provider;

// ...
        
public class MemCacheProvider implements Provider<Cache> {
	
	
	Cache cache;

	@SuppressWarnings("unchecked")
	public MemCacheProvider() {
		@SuppressWarnings("rawtypes")
		Map props = new HashMap();
	    props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
	    try {
	        CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	        cache = cacheFactory.createCache(props);
	    } catch (CacheException e) {
	        // ...
	    }
	}

	@Override
	public Cache get() {
		return cache;
	}
}
