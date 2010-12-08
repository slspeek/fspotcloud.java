package fspotcloud.server.util;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.util.Map;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskHandle;
import com.google.appengine.api.labs.taskqueue.TaskOptions;
import com.google.inject.Inject;

public class TaskScheduler {
	
	private Queue defaultQueue;

	@Inject
	public TaskScheduler(Queue defaultQueue) {
		this.defaultQueue = defaultQueue;
	}
	
	public TaskHandle schedule(String url, Map<String, String> params) {
		TaskOptions options = url(url);
		for (String key: params.keySet()) {
			options.param(key, params.get(key));
		}
		return defaultQueue.add(options);
	}

}
