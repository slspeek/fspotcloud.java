package fspotcloud.botdispatch.controller.callback;

import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;

public class ErrorHandlerImpl {

	final private Throwable error;
	final private Command command;
	final private Injector injector;
	final private Commands commandManager;

	@Inject
	public ErrorHandlerImpl(@Assisted Throwable error, @Assisted Command command, Injector injector,
			Commands commandManager) {
		super();
		this.error = error;
		this.command = command;
		this.injector = injector;
		this.commandManager = commandManager;
	}


	public void onError() {
		AsyncCallback<Result> callback = command.getCallback();
		if (injector != null) {
			injector.injectMembers(callback);
		}
		callback.onFailure(error);
	}

}
