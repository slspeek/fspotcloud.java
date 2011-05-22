package fspotcloud.client.main;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.place.BasePlace;

public interface Navigator {
	void goEnd(boolean first, BasePlace place);

	void goEnd(boolean first);

	void go(boolean forward, BasePlace place);

	void go(boolean forward);

	void canGo(boolean forward, BasePlace place, AsyncCallback<Boolean> callback);

	void canGo(boolean forward, AsyncCallback<Boolean> callback);
}
