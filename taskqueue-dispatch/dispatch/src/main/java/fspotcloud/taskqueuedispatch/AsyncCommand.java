package fspotcloud.taskqueuedispatch;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class AsyncCommand<A extends Action<R>, R extends Result> implements Serializable {

	private final SerializableAsyncCallback<R> callback;
	private final Action<R> action;
	
	public AsyncCommand(SerializableAsyncCallback<R> callback, Action<R> action) {
		super();
		this.callback = callback;
		this.action = action;
	}

	public SerializableAsyncCallback<R> getCallback() {
		return callback;
	}

	public Action<R> getAction() {
		return action;
	}
	
}
