package fspotcloud.server.main;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fspotcloud.client.tree.TagService;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.model.tag.TagReader;
import fspotcloud.server.model.tag.TreeBuilder;
import fspotcloud.server.util.PMF;
import fspotcloud.shared.tag.TagNode;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TagServiceImpl extends RemoteServiceServlet implements TagService {

	public List<TagNode> loadTagTree() {
		PeerDatabase p = DefaultPeer.get();
		if (p.getCachedTagTree() != null) {
			return p.getCachedTagTree();
		} else {
			List<TagNode> tags = TagReader.getTags();
			TreeBuilder builder = new TreeBuilder(tags);
			List<TagNode> tree = builder.getRoots();
			p.setCachedTagTree(tree);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(p);
			} finally {
				pm.close();
			}
			return tree;
		}
	}
}
