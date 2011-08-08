package fspotcloud.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.shared.admin.MetaDataInfo;

public interface AdminServiceAsync {

	void deleteAllPhotos(AsyncCallback<Long> callback);

	void deleteAllTags(AsyncCallback<Void> callback);

	void countPhotos(AsyncCallback<Void> callback);

	void update(AsyncCallback<Void> callback);

	void importTag(String tagId, AsyncCallback<Void> callback);
	
	void getMetaData(AsyncCallback<MetaDataInfo> callback); 
	
	void importImageData(AsyncCallback<Void> callback);
	
	void resetPeerPhotoCount(AsyncCallback<Void> callback);
}
