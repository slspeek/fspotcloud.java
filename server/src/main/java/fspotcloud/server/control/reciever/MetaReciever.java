package fspotcloud.server.control.reciever;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.util.logging.Logger;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.inject.Inject;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

public class MetaReciever {
	
	private static final Logger log = Logger.getLogger(MetaReciever.class
			.getName());
	 
	private final PeerDatabases defaultPeer;
	
	@Inject
	public MetaReciever(PeerDatabases defaultPeer) {
		this.defaultPeer = defaultPeer;
	}
	
	public int recieveMetaData(int count) {
		log.info("Recieved count " + count);
		PeerDatabase p = defaultPeer.get();
		int previousCount = p.getPeerPhotoCount();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/control/task/photoData").param("offset", String.valueOf(previousCount))
				.param("limit", String.valueOf(count - previousCount)));
		p.setPeerPhotoCount(count);
		defaultPeer.save(p);
		return 0;
	}

}
