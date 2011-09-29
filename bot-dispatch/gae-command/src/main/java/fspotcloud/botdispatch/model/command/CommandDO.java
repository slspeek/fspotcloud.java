package fspotcloud.botdispatch.model.command;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.datastore.Blob;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Command;

@PersistenceCapable
public class CommandDO implements Command {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent(serialized = "true")
	private Object action;

	@Persistent(serialized = "true")
	private Object callback;

	@Persistent 
	private Blob callbackBlob;
	
	@Persistent 
	private Blob actionBlob;
	
	@Persistent
	private Date ctime;

	public CommandDO(Action<?> action, AsyncCallback<? extends Result> callback) throws IOException {
		ctime = new Date();
		this.action = action;
		this.callback = callback;
		
		// Serialize to a byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();

		// Get the bytes of the serialized object
		callbackBlob = new Blob(bos.toByteArray());

		// Serialize to a byte array
		bos = new ByteArrayOutputStream();
		out = new ObjectOutputStream(bos);
		out.writeObject(action);
		out.close();

		// Get the bytes of the serialized object
		actionBlob = new Blob(bos.toByteArray());


	}

	public Long getId() {
		return id;
	}

	public Date getCtime() {
		return ctime;
	}

	@Override
	public Action<?> getAction() {
		if (action == null) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new ByteArrayInputStream(
						callbackBlob.getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				action = in.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return (Action<?>) action;
	}

	@Override
	public AsyncCallback<Result> getCallback() {
		if (callback == null) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new ByteArrayInputStream(
						callbackBlob.getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				callback = (AsyncCallback<Result>) in.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (AsyncCallback<Result>) callback;
	}

	@Override
	public String toString() {
		String result = String.valueOf(action) + " : "
				+ String.valueOf(callback);
		return result;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
