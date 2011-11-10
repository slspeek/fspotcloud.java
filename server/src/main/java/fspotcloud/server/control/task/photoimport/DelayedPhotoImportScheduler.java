package fspotcloud.server.control.task.photoimport;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import com.google.appengine.api.taskqueue.Queue;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public class DelayedPhotoImportScheduler implements PhotoImportScheduler {

	final private Queue queue;

	@Inject
	public DelayedPhotoImportScheduler(@Named("default") Queue queue) {
		this.queue = queue;
	}

	@Override
	public void schedulePhotoImport(String tagId, String minKey, int offset,
			int limit) {
		queue.add(withUrl("/control/task/photo_import").param("tagId", tagId)
				.param("minKey", minKey)
				.param("offset", String.valueOf(offset))
				.param("limit", String.valueOf(limit)));
	}

}
