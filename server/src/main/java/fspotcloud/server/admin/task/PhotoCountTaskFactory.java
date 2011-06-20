package fspotcloud.server.admin.task;

import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Batches;
import fspotcloud.server.model.api.Photos;

public class PhotoCountTaskFactory {

	private final Batches batchManager;
	private final Photos photoManager;
	
	private final int maxCount; 
	@Inject
	public PhotoCountTaskFactory(Batches batchManager, Photos photoManager,  @Named("maxCount") int maxCount ) {
		this.photoManager = photoManager;
		this.batchManager = batchManager;
		this.maxCount = maxCount;
	}

	public PhotoCountTask getTask(Batch batch, int countUpToNow,
			Date lastSeenDate) {
		return new PhotoCountTask(this, batch, photoManager, countUpToNow,
				lastSeenDate, maxCount);
	}

	public PhotoCountTask newTask() {
		Batch batch = batchManager.create("PhotoCountTask");
		return getTask(batch, 0, new Date(0l));
	}
}
