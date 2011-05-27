package fspotcloud.server.admin;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.util.Collections;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.rpc.AdminService;
import fspotcloud.server.control.Scheduler;
import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Batches;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.admin.BatchInfo;
import fspotcloud.shared.admin.MetaDataInfo;
import fspotcloud.shared.photo.PhotoInfo;
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
	private Tags tagManager;
	@Inject
	private PeerDatabases defaultPeer;
	
	@Inject
	private Scheduler scheduler;

	@Override
	public long deleteAllPhotos() {
		Batch batch = batchManager.create("deleteAllPhotos");
		long batchId = batchManager.save(batch);

		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(withUrl("/admin/task/photoDelete").param("deleteCount", "0")
				.param("batchId", String.valueOf(batchId)));
		return batchId;
	}

	@Override
	public void deleteAllTags() {
		tagManager.deleteAll();
	}

	@Override
	public int getPeerPhotoCount() {
		PeerDatabase pd = defaultPeer.get();
		return pd.getPeerPhotoCount();
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
		Batch batch = batchManager.create("getServerPhotoCount");
		long batchId = batchManager.save(batch);
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(withUrl("/admin/task/photoCount").param("minDate", "0").param(
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
		Batch batch = batchManager.create("tagViewablePhotos");
		long batchId = batchManager.save(batch);
		// clear the cached photo names
		Tag tag = tagManager.getById(tagId);
		tag.setCachedPhotoList(new TreeSet<PhotoInfo>());
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(withUrl("/main/task/tagView").param("tagId", tagId).param(
				"minDate", "0").param("batchId", String.valueOf(batchId)));
		return batchId;
	}

	@Override
	public void importTag(String tagId) {
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(withUrl("/control/task/imageData").param("minDate", "0").param(
				"maxCount", "10000").param("tagId", String.valueOf(tagId)));
		
	}

	@Override
	public MetaDataInfo getMetaData() {
		PeerDatabase peerDatabase = defaultPeer.get();
		MetaDataInfo dataInfo = new MetaDataInfo();
		dataInfo.setInstanceName(peerDatabase.getPeerName());
		dataInfo.setPeerLastSeen(peerDatabase.getPeerLastContact());
		dataInfo.setPeerPhotoCount(peerDatabase.getPeerPhotoCount());
		dataInfo.setTagCount(peerDatabase.getTagCount());
		return dataInfo;
	}
}
