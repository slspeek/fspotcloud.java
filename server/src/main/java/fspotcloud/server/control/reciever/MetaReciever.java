package fspotcloud.server.control.reciever;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

public class MetaReciever {

	private static final Logger log = Logger.getLogger(MetaReciever.class
			.getName());

	private final PeerDatabases defaultPeer;
	private final Queue defaultQueue;

	@Inject
	public MetaReciever(PeerDatabases defaultPeer, @Named("default") Queue defaultQueue) {
		this.defaultPeer = defaultPeer;
		this.defaultQueue = defaultQueue;
	}

	public int recieveMetaData(int count) {
		log.info("Recieved count " + count);
		PeerDatabase p = defaultPeer.get();
		int previousCount = p.getPeerPhotoCount();
		TaskOptions options = withUrl("/control/task/photoData").param(
				"offset", String.valueOf(previousCount)).param("limit",
				String.valueOf(count - previousCount));
		defaultQueue.add(options);
		p.setPeerPhotoCount(count);
		defaultPeer.save(p);
		return 0;
	}


}
