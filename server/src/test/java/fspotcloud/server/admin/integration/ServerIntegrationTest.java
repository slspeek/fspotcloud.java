package fspotcloud.server.admin.integration;

import java.net.URL;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.inject.LocalControllerModule;
import fspotcloud.peer.inject.PeerActionsModule;
import fspotcloud.peer.inject.PeerModule;
import fspotcloud.server.admin.actions.ImportTagHandler;
import fspotcloud.server.admin.actions.SynchronizePeerHandler;
import fspotcloud.server.admin.actions.TagDeleteAllHandler;
import fspotcloud.server.admin.actions.UnImportTagHandler;
import fspotcloud.server.control.task.TaskActionsModule;
import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.inject.FSpotCloudModule;
import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.ModelModule;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.TagDeleteAll;
import fspotcloud.shared.dashboard.actions.UnImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;

public class ServerIntegrationTest extends DatastoreTest {

	final static private Logger log = Logger
			.getLogger(ServerIntegrationTest.class.getName());
	Injector injector;
	Photos photos;
	Tags tags;
	ControllerDispatchAsync controller;
	PeerDatabases peers;
	private Dispatch dispatch;

	public void setUp() throws Exception {
		super.setUp();
		URL testDatabase = ClassLoader.getSystemResource("photos.db");
		String path = testDatabase.getPath();
		System.setProperty("db", path);
		injector = Guice.createInjector(new FSpotCloudModule(),
				new MyAdminActionsModule(), new ModelModule(),
				new TaskModule(), new LocalControllerModule(),
				new TaskQueueDispatchDirectModule(), new TaskActionsModule(),
				new PeerModule(), new PeerActionsModule());
		photos = injector.getInstance(Photos.class);
		tags = injector.getInstance(Tags.class);
		controller = injector.getInstance(ControllerDispatchAsync.class);
		peers = injector.getInstance(PeerDatabases.class);
		dispatch = injector.getInstance(Dispatch.class);
	}

	public void testImportOffAllTags() throws Exception {
		PeerDatabase peer = peers.get();
		peers.save(peer);
		controller.execute(new SynchronizePeer(),
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
		tags.getById("1");
		tags.getById("2");
		tags.getById("3");
		tags.getById("4");
		tags.getById("5");
	}

	public void testPeer() throws DispatchException {
		PeerMetaDataResult result = dispatch.execute(new GetPeerMetaData());
		assertEquals(28, result.getPhotoCount());
		assertEquals(5, result.getTagCount());
	}

	public void testImportOneLabel() throws Exception {
		testImportOffAllTags();
		controller.execute(new ImportTag("5", 0),
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
		photos.getById("3");

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