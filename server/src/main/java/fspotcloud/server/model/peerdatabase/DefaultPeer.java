package fspotcloud.server.model.peerdatabase;

import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;

public class DefaultPeer {
	private static final Logger log = Logger
	.getLogger(DefaultPeer.class.getName());

	private final PersistenceManager pm;
	
	@Inject
	public DefaultPeer(PersistenceManager pm) {
		this.pm = pm;
	}
	
	public PeerDatabase get() {
		PeerDatabase peerDatabase;
		try {
			peerDatabase = pm.getObjectById(PeerDatabase.class, "1");
		} catch(JDOObjectNotFoundException firstTime) {
			log.info("Default peer not found, creating one.");
			peerDatabase = new PeerDatabase();
			peerDatabase.setName("1");
			peerDatabase.setCount(0);
			peerDatabase.setTagCount(0);
			peerDatabase.setPeerName("No given name");
			pm.makePersistent(peerDatabase);
		}
		return peerDatabase;
	}
	
	public void save(PeerDatabase pd) {
		log.info("Saving default peer with count: " + pd.getCount());
		pm.makePersistent(pd);
	}
}
