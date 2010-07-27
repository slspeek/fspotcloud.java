/**
 * 
 */
package fspotcloud.server.model.batch;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import junit.framework.TestCase;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Provider;

import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Batches;

/**
 * @author fspotcloud
 * 
 */

public class BatchManagerTest extends TestCase {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private Batches batchManager;

	private Provider<PersistenceManager> pmProviver = new PersistenceManagerProvider();

	
	public void setUp() {
		helper.setUp();
		batchManager = new BatchManager(pmProviver);
	}

	
	public void tearDown() {
		helper.tearDown();
	}

	private void doTest() {
		BatchDO batch = new BatchDO();
		batch.setJobName("test job");

		long l = batchManager.save(batch);
		long k = batchManager.save(batch);

		List<Batch> all = batchManager.getAll();
		assertEquals(1, all.size());
		Batch retrieved = all.get(0);
		assertEquals("test job", retrieved.getJobName());

	}

	
	public void testDelete() {
		Batch batch = new BatchDO();
		batch.setJobName("deletable job");
		long l = batchManager.save(batch);

		List<Batch> all = batchManager.getAll();
		assertEquals(1, all.size());
		batchManager.delete(batch);
		all = batchManager.getAll();
		assertEquals(0, all.size());
	}


	public void testCreateLoadModifySave() {
		Batch batch = new BatchDO();
		long l = batchManager.save(batch);

		Batch retrieved = batchManager.getById(l);
		retrieved.setResult("PI");
		long k = batchManager.save(retrieved);
		assertEquals(l, k);
		retrieved = batchManager.getById(l);
		assertEquals("PI", retrieved.getResult());
	}


	public void testThrowsObjectNotFound() {
		try {
			Batch retrieved = batchManager.getById(10);
			fail();
		} catch(JDOObjectNotFoundException yes) {
			
		}
	}

	

	public void testInsert1() {
		doTest();
	}


	public void testInsert2() {
		doTest();
	}
}
