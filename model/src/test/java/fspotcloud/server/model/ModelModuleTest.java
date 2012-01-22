package fspotcloud.server.model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ModelModuleTest extends TestCase {

	ModelModule module = new ModelModule();
	
	public static TestSuite suite() {
		return new TestSuite(ModelModuleTest.class);
	}
	public void testInjector() {
		Injector injector = Guice.createInjector(module);
		assertNotNull(injector);
	}
}
