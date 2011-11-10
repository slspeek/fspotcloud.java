package fspotcloud.server.control.task.tagimport;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import com.google.appengine.api.taskqueue.Queue;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public class DelayedDataScheduler implements DataScheduler {

	final private Queue queue;
	final private String kind;
	
	@Inject
	public DelayedDataScheduler(@Named("default") Queue queue, @Assisted String kind) {
		this.kind = kind;
		this.queue = queue;
	}

	
	@Override
	public void scheduleDataImport(int offset, int limit) {
		queue.add(withUrl("/control/task/data").param("offset",
				String.valueOf(offset)).param("limit",
				String.valueOf(limit)).param("kind", kind));
		
	}
	
}
