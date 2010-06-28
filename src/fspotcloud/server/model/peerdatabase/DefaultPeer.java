package fspotcloud.server.model.peerdatabase;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import fspotcloud.server.util.PMF;

public class DefaultPeer {
	
	public static PeerDatabase get() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
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
		} finally {
			pm.close();
		}
		return peerDatabase;
	}

}
