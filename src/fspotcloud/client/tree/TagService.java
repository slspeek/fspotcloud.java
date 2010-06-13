package fspotcloud.client.tree;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fspotcloud.shared.tag.TagNode;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("tag")
public interface TagService extends RemoteService {
	List<TagNode> loadTagTree();
}
