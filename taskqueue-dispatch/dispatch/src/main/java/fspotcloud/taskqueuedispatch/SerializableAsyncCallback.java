package fspotcloud.taskqueuedispatch;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SerializableAsyncCallback<T> extends AsyncCallback<T>, Serializable{
	
}
