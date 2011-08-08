package fspotcloud.server.inject;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.model.ModelModule;

public class InjectorTest extends TestCase {
	public static TestSuite suite() {
		return new TestSuite(InjectorTest.class);
	}
	public void testInjector() {
		Injector injector = Guice.createInjector(new FSpotCloudModule(),
				new ModelModule(), new TaskModule(),
				new FSpotCloudServletModule());
		assertNotNull(injector);
	}
}
