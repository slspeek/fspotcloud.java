package fspotcloud.server.inject;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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

public class InjectorTest {
	@Test
	public void testInjector() {
		Injector injector = Guice.createInjector(new ServerServletModule(),
				new ServerModule(), new AdminActionsModule(), new ModelModule(),
				new TaskModule(), new ServerControllerModule(),
				new ControllerServletModule(), new MinimalCommandModelModule(),
				new TaskQueueDispatchModule(),
				new TaskQueueDispatchServletModule(), new TaskActionsModule());
		AssertJUnit.assertNotNull(injector);
		// PeerDatabases defaultPeer =
		// injector.getInstance(PeerDatabases.class);
		ControllerDispatchAsync controller = injector
				.getInstance(ControllerDispatchAsync.class);
		AssertJUnit.assertNotNull(controller);
	}
}
