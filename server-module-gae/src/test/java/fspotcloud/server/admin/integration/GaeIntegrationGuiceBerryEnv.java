package fspotcloud.server.admin.integration;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.guiceberry.GuiceBerryEnvMain;
import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistService;
import fspotcloud.botdispatch.controller.inject.LocalControllerModule;
import fspotcloud.peer.CopyDatabase;
import fspotcloud.peer.ImageData;
import fspotcloud.peer.db.Data;
import fspotcloud.peer.inject.PeerActionsModule;
import fspotcloud.server.admin.actions.ImportTagHandler;
import fspotcloud.server.admin.actions.SynchronizePeerHandler;
import fspotcloud.server.admin.actions.TagDeleteAllHandler;
import fspotcloud.server.admin.actions.UnImportTagHandler;
import fspotcloud.server.control.task.TaskActionsModule;
import fspotcloud.server.model.MemCacheProvider;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.photo.CachedPhotoManager;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.model.tag.CachedTagManager;
import fspotcloud.server.model.tag.TagManager;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.TagDeleteAll;
import fspotcloud.shared.dashboard.actions.UnImportTag;
import fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;
import fspotcloud.user.LenientUserService;
import fspotcloud.user.UserService;
import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import net.sf.jsr107cache.Cache;


public class GaeIntegrationGuiceBerryEnv extends GuiceBerryModule {

    @Override
    public void configure() {
        super.configure();
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
        bind(GuiceBerryEnvMain.class).to(PersistServiceInitMain.class);
    }

    static class PersistServiceInitMain implements GuiceBerryEnvMain {

        @Inject
        PersistService service;

        @Override
        public void run() {
            System.setProperty("appengine.orm.duplicate.emf.exception", "true");
            service.start();
            System.out.println("Persistency Started! ");
        }
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

	@Provides
	DatastoreService getDatastoreService() {
		return DatastoreServiceFactory.getDatastoreService();
	}

}

class MyTaskModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ImageSpecs.class).annotatedWith(Names.named("defaultImageSpecs"))
				.toInstance(new ImageSpecs(1, 1, 1, 1));
		bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks"))
				.toInstance(2);
	}

}

class MyModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Photos.class).to(CachedPhotoManager.class).in(Singleton.class);
		bind(Photos.class).annotatedWith(Names.named("uncached"))
				.to(PhotoManager.class).in(Singleton.class);
		bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
				Singleton.class);
		bind(Tags.class).to(CachedTagManager.class).in(Singleton.class);
		bind(Tags.class).annotatedWith(Names.named("uncached"))
				.to(TagManager.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(
				PersistenceManagerProvider.class);
		bind(Cache.class).toProvider(MemCacheProvider.class);
		bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(1));
	}
}
class MyPeerModule extends AbstractModule {

	protected void configure() {
		bind(Data.class).in(Singleton.class);
		bind(ImageData.class);
		bind(String.class).annotatedWith(Names.named("JDBC URL"))
				.toProvider(CopyDatabase.class).in(Singleton.class);
		bind(String.class)
				.annotatedWith(Names.named("DatabasePath"))
				.toInstance(
						System.getProperty(
								"db",
								System.getProperty("user.home")
										+ "/fspotcloud/peer/src/test/resources/photos.db"));
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