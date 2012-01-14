package fspotcloud.server.model;

import fspotcloud.model.jpa.ModelModule;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

public class ModelModuleTest extends TestCase {

	ModelModule module = new ModelModule();
	
	public static TestSuite suite() {
		return new TestSuite(ModelModuleTest.class);
	}
	public void testInjector() {
		Injector injector = Guice.createInjector(module);
		assertNotNull(injector);
                PersistService s = injector.getInstance(PersistService.class);
                s.start();
	}
}
