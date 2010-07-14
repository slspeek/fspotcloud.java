package fspotcloud.server.main;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fspotcloud.client.tree.TagService;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.model.tag.Tag;
import fspotcloud.server.model.tag.TagReader;
import fspotcloud.server.model.tag.TreeBuilder;
import fspotcloud.server.util.PMF;
import fspotcloud.shared.tag.TagNode;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TagServiceImpl extends RemoteServiceServlet implements TagService {

	private final TagReader tagManager = new TagReader();
	
	
	public List<TagNode> loadTagTree() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		PeerDatabase p = DefaultPeer.get(pm);
		if (p.getCachedTagTree() != null) {
			return p.getCachedTagTree();
		} else {
			List<TagNode> tags = TagReader.getTags();
			TreeBuilder builder = new TreeBuilder(tags);
			List<TagNode> tree = builder.getRoots();
			p.setCachedTagTree(tree);
			DefaultPeer.save(p, pm);
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
