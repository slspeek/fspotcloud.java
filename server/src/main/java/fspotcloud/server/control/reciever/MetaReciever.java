package fspotcloud.server.control.reciever;

import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.server.control.task.DataScheduler;
import fspotcloud.server.control.task.DataSchedulerFactory;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

public class MetaReciever {

	private static final Logger log = Logger.getLogger(MetaReciever.class
			.getName());

	private final PeerDatabases defaultPeer;
	private final DataSchedulerFactory dataSchedulerFactory;
	
	@Inject
	public MetaReciever(PeerDatabases defaultPeer,
			DataSchedulerFactory dataSchedulerFactory) {
		super();
		this.defaultPeer = defaultPeer;
		this.dataSchedulerFactory = dataSchedulerFactory;
	}
	
	public int recieveMetaData(Object[] metaData) {
		int count = (Integer) metaData[0];
		int tagCount = (Integer) metaData[1];
		log.info("Recieved count " + count + " Tag count: " + tagCount);
		PeerDatabase p = defaultPeer.get();
		int previousCount = p.getPeerPhotoCount();
		DataScheduler scheduler = dataSchedulerFactory.get("Photo");
		scheduler.scheduleDataImport(previousCount,count - previousCount);
		p.setPeerPhotoCount(count);
		defaultPeer.save(p);
		return 0;
	}



}
