package fspotcloud.server.model.peerdatabase;

import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;

import com.google.inject.Provider;

import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.PeerDatabase;

public class PeerDatabaseManagerTest extends DatastoreTest {
	private Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();
	private PeerDatabaseManager manager = new PeerDatabaseManager(pmProvider);
	

	public static TestSuite suite(){
		return new TestSuite(PeerDatabaseManagerTest.class);
	}
	
	
	public void testGet() {
		PeerDatabase defaultPD = manager.get();
		assertNotNull(defaultPD);
		PeerDatabase secondInstance = manager.get();
	}
	
}
