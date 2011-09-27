package fspotcloud.botdispatch.model.command;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Command;

public class NullCommand implements Command {

	public static Command INSTANCE = new NullCommand();
	@Override
	public Key getKey() {
		return null;
	}

	@Override
	public Action<?> getAction() {
		return null;
	}

	@Override
	public AsyncCallback<Result> getCallback() {
		return null;
	}

	@Override
	public Date getCtime() {
		return null;
	}
}
