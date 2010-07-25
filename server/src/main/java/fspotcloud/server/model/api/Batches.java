package fspotcloud.server.model.api;

import java.util.List;

import fspotcloud.shared.admin.BatchInfo;

public interface Batches {

	public abstract BatchInfo getBatchInfo(long batchId);

	public abstract Batch getById(long batchId);

	public abstract long save(Batch batch);
	
	public abstract void delete(Batch batch);
	
	public abstract List<Batch> getAll();

}