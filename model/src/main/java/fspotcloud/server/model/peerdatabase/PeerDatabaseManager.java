package fspotcloud.server.model.peerdatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.tag.TagNode;

public class PeerDatabaseManager implements PeerDatabases {
	private static final Logger log = Logger
			.getLogger(PeerDatabaseManager.class.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public PeerDatabaseManager(Provider<PersistenceManager> pmProvider) {
		this.pmProvider = pmProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fspotcloud.server.model.peerdatabase.PeerDatabases#get()
	 */
	public PeerDatabase get() {
		PersistenceManager pm = pmProvider.get();
		PeerDatabaseDO attachedPeerDatabase, peerDatabase;
		try {
			attachedPeerDatabase = pm.getObjectById(PeerDatabaseDO.class, "1");
			peerDatabase = pm.detachCopy(attachedPeerDatabase);
			if (attachedPeerDatabase.getCachedTagTree() != null) {
				peerDatabase.setCachedTagTree(new ArrayList<TagNode>(
						attachedPeerDatabase.getCachedTagTree()));
			}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fspotcloud.server.model.peerdatabase.PeerDatabases#save(fspotcloud.server
	 * .model.peerdatabase.PeerDatabase)
	 */
	public void save(PeerDatabase pd) {
		PersistenceManager pm = pmProvider.get();
		try {
			log.info("Saving default peer with count: " + pd.getCount());
			pm.makePersistent(pd);
		} finally {
			pm.close();
		}
	}
}
