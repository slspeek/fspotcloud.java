package fspotcloud.server.inject;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.controller.inject.ControllerServletModule;
import fspotcloud.botdispatch.model.CommandModelModule;
import fspotcloud.botdispatch.model.MinimalCommandModelModule;
import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.model.ModelModule;
import fspotcloud.server.model.api.PeerDatabases;

public class InjectorTest extends TestCase {
	public static TestSuite suite() {
		return new TestSuite(InjectorTest.class);
	}

	public void testInjector() {
		Injector injector = Guice.createInjector(new FSpotCloudModule(),
				new ModelModule(), new TaskModule(),
				new FSpotCloudServletModule(), new ControllerServletModule(),
				new ControllerModule(), new MinimalCommandModelModule());

		assertNotNull(injector);
		// PeerDatabases defaultPeer =
		// injector.getInstance(PeerDatabases.class);
		ControllerDispatchAsync controller = injector
				.getInstance(ControllerDispatchAsync.class);
	}
}
