/**
 * 
 */
package fspotcloud.server.model.batch;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;

import com.google.inject.Provider;

import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Batches;

public class BatchManagerTest extends DatastoreTest {
	
	Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();
	
	public static TestSuite suite(){
		return new TestSuite(BatchManagerTest.class);
	}
	
	Batches batchManager;

	
	public void setUp() throws Exception{
		super.setUp();
		batchManager = new BatchManager(pmProvider);
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
		//doTest();
		BatchDO batch = new BatchDO();
		batch.setJobName("test job");

		long l = batchManager.save(batch);
//		long k = batchManager.save(batch);

		List<Batch> all = batchManager.getAll();
	//	assertEquals(2, all.size());
		Batch retrieved = all.get(0);
		assertEquals("test job", retrieved.getJobName());

	}
	

}
