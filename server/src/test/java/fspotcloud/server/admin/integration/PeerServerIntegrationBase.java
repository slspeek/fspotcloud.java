package fspotcloud.server.admin.integration;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import net.sf.jsr107cache.Cache;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.inject.LocalControllerModule;
import fspotcloud.peer.db.Data;
import fspotcloud.peer.inject.PeerActionsModule;
import fspotcloud.peer.inject.PeerModule;
import fspotcloud.server.admin.actions.ImportTagHandler;
import fspotcloud.server.admin.actions.SynchronizePeerHandler;
import fspotcloud.server.admin.actions.TagDeleteAllHandler;
import fspotcloud.server.admin.actions.UnImportTagHandler;
import fspotcloud.server.control.task.TaskActionsModule;
import fspotcloud.server.model.DatastoreTest;
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
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;

public abstract class PeerServerIntegrationBase extends DatastoreTest {

	protected static final Logger log = Logger
				.getLogger(PeerServerIntegrationTest.class.getName());
	Injector injector;
	protected Photos photos;
	protected Tags tags;
	protected ControllerDispatchAsync controller;
	protected PeerDatabases peers;
	Data data;
	protected Dispatch dispatch;

	public PeerServerIntegrationBase() {
		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		super.setUp();
		String basedir = (new File(".")).getAbsolutePath();
		System.setProperty("photo.dir.override", "file:/" + basedir.substring(0, basedir.length()-2)
				+ "/../peer/src/test/resources/Photos");
		System.setProperty("photo.dir.original", "file:///home/steven/Photos");
		setPeerTestDatabase("photos.db");
		injector = Guice.createInjector(new MyFSpotCloudModule(),
				new MyAdminActionsModule(), new MyModelModule(),
				new MyTaskModule(), new LocalControllerModule(),
				new TaskQueueDispatchDirectModule(), new TaskActionsModule(),
				new PeerModule(), new PeerActionsModule());
		photos = injector.getInstance(Photos.class);
		tags = injector.getInstance(Tags.class);
		controller = injector.getInstance(ControllerDispatchAsync.class);
		peers = injector.getInstance(PeerDatabases.class);
		dispatch = injector.getInstance(Dispatch.class);
		data = injector.getInstance(Data.class);
	}

	protected void setPeerTestDatabase(String db) throws SQLException {
		String basedir = (new File(".")).getAbsolutePath();
		File testDatabase = new File(basedir + "./peer/src/test/resources/"
				+ db);
		String path = testDatabase.getPath();
		System.setProperty("db", path);
		log.info("DBPath " + path);
		if (data != null) {
			data.setJDBCUrl("jdbc:sqlite:" + path);
		}
	}

	protected void verifyPhotoRemoved(String id) {
		try {
			photos.getById(id);
			Assert.fail();
		} catch (JDOObjectNotFoundException e) {
		}
	}
	
	protected void assertPhotoLoaded(String id) {
		try {
			photos.getById(id);
		} catch (JDOObjectNotFoundException e) {
			Assert.fail();
		}
	}
	
	protected void assertTagLoaded(String id) {
		try {
			tags.getById(id);
		} catch (JDOObjectNotFoundException e) {
			Assert.fail();
		}
	}


	protected void assertPhotosRemoved(String  ...allIds) {
		for (String id: allIds) {
			verifyPhotoRemoved(id);
		}
		
	}
	protected void assertPhotosLoaded(String  ...allIds) {
		for (String id: allIds) {
			assertPhotoLoaded(id);
		}
	}

	protected void assertTagsLoaded(String  ...allIds) {
		for (String id: allIds) {
			assertTagLoaded(id);
		}
	}

	protected void assertTagsRemoved(String  ...allIds) {
		for (String id: allIds) {
			verifyTagRemoved(id);
		}
		
	}
	
	protected void verifyTagRemoved(String id) {
		try {
			tags.getById(id);
			Assert.fail();
		} catch (JDOObjectNotFoundException e) {
		}
	}

	protected void synchronizePeer() {
		controller.execute(new SynchronizePeer(),
				new AsyncCallback<VoidResult>() {
	
					@Override
					public void onFailure(Throwable caught) {
						log.log(Level.SEVERE, "On fail ", caught);
	
					}
	
					@Override
					public void onSuccess(VoidResult result) {
						log.info("On success");
					}
	
				});
	}

	protected void importTag(String tagId) {
		controller.execute(new ImportTag(tagId, 0),
				new AsyncCallback<VoidResult>() {
	
					@Override
					public void onFailure(Throwable caught) {
						log.info("On fail " + caught);
	
					}
	
					@Override
					public void onSuccess(VoidResult result) {
						log.info("On success");
					}
	
				});
	}

	protected void unImportTag(String tagId) {
		controller.execute(new UnImportTag(tagId),
				new AsyncCallback<VoidResult>() {
	
					@Override
					public void onFailure(Throwable caught) {
						log.info("On fail " + caught);
	
					}
	
					@Override
					public void onSuccess(VoidResult result) {
						log.info("On success");
					}
	
				});
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
