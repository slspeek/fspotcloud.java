package fspotcloud.server.control.reciever;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.util.logging.Logger;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.inject.Inject;

import fspotcloud.server.model.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;

public class MetaReciever {
	
	private static final Logger log = Logger.getLogger(MetaReciever.class
			.getName());
	 
	private final PeerDatabaseManager defaultPeer;
	
	@Inject
	public MetaReciever(PeerDatabaseManager defaultPeer) {
		this.defaultPeer = defaultPeer;
	}
	
	public int recieveMetaData(int count) {
		log.info("Recieved count " + count);
		PeerDatabaseDO p = defaultPeer.get();
		int previousCount = p.getCount();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/control/task/photoData").param("offset", String.valueOf(previousCount))
				.param("limit", String.valueOf(count - previousCount)));
		p.setCount(count);
		defaultPeer.save(p);
		return 0;
	}

}
