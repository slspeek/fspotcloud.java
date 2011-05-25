package fspotcloud.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.shared.tag.TagNode;

public interface TagServiceAsync {

	void loadTagTree(AsyncCallback<List<TagNode>> callback);
	
	void loadAdminTagTree(AsyncCallback<List<TagNode>> callback);

	void keysForTag(String tagId, AsyncCallback<List<String>> callback);

}
