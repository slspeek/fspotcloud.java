package fspotcloud.server.util;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.util.Map;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.inject.Inject;

public class TaskScheduler {
	
	private Queue defaultQueue;

	@Inject
	public TaskScheduler(Queue defaultQueue) {
		this.defaultQueue = defaultQueue;
	}
	
	public TaskHandle schedule(String url, Map<String, String> params) {
		TaskOptions options = withUrl(url);
		for (String key: params.keySet()) {
			options.param(key, params.get(key));
		}
		return defaultQueue.add(options);
	}

}
