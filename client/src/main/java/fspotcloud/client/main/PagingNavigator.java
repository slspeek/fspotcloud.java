package fspotcloud.client.main;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.place.BasePlace;

public interface PagingNavigator {
	void getPageCount(String tagId, int pageSize,
			AsyncCallback<Integer> callback);

	void getPage(String tagId, int pageSize, int pageNumber,
			AsyncCallback<List<BasePlace>> callback);

	void getPage(String tagId, String photoId, int pageSize,
			AsyncCallback<List<BasePlace>> callback);
}
