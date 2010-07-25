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

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public BatchManager(Provider<PersistenceManager> pmProviver) {
		this.pmProvider = pmProviver;
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

	public BatchDO getById(long batchId) {
		BatchDO batch = null;
		PersistenceManager pm = pmProvider.get();
		try {
			batch = pm.getObjectById(BatchDO.class, batchId);
			batch = pm.detachCopy(batch);
		} finally {
			pm.close();
		}
		return batch;
	}

	public long save(BatchDO batch) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(batch);
		} finally {
			pm.close();
		}
		return batch.getKey();
	}

	public void delete(Batch batch) {
		long id = batch.getKey();
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistent(pm.getObjectById(BatchDO.class, id));
		} finally {
			pm.close();
		}
	}

	public List<BatchDO> getAll() {
		PersistenceManager pm = pmProvider.get();
		try {
			List<BatchDO> batches = new ArrayList<BatchDO>();
			Extent<BatchDO> extent = pm.getExtent(BatchDO.class, false);
			for (BatchDO batch : extent) {
				batches.add(batch);
			}
			extent.closeAll();

			return batches;
		} finally {
			pm.close();
		}

	}
}
