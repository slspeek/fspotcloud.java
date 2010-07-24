package fspotcloud.server.model.batch;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.shared.admin.BatchInfo;

public class BatchManager implements Batches {

	private final Provider<PersistenceManager> pmProviver;

	@Inject
	public BatchManager(Provider<PersistenceManager> pmProviver) {
		this.pmProviver = pmProviver;
	}

	public BatchInfo getBatchInfo(long batchId) {
		Batch batch = getById(batchId);
		BatchInfo batchInfo = new BatchInfo();
		batchInfo.setKey(batch.getKey());
		batchInfo.setJobName(batch.getJobName());
		batchInfo.setRunning(batch.isRunning());
		batchInfo.setCtime(batch.getCtime());
		batchInfo.setFtime(batch.getFtime());
		batchInfo.setResult(batch.getResult());
		batchInfo.setState(new String[] { batch.getState() });
		batchInfo.setInterationCount(batch.getInterationCount());
		return batchInfo;
	}

	public Batch getById(long batchId) {
		Batch batch = null;
		PersistenceManager pm = pmProviver.get();
		try {
			batch = pm.getObjectById(Batch.class, batchId);
			batch = pm.detachCopy(batch);
		} finally {
			pm.close();
		}
		return batch;
	}

	public long save(Batch batch) {
		PersistenceManager pm = pmProviver.get();
		try {
			pm.makePersistent(batch);
		} finally {
			pm.close();
		}
		return batch.getKey();
	}

	public void delete(Batch batch) {
		long id = batch.getKey();
		PersistenceManager pm = pmProviver.get();
		try {
			pm.deletePersistent(pm.getObjectById(Batch.class, id));
		} finally {
			pm.close();
		}
	}

	public List<Batch> getAll() {
		PersistenceManager pm = pmProviver.get();
		try {
			List<Batch> batches = new ArrayList<Batch>();
			Extent<Batch> extent = pm.getExtent(Batch.class, false);
			for (Batch batch : extent) {
				batches.add(batch);
			}
			extent.closeAll();

			return batches;
		} finally {
			pm.close();
		}

	}
}
