package fspotcloud.server.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.rpc.TagService;
import fspotcloud.server.model.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;
import fspotcloud.server.model.tag.Tag;
import fspotcloud.server.model.tag.TagManager;
import fspotcloud.server.model.tag.TreeBuilder;
import fspotcloud.shared.tag.TagNode;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Singleton
public class TagServiceImpl extends RemoteServiceServlet implements TagService {
	private static final Logger log = Logger.getLogger(TagServiceImpl.class
			.getName());
	
	@Inject
	private TagManager tagManager;
	@Inject
	private PeerDatabaseManager defaultPeer;
	
	
	public List<TagNode> loadTagTree() {
		PeerDatabaseDO p = defaultPeer.get();
		if (p.getCachedTagTree() != null) {
			log.info("Got the tree from cache HIT");
			return p.getCachedTagTree();
		} else {
			log.info("Missed the cache; building");
			List<TagNode> tags = tagManager.getTags();
			TreeBuilder builder = new TreeBuilder(tags);
			List<TagNode> tree = builder.getRoots();
			p.setCachedTagTree(tree);
			log.info("Builded, about to save");
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
