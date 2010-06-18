package fspotcloud.server;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fspotcloud.client.tree.TagService;
import fspotcloud.server.peerdatabase.DefaultPeer;
import fspotcloud.server.peerdatabase.PeerDatabase;
import fspotcloud.server.tag.TagReader;
import fspotcloud.server.tag.TreeBuilder;
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
