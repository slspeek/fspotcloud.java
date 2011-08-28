package fspotcloud.server.admin;

import java.util.Collections;
import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.rpc.AdminService;
import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.server.mapreduce.MapReduceUtil;
import fspotcloud.server.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Singleton
public class AdminServiceImpl extends RemoteServiceServlet implements
		AdminService {

	private static final Logger log = Logger.getLogger(AdminServiceImpl.class
			.getName());

	@Inject
	private Tags tagManager;
	@Inject
	private Commands commandManager;
	@Inject
	private PeerDatabases defaultPeer;

	@Inject
	private SchedulerInterface scheduler;
	
	@Override
	public long deleteAllPhotos() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = MapReduceUtil.buildStartJob("Delete All Mapper", "PhotoDO");
		queue.add(task);
		return 0;
	}

	@Override
	public void deleteAllTags() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = MapReduceUtil.buildStartJob("Delete All Mapper", "TagDO");
		queue.add(task);
	}

	@Override
	public void countPhotos() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = MapReduceUtil.buildStartJob("Entity Counter Mapper", "PhotoDO");
		queue.add(task);
	}

	
	@Override
	public void update() {
		scheduler.schedule("sendMetaData", Collections.EMPTY_LIST);
	}


	@Override
	public void importTag(String tagId) {
		Tag tag = tagManager.getById(tagId);
		tag.setImportIssued(true);
		tagManager.save(tag);
		PeerDatabase pd = defaultPeer.get();
		pd.getCachedImportedTags().add(tagId);
		defaultPeer.save(pd);
	}
	
	@Override
	public void importImageData() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = MapReduceUtil.buildStartJob("Image Data Import Mapper", "PhotoDO");
		queue.add(task);
		log.info("Image Data Mapper scheduled.");
	}

	@Override
	public void resetPeerPhotoCount() {
		PeerDatabase peerDatabase = defaultPeer.get();
		peerDatabase.setPeerPhotoCount(0);
		defaultPeer.save(peerDatabase);
	}
}
