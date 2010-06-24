package fspotcloud.client.admin;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminServiceAsync {

	void deleteAllPhotos(AsyncCallback<Void> callback);

	void deleteAllTags(AsyncCallback<Void> callback);

	void getPhotoCount(AsyncCallback<Integer> callback);

	void getTagCount(AsyncCallback<Integer> callback);

	void importTags(AsyncCallback<Void> callback);

}
