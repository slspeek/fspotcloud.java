package fspotcloud.client.admin;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fspotcloud.shared.tag.TagNode;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("admin")
public interface AdminService extends RemoteService {
	void deleteAllPhotos();
	void deleteAllTags();
	int getPhotoCount();
	int getTagCount();
	void importTags();
	
	
}
