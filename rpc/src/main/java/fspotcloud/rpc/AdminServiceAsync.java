package fspotcloud.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.shared.admin.BatchInfo;
import fspotcloud.shared.admin.MetaDataInfo;

public interface AdminServiceAsync {

	void deleteAllPhotos(AsyncCallback<Long> callback);

	void deleteAllTags(AsyncCallback<Void> callback);

	void getPeerPhotoCount(AsyncCallback<Integer> callback);

	void getTagCount(AsyncCallback<Integer> callback);

	void importTags(AsyncCallback<Void> callback);

	void getServerPhotoCount(AsyncCallback<Long> callback);

	void getBatchInfo(long batchId, AsyncCallback<BatchInfo> callback);

	void update(AsyncCallback<Void> callback);

	void tagViewablePhotos(String tagId, AsyncCallback<Long> callback);
	
	void importTag(String tagId, AsyncCallback<Void> callback);
	
	void getMetaData(AsyncCallback<MetaDataInfo> callback); 
	
	void importImageData(AsyncCallback<Void> callback);

}
