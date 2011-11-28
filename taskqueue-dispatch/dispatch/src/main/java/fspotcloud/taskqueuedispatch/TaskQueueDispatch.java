package fspotcloud.taskqueuedispatch;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.taskqueue.Queue;

public interface TaskQueueDispatch {

	<A extends Action<R>, R extends Result> void execute(A action, SerializableAsyncCallback<R> callback );

	Queue getQueue();

	<A extends Action<R>, R extends Result> void execute(A action);
}
