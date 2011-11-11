package fspotcloud.server.model.peerdatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.apache.commons.lang.SerializationUtils;

import net.sf.jsr107cache.Cache;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.tag.TagNode;

public class PeerDatabaseManager implements PeerDatabases {
	private static final String DEFAULT_PEER = "DEFAULT_PEER";

	private static final String DEFAULT_PEER_ID = "1";

	private static final Logger log = Logger
			.getLogger(PeerDatabaseManager.class.getName());

	final private Provider<PersistenceManager> pmProvider;
	final private Cache cache;

	@Inject
	public PeerDatabaseManager(Provider<PersistenceManager> pmProvider,
			Cache cache) {
		this.pmProvider = pmProvider;
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	public PeerDatabase get() {
		PeerDatabase peer;
		byte[] cachedPeer = (byte[]) cache.get(DEFAULT_PEER); 
		if (cachedPeer != null) {
			peer = (PeerDatabase)SerializationUtils.deserialize(cachedPeer);
		} else {
			peer = getJDOImpl();
			byte[] serializedPeer = SerializationUtils.serialize((Serializable) peer);
			cache.put(DEFAULT_PEER, serializedPeer);
		}
		return peer;
	}

	private PeerDatabase getJDOImpl() {
		PersistenceManager pm = pmProvider.get();
		PeerDatabaseDO attachedPeerDatabase, peerDatabase;
		try {
			attachedPeerDatabase = pm.getObjectById(PeerDatabaseDO.class,
					DEFAULT_PEER_ID);
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
			peerDatabase.setPeerPhotoCount(0);
			peerDatabase.setPhotoCount(0);
			peerDatabase.setTagCount(0);
			peerDatabase.setPeerName("No given name");
			peerDatabase.setPeerLastContact(new Date(0));
			peerDatabase.setCachedImportedTags(new ArrayList<String>());
			pm.makePersistent(peerDatabase);

		} finally {
			pm.close();
		}
		return peerDatabase;
	}

	public void save(PeerDatabase pd) {
		byte[] serializedPeer = SerializationUtils.serialize((Serializable) pd);
		cache.put(DEFAULT_PEER, serializedPeer);
		
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
