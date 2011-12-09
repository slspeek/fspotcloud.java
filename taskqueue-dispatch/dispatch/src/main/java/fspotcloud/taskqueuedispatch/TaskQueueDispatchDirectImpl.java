package fspotcloud.taskqueuedispatch;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.taskqueue.Queue;
import com.google.inject.Injector;

public class TaskQueueDispatchDirectImpl implements TaskQueueDispatch {

	final private Dispatch dispatch;
	final private Injector injector;

	@Inject
	public TaskQueueDispatchDirectImpl(Dispatch dispatch, Injector injector) {
		super();
		this.dispatch = dispatch;
		this.injector = injector;
	}

	@Override
	public <A extends Action<R>, R extends Result> void execute(A action,
			SerializableAsyncCallback<R> callback) {
		injector.injectMembers(callback);
		try {
			R result = dispatch.execute(action);
			callback.onSuccess(result);
		} catch (DispatchException e) {
			callback.onFailure(e);
		}
	}

	@Override
	public Queue getQueue() {
		return null;
	}

	@Override
	public <A extends Action<R>, R extends Result> void execute(A action) {
		NullCallback<R> nullCallback = new NullCallback<R>();
		execute(action, nullCallback);
	}

}
