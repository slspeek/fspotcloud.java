package com.googlecode.fspotcloud.client.data;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.fspotcloud.shared.tag.TagNode;


public interface DataManager {

	void getTagNode(String id, AsyncCallback<TagNode> callback);

	void getTagTree(final AsyncCallback<List<TagNode>> callback);
	
	void getAdminTagTree(final AsyncCallback<List<TagNode>> callback);

	void getAdminTagNode(String tagId, AsyncCallback<TagNode> asyncCallback);

}