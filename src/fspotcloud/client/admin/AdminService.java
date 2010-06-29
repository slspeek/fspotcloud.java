package fspotcloud.client.admin;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("admin")
public interface AdminService extends RemoteService {
	void deleteAllPhotos();
	void deleteAllTags();
	int getPhotoCount();
	long getServerPhotoCount();
	int getTagCount();
	void importTags();
	
	
}
