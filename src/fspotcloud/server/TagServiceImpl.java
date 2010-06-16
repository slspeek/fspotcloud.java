package fspotcloud.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fspotcloud.client.tree.TagService;
import fspotcloud.server.tag.TagReader;
import fspotcloud.server.tag.TreeBuilder;
import fspotcloud.shared.tag.TagNode;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TagServiceImpl extends RemoteServiceServlet implements TagService {

	public List<TagNode> loadTagTree() {
		List<TagNode> tags = TagReader.getTags();
		TreeBuilder builder = new TreeBuilder(tags);
		return builder.getRoots();
	}
}
