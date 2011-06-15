package fspotcloud.server.admin;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.util.Collections;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.rpc.AdminService;
import fspotcloud.server.control.SchedulerInterface;
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
	private SchedulerInterface scheduler;

	@Override
	public long deleteAllPhotos() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = buildStartJob("Delete All Mapper");
		addJobParam(task,
				"mapreduce.mapper.inputformat.datastoreinputformat.entitykind",
				"PhotoDO");
		queue.add(task);

		return 0;
	}

	private static TaskOptions buildStartJob(String jobName) {
		return TaskOptions.Builder.withUrl("/mapreduce/command/start_job")
				.method(Method.POST)
				.header("X-Requested-With", "XMLHttpRequest") // hack: we need
																// to fix
																// appengine-mapper
																// so we can
																// properly call
																// start_job
																// without need
																// to pretend to
																// be an
																// ajaxmethod
				.param("name", jobName);
	}

	private static void addJobParam(TaskOptions task, String paramName,
			String paramValue) {
		task.param("mapper_params." + paramName, paramValue);
	}

	private static void addJobParam(TaskOptions task, String paramName,
			long value) {
		addJobParam(task, paramName, Long.toString(value));
	}

	@Override
	public void deleteAllTags() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = buildStartJob("Delete All Mapper");
		addJobParam(task,
				"mapreduce.mapper.inputformat.datastoreinputformat.entitykind",
				"TagDO");
		queue.add(task);
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
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = buildStartJob("Entity Counter Mapper");
		addJobParam(task,
				"mapreduce.mapper.inputformat.datastoreinputformat.entitykind",
				"PhotoDO");
		queue.add(task);
		return 0;
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
		queue.add(withUrl("/main/task/tagView").param("tagId", tagId)
				.param("minDate", "0")
				.param("batchId", String.valueOf(batchId)));
		return batchId;
	}

	@Override
	public void importTag(String tagId) {
		Tag tag = tagManager.getById(tagId);
		tag.setImportIssued(true);
		tagManager.save(tag);
		PeerDatabase pd = defaultPeer.get();
		pd.getCachedImportedTags().add(tagId);
		defaultPeer.save(pd);
		// Queue queue = QueueFactory.getDefaultQueue();
		// queue.add(withUrl("/control/task/imageData").param("minDate",
		// "0").param(
		// "maxCount", "10000").param("tagId", String.valueOf(tagId)));

	}

	@Override
	public MetaDataInfo getMetaData() {
		PeerDatabase peerDatabase = defaultPeer.get();
		MetaDataInfo dataInfo = new MetaDataInfo();
		dataInfo.setInstanceName(peerDatabase.getPeerName());
		dataInfo.setPeerLastSeen(peerDatabase.getPeerLastContact());
		dataInfo.setPeerPhotoCount(peerDatabase.getPeerPhotoCount());
		dataInfo.setPhotoCount(peerDatabase.getPhotoCount());
		dataInfo.setTagCount(peerDatabase.getTagCount());
		return dataInfo;
	}

	@Override
	public void importImageData() {
		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions task = buildStartJob("Image Data Import Mapper");
		addJobParam(task,
				"mapreduce.mapper.inputformat.datastoreinputformat.entitykind",
				"PhotoDO");
		queue.add(task);
		log.info("Image Data Mapper scheduled.");
	}
}
