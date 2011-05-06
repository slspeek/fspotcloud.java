package fspotcloud.client.main;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.view.BasePlace;

public interface Navigator {
	void goEnd(boolean first, BasePlace place);
	void go(boolean forward, BasePlace place);
	void canGo(boolean forward, BasePlace place, AsyncCallback<Boolean> callback);
	
}
