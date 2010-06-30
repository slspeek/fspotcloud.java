package fspotcloud.server.model.batch;

import javax.jdo.PersistenceManager;

import fspotcloud.server.util.PMF;
import fspotcloud.shared.admin.BatchInfo;

public class BatchReader {

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
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Batch batch = null;
		try {
			batch = pm.getObjectById(Batch.class, batchId);
		} finally {
			pm.close();
		}
		return batch;
	}

}
