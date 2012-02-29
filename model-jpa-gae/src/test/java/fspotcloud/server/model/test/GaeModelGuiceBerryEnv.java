package fspotcloud.server.model.test;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import fspotcloud.model.jpa.gae.photo.PhotoManager;
import fspotcloud.model.jpa.gae.tag.TagManager;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.simplejpadao.EMProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        bind(EntityManager.class).toProvider(EMProvider.class);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("gae");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);
        System.out.println("Test ModelModule configured.");
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
            Logger.getLogger(ModelModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
