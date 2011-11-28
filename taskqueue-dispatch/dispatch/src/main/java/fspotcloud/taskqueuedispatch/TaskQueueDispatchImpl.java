package fspotcloud.taskqueuedispatch;

import org.apache.commons.lang.SerializationUtils;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.Queue;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class TaskQueueDispatchImpl implements TaskQueueDispatch {

	private final Queue queue;

	@Inject
	public TaskQueueDispatchImpl(@Named("dispatch") Queue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public <A extends Action<R>, R extends Result> void execute(A action,
			SerializableAsyncCallback<R> callback) {
		AsyncCommand<A, R> command = new AsyncCommand<A, R>(callback, action);
		byte[] payload = SerializationUtils.serialize(command);
		TaskOptions taskOptions = TaskOptions.Builder.withUrl(
				"/taskqueue/dispatch").payload(payload, "image/jpeg");
		queue.add(taskOptions);
	}

	@Override
	public Queue getQueue() {
		return queue;
	}

	@Override
	public <A extends Action<R>, R extends Result> void execute(A action) {
		NullCallback<R> nullCallback = new NullCallback<R>();
		execute(action, nullCallback);
	}
}
