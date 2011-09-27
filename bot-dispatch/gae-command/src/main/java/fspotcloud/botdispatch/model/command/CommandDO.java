package fspotcloud.botdispatch.model.command;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Command;

@PersistenceCapable
public class CommandDO implements Command {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent(serialized = "true")
	private Object action;

	@Persistent(serialized = "true")
	private Object callback;

	@Persistent
	private Date ctime;
	
	public CommandDO(Action<?> action, AsyncCallback<? extends Result> callback) {
		ctime = new Date();
		this.action = action;
		this.callback = callback;
	}

	public Key getKey() {
		return key;
	}

	public Date getCtime() {
		return ctime;
	}

	@Override
	public Action<?> getAction() {
		return (Action<?>) action;
	}

	@Override
	public AsyncCallback<Result> getCallback() {
		return (AsyncCallback<Result>) callback;
	}

}
