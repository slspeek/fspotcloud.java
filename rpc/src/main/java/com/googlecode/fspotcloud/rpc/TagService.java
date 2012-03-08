package com.googlecode.fspotcloud.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.googlecode.fspotcloud.shared.tag.TagNode;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("tag")
public interface TagService extends RemoteService {
	List<TagNode> loadTagTree();
	
	List<TagNode> loadAdminTagTree();

	List<String> keysForTag(String tagId);
}
