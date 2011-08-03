package fspotcloud.server.control.task;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import com.google.appengine.api.taskqueue.Queue;
import com.google.inject.Inject;

public class DelayedPhotoDataScheduler implements PhotoDataScheduler {

	final private Queue queue;
	
	@Inject
	public DelayedPhotoDataScheduler(Queue queue) {
		super();
		this.queue = queue;
	}

	
	@Override
	public void schedulePhotoDataImport(int offset, int limit) {
		queue.add(withUrl("/control/task/photoData").param("offset",
				String.valueOf(offset)).param("limit",
				String.valueOf(limit)));
		
	}
	
}
