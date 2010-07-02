package fspotcloud.server.control.reciever;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;

import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.util.PMF;

public class MetaReciever {
	public int recieveMetaData(int count) {
		PeerDatabase p = DefaultPeer.get();
		int previousCount = p.getCount();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/control/task/photoData").param("offset", String.valueOf(previousCount))
				.param("limit", String.valueOf(count - previousCount)));
		p.setCount(count);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		return 0;
	}

}
