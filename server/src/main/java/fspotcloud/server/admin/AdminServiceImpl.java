package fspotcloud.server.admin;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.rpc.AdminService;
import fspotcloud.server.control.Scheduler;
import fspotcloud.server.model.api.Batches;
import fspotcloud.server.model.batch.BatchDO;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.model.tag.Tag;
import fspotcloud.server.model.tag.TagManager;
import fspotcloud.shared.admin.BatchInfo;

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
	private Batches batchManager;
	@Inject
	private TagManager tagManager;
	@Inject
	private DefaultPeer defaultPeer;
	
	@Inject
	private Scheduler scheduler;

	@Override
	public long deleteAllPhotos() {
		BatchDO batch = new BatchDO("deleteAllPhotos");
		long batchId = batchManager.save(batch);

		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/admin/task/photoDelete").param("deleteCount", "0")
				.param("batchId", String.valueOf(batchId)));
		return batchId;
	}

	@Override
	public void deleteAllTags() {
		tagManager.deleteAll();
	}

	@Override
	public int getPhotoCount() {
		PeerDatabase pd = defaultPeer.get();
		return pd.getCount();
	}

	@Override
	public int getTagCount() {
		PeerDatabase pd = defaultPeer.get();
		return pd.getTagCount();
	}

	@Override
	public void importTags() {
		scheduler.schedule("sendTagData", Collections.EMPTY_LIST);
	}

	@Override
	public long getServerPhotoCount() {
		BatchDO batch = new BatchDO("getServerPhotoCount");
		long batchId = batchManager.save(batch);
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/admin/task/photoCount").param("minDate", "0").param(
				"count", "0").param("batchId", String.valueOf(batchId)));
		return batchId;
	}

	@Override
	public BatchInfo getBatchInfo(long batchId) {
		BatchInfo info = batchManager.getBatchInfo(batchId);
		return info;
	}

	@Override
	public void update() {
		scheduler.schedule("sendMetaData", Collections.EMPTY_LIST);
	}

	@Override
	public long tagViewablePhotos(String tagId) {
		BatchDO batch = new BatchDO("tagViewablePhotos");
		long batchId = batchManager.save(batch);
		// clear the cached photo names
		Tag tag = tagManager.getById(tagId);
		tag.setCachedPhotoList(new ArrayList<String>());
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/main/task/tagView").param("tagId", tagId).param(
				"minDate", "0").param("batchId", String.valueOf(batchId)));
		return batchId;
	}
}
