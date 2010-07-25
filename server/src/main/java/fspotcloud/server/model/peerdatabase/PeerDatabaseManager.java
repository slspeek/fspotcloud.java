package fspotcloud.server.model.peerdatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.shared.tag.TagNode;

public class PeerDatabaseManager {
	private static final Logger log = Logger.getLogger(PeerDatabaseManager.class
			.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public PeerDatabaseManager(Provider<PersistenceManager> pmProvider) {
		this.pmProvider = pmProvider;
	}

	public PeerDatabaseDO get() {
		PersistenceManager pm = pmProvider.get();
		PeerDatabaseDO attachedPeerDatabase, peerDatabase;
		try {
			attachedPeerDatabase = pm.getObjectById(PeerDatabaseDO.class, "1");
			peerDatabase = pm.detachCopy(attachedPeerDatabase);
			peerDatabase.setCachedTagTree(new ArrayList<TagNode>(attachedPeerDatabase
					.getCachedTagTree()));

		} catch (JDOObjectNotFoundException firstTime) {
			log.info("Default peer not found, creating one.");
			peerDatabase = new PeerDatabaseDO();
			peerDatabase.setName("1");
			peerDatabase.setCount(0);
			peerDatabase.setTagCount(0);
			peerDatabase.setPeerName("No given name");
			pm.makePersistent(peerDatabase);

		} finally {
			pm.close();
		}
		return peerDatabase;
	}

	public List<TagNode> getCachedTagTree() {
		PersistenceManager pm = pmProvider.get();
		PeerDatabaseDO peerDatabase;
		try {
			peerDatabase = pm.getObjectById(PeerDatabaseDO.class, "1");
			return peerDatabase.getCachedTagTree();
		} finally {
			pm.close();
		}
	}

	public void save(PeerDatabaseDO pd) {
		PersistenceManager pm = pmProvider.get();
		try {
			log.info("Saving default peer with count: " + pd.getCount());
			pm.makePersistent(pd);
		} finally {
			pm.close();
		}
	}
}
