package fspotcloud.server.model;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ModelModuleTest extends TestCase {

	ModelModule module = new ModelModule();
	
	public void testInjector() {
		Injector injector = Guice.createInjector(module);
		assertNotNull(injector);
	}
}
