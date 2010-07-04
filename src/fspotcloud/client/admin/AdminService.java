package fspotcloud.client.admin;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fspotcloud.shared.admin.BatchInfo;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("admin")
public interface AdminService extends RemoteService {
	long deleteAllPhotos();
	void deleteAllTags();
	int getPhotoCount();
	long getServerPhotoCount();
	int getTagCount();
	void importTags();
	void update();
	long tagViewablePhotos(String tagId);
	BatchInfo getBatchInfo(long batchId); 
	
}
