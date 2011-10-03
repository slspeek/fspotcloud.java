package fspotcloud.botdispatch.testserver;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;

interface SeriablizableAsyncCallback<T> extends Serializable, AsyncCallback<T> {}