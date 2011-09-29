package fspotcloud.botdispatch.model.api;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface Commands {

	Command createAndSave(Action<?> action, AsyncCallback<? extends Result> callback);

	Command popFirstCommand();
	
	Command getById(long callbackId);
	
	void save(Command cmd);
	
	int getCountUnderAThousend();

}