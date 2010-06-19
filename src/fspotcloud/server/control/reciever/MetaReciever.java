package fspotcloud.server.control.reciever;

import javax.jdo.PersistenceManager;

import fspotcloud.server.peerdatabase.DefaultPeer;
import fspotcloud.server.peerdatabase.PeerDatabase;
import fspotcloud.server.util.PMF;

public class MetaReciever {
	public int recievePhotoData(int count) {
		PeerDatabase p = DefaultPeer.get();
		int previousCount = p.getCount();
		//schedule
		p.setCount(count);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistentAll(p);
		} finally {
			pm.close();
		}
		return 0;
	}

}
