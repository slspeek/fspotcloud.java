package fspotcloud.server.model.peerdatabase;

import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import fspotcloud.server.admin.task.PhotoDeleteTaskServlet;
import fspotcloud.server.util.PMF;

public class DefaultPeer {
	private static final Logger log = Logger
	.getLogger(DefaultPeer.class.getName());
	
	public static PeerDatabase get(PersistenceManager pm) {
		PeerDatabase peerDatabase;
		try {
			peerDatabase = pm.getObjectById(PeerDatabase.class, "1");
		} catch(JDOObjectNotFoundException firstTime) {
			peerDatabase = new PeerDatabase();
			peerDatabase.setName("1");
			peerDatabase.setCount(0);
			peerDatabase.setTagCount(0);
			peerDatabase.setPeerName("No given name");
			pm.makePersistent(peerDatabase);
		}
		return peerDatabase;
	}
	
	public static PeerDatabase get() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return get(pm);
	}

	public static void save(PeerDatabase pd, PersistenceManager pm) {
		log.info("Saving default peer with count: " + pd.getCount());
		pm.makePersistent(pd);
		pm.close();
	}
}
