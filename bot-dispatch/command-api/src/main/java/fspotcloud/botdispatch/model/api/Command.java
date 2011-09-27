package fspotcloud.botdispatch.model.api;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Command {

	Key getKey();
	Action<?> getAction();
	AsyncCallback<Result> getCallback();
	Date getCtime();

}