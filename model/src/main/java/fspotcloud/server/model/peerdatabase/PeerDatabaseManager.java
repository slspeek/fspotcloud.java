package fspotcloud.server.model.peerdatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.tag.TagNode;

public class PeerDatabaseManager implements PeerDatabases {
	private static final String DEFAULT_PEER_ID = "1";

	private static final Logger log = Logger
			.getLogger(PeerDatabaseManager.class.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public PeerDatabaseManager(Provider<PersistenceManager> pmProvider) {
		this.pmProvider = pmProvider;
	}

	public PeerDatabase get() {
		PersistenceManager pm = pmProvider.get();
		PeerDatabaseDO attachedPeerDatabase, peerDatabase;
		try {
			attachedPeerDatabase = pm.getObjectById(PeerDatabaseDO.class, DEFAULT_PEER_ID);
			peerDatabase = pm.detachCopy(attachedPeerDatabase);
			if (attachedPeerDatabase.getCachedTagTree() != null) {
				peerDatabase.setCachedTagTree(new ArrayList<TagNode>(
						attachedPeerDatabase.getCachedTagTree()));
			} else {
				peerDatabase.setCachedTagTree(null);
			}

		} catch (JDOObjectNotFoundException firstTime) {
			log.info("Default peer not found, creating one.");
			peerDatabase = new PeerDatabaseDO();
			peerDatabase.setName(DEFAULT_PEER_ID);
			peerDatabase.setCount(0);
			peerDatabase.setTagCount(0);
			peerDatabase.setPeerName("No given name");
			peerDatabase.setPeerLastContact(new Date(0));
			pm.makePersistent(peerDatabase);

		} finally {
			pm.close();
		}
		return peerDatabase;
	}

	public void save(PeerDatabase pd) {
		PersistenceManager pm = pmProvider.get();
		try {
			log.info("Saving default peer: " + pd);
			pm.makePersistent(pd);
		} finally {
			pm.close();
		}
	}
	
	public void touchPeerContact() {
		PeerDatabase dp = get();
		dp.setPeerLastContact(new Date());
		save(dp);
	}
	
}
