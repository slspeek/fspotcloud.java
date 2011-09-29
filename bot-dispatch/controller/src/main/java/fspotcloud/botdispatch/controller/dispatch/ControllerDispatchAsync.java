package fspotcloud.botdispatch.controller.dispatch;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.botdispatch.model.api.Commands;

public class ControllerDispatchAsync implements DispatchAsync{

	private final Commands commandManager;
	
	@Inject
	public ControllerDispatchAsync(Commands commandManager) {
		super();
		this.commandManager = commandManager;
	}
	public <A extends Action<R>, R extends Result> void execute(A action,
			AsyncCallback<R> callback) {
		commandManager.createAndSave(action, callback);
	}

}