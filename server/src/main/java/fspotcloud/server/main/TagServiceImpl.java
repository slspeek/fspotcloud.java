package fspotcloud.server.main;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;

import fspotcloud.client.tree.TagService;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.model.tag.Tag;
import fspotcloud.server.model.tag.TagReader;
import fspotcloud.server.model.tag.TreeBuilder;
import fspotcloud.shared.tag.TagNode;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TagServiceImpl extends RemoteServiceServlet implements TagService {

	@Inject
	private TagReader tagManager;
	@Inject
	private DefaultPeer defaultPeer;
	
	
	public List<TagNode> loadTagTree() {
		PeerDatabase p = defaultPeer.get();
		if (p.getCachedTagTree() != null) {
			return p.getCachedTagTree();
		} else {
			List<TagNode> tags = tagManager.getTags();
			TreeBuilder builder = new TreeBuilder(tags);
			List<TagNode> tree = builder.getRoots();
			p.setCachedTagTree(tree);
			defaultPeer.save(p);
			return tree;
		}
	}

	@Override
	public List<String> keysForTag(String tagId) {
		Tag tag = tagManager.getById(tagId);
		List<String> result = new ArrayList<String>();
		result.addAll(tag.getCachedPhotoList());
		return result;
	}
}
