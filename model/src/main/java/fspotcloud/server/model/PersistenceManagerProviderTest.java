package fspotcloud.server.model;

import javax.jdo.PersistenceManager;

import junit.framework.TestCase;

public class PersistenceManagerProviderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testPersistenceManagerProvider() {
		//Test a simple instantiation
		PersistenceManagerProvider pmp = new PersistenceManagerProvider();
	}

	public final void testGet() {
		PersistenceManagerProvider pmp = new PersistenceManagerProvider();
		PersistenceManager pm = pmp.get();
		assertNotNull(pm);
	}

}
