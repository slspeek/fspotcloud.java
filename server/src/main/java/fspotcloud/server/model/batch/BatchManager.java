package fspotcloud.server.model.batch;

import javax.jdo.PersistenceManager;

import com.google.inject.Inject;

import fspotcloud.shared.admin.BatchInfo;

public class BatchManager {
	
	private final PersistenceManager pm;
	
	@Inject
	public BatchManager(PersistenceManager pm) {
		this.pm =pm;
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
		batch = pm.getObjectById(Batch.class, batchId);
		return batch;
	}
	
	public long save(Batch batch) {
		pm.makePersistent(batch);
		return batch.getKey();
	}
}
