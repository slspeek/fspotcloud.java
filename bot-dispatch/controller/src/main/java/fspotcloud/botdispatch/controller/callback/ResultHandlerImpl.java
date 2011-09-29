package fspotcloud.botdispatch.controller.callback;

import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.botdispatch.model.api.Command;

public class ResultHandlerImpl {

	final private Result result;
	final private Command command;
	final private Injector injector;

	@Inject
	public ResultHandlerImpl(@Assisted Result result,
			@Assisted Command command, Injector injector) {
		super();
		this.result = result;
		this.command = command;
		this.injector = injector;
	}

	public void callback() {
		AsyncCallback<Result> callback = command.getCallback();
		if (injector != null) {
			injector.injectMembers(callback);
		}
		callback.onSuccess(result);
	}

}
