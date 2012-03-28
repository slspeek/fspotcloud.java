package com.googlecode.fspotcloud.server.admin.integration;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.controller.inject.LocalControllerModule;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.gae.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.gae.tag.TagManager;
import com.googlecode.fspotcloud.peer.CopyDatabase;
import com.googlecode.fspotcloud.peer.ImageData;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.server.admin.actions.ImportTagHandler;
import com.googlecode.fspotcloud.server.admin.actions.SynchronizePeerHandler;
import com.googlecode.fspotcloud.server.admin.actions.TagDeleteAllHandler;
import com.googlecode.fspotcloud.server.admin.actions.UnImportTagHandler;
import com.googlecode.fspotcloud.server.control.task.TaskActionsModule;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.ImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.SynchronizePeer;
import com.googlecode.fspotcloud.shared.dashboard.actions.TagDeleteAll;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import com.googlecode.fspotcloud.user.LenientUserService;
import com.googlecode.fspotcloud.user.UserService;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

public class GaeIntegrationGuiceBerryEnv extends GuiceBerryModule {

    @Override
    public void configure() {
        super.configure();
        System.setProperty("photo.dir.original", "//home/steven/Photos");
        System.setProperty("photo.dir.override", "" + System.getProperty("user.dir") + "/../peer/src/test/resources/Photos");
        install(new MyFSpotCloudModule());
        install(new MyAdminActionsModule());
        install(new MyModelModule());
        install(new MyTaskModule());
        install(new LocalControllerModule());
        install(new TaskQueueDispatchDirectModule());
        install(new TaskActionsModule());
        install(new PeerActionsModule());
        install(new MyPeerModule());
        install(new MyUserModule());
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
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
            Logger.getLogger(GaeIntegrationGuiceBerryEnv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

class MyAdminActionsModule extends ActionHandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(TagDeleteAll.class, TagDeleteAllHandler.class);
        bindHandler(ImportTag.class, ImportTagHandler.class);
        bindHandler(UnImportTag.class, UnImportTagHandler.class);
        bindHandler(SynchronizePeer.class, SynchronizePeerHandler.class);
    }
}

class MyFSpotCloudModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
                new Integer(3));
    }
}

class MyTaskModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ImageSpecs.class).annotatedWith(Names.named("defaultImageSpecs")).toInstance(new ImageSpecs(1024, 768, 512, 378));
        bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks")).toInstance(2);
    }
}

class MyModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);

        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(1));
        install(new EntityModule("gae"));
    }
}

class MyPeerModule extends AbstractModule {

    protected void configure() {
        bind(Data.class).in(Singleton.class);
        bind(ImageData.class);
        bind(String.class).annotatedWith(Names.named("JDBC URL")).toProvider(CopyDatabase.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("DatabasePath")).toInstance(
                System.getProperty(
                "db",
                System.getProperty("user.dir")
                + "/../peer/src/test/resources/photos.db"));
        bind(String.class).annotatedWith(Names.named("WorkDir")).toInstance(
                System.getProperty("user.dir"));
        bind(Integer.class).annotatedWith(Names.named("stop port")).toInstance(
                Integer.valueOf(System.getProperty("stop.port", "4444")));
    }
}

class MyUserModule extends AbstractModule {

    protected void configure() {
        bind(UserService.class).to(LenientUserService.class);
    }
}