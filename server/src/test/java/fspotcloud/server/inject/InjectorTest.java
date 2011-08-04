package fspotcloud.server.inject;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.model.ModelModule;

public class InjectorTest extends TestCase {

	public void testInjector() {
		Injector injector = Guice.createInjector(new FSpotCloudModule(),
				new ModelModule(), new TaskModule(),
				new FSpotCloudServletModule());
		assertNotNull(injector);
	}
}
