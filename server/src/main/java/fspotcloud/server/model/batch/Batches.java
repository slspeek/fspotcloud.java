package fspotcloud.server.model.batch;

import java.util.List;

import fspotcloud.shared.admin.BatchInfo;

public interface Batches {

	public abstract BatchInfo getBatchInfo(long batchId);

	public abstract BatchDO getById(long batchId);

	public abstract long save(BatchDO batch);
	
	public abstract void delete(Batch batch);
	
	public abstract List<BatchDO> getAll();

}