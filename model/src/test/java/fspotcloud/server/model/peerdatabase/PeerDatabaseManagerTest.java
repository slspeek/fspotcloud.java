package fspotcloud.server.model.peerdatabase;

import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;

import com.google.inject.Provider;

import fspotcloud.server.model.CloudcoverDatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.shared.tag.TagNode;

public class PeerDatabaseManagerTest extends CloudcoverDatastoreTest {
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

	public void testGetCachedTagTree() {
		PeerDatabase defaultPD = manager.get();
		List<TagNode> list = defaultPD.getCachedTagTree();
	}

	public void testDefaultsForThumbDimension() {
		PeerDatabase defaultPD = manager.get();
		String dim = defaultPD.getThumbDimension();
		assertEquals("512x384", dim);
	}
	
	public void testDefaultForGetTagCount() {
		PeerDatabase defaultPD = manager.get();
		int count = defaultPD.getTagCount();
		assertEquals(0, count);
	}

}
