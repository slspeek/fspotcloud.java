package fspotcloud.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("admin")
public interface AdminService extends RemoteService {
	long deleteAllPhotos();

	void deleteAllTags();
	
	void resetPeerPhotoCount();

	void countPhotos();

	void update();

	void importTag(String tagId);
	
	void importImageData();
}
