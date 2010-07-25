/**
 * 
 */
package fspotcloud.server.model.batch;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Provider;

import fspotcloud.server.model.PersistenceManagerProvider;

/**
 * @author fspotcloud
 * 
 */

public class BatchManagerTest extends TestCase {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private Batches batchManager;

	private Provider<PersistenceManager> pmProviver = new PersistenceManagerProvider();

	@Before
	public void setUp() {
		helper.setUp();
		batchManager = new BatchManager(pmProviver);
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	private void doTest() {
		BatchDO batch = new BatchDO();
		batch.setJobName("test job");

		long l = batchManager.save(batch);
		long k = batchManager.save(batch);

		List<BatchDO> all = batchManager.getAll();
		assertEquals(1, all.size());
		Batch retrieved = all.get(0);
		assertEquals("test job", retrieved.getJobName());

	}

	@Test
	public void testDelete() {
		BatchDO batch = new BatchDO();
		batch.setJobName("deletable job");
		long l = batchManager.save(batch);

		List<BatchDO> all = batchManager.getAll();
		assertEquals(1, all.size());
		batchManager.delete(batch);
		all = batchManager.getAll();
		assertEquals(0, all.size());
	}

	@Test
	public void testCreateLoadModifySave() {
		BatchDO batch = new BatchDO();
		long l = batchManager.save(batch);

		BatchDO retrieved = batchManager.getById(l);
		retrieved.setResult("PI");
		long k = batchManager.save(retrieved);
		assertEquals(l, k);
		retrieved = batchManager.getById(l);
		assertEquals("PI", retrieved.getResult());

	}

	@Test
	public void testThrowsObjectNotFound() {
		try {
			Batch retrieved = batchManager.getById(10);
			fail();
		} catch(JDOObjectNotFoundException yes) {
			
		}
		

	}

	
	@Test
	public void testInsert1() {
		doTest();
	}

	@Test
	public void testInsert2() {
		doTest();
	}
}
