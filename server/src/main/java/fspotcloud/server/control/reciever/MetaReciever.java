package fspotcloud.server.control.reciever;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.inject.Inject;

import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;

public class MetaReciever {
	
	 
	private final DefaultPeer defaultPeer;
	
	@Inject
	public MetaReciever(DefaultPeer defaultPeer) {
		this.defaultPeer = defaultPeer;
	}
	
	public int recieveMetaData(int count) {
		PeerDatabase p = defaultPeer.get();
		int previousCount = p.getCount();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/control/task/photoData").param("offset", String.valueOf(previousCount))
				.param("limit", String.valueOf(count - previousCount)));
		p.setCount(count);
		defaultPeer.save(p);
		return 0;
	}

}