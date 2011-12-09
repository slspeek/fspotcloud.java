package fspotcloud.server.inject;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.inject.ControllerServletModule;
import fspotcloud.botdispatch.model.MinimalCommandModelModule;
import fspotcloud.server.control.task.TaskActionsModule;
import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.model.ModelModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchModule;
import fspotcloud.taskqueuedispatch.inject.TaskQueueDispatchServletModule;

public class InjectorTest extends TestCase {
	public static TestSuite suite() {
		return new TestSuite(InjectorTest.class);
	}

	public void testInjector() {
		Injector injector = Guice.createInjector(new FSpotCloudServletModule(),
				new FSpotCloudModule(), new AdminActionsModule(), new ModelModule(),
				new TaskModule(), new FSCControllerModule(),
				new ControllerServletModule(), new MinimalCommandModelModule(),
				new TaskQueueDispatchModule(),
				new TaskQueueDispatchServletModule(), new TaskActionsModule());
		assertNotNull(injector);
		// PeerDatabases defaultPeer =
		// injector.getInstance(PeerDatabases.class);
		ControllerDispatchAsync controller = injector
				.getInstance(ControllerDispatchAsync.class);
		assertNotNull(controller);
	}
}
