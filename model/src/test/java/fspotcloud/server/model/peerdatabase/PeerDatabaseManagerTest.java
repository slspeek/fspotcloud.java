package fspotcloud.server.model.peerdatabase;

import static org.mockito.Mockito.mock;

import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;
import net.sf.jsr107cache.Cache;

import com.google.inject.Provider;

import fspotcloud.server.model.CloudcoverDatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.shared.tag.TagNode;

public class PeerDatabaseManagerTest extends CloudcoverDatastoreTest {
	private Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();
	private Cache cache;
	private PeerDatabaseManager manager;
	

	public static TestSuite suite(){
		return new TestSuite(PeerDatabaseManagerTest.class);
	}
	
	
	
	@Override
	public void setUp() throws Exception {
		cache = mock(Cache.class);
		manager  = new PeerDatabaseManager(pmProvider, cache);
		super.setUp();
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
