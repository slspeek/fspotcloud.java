package fspotcloud.server.admin;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fspotcloud.client.admin.AdminService;
import fspotcloud.server.control.Scheduler;
import fspotcloud.server.model.batch.Batch;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.tag.Tag;
import fspotcloud.server.util.PMF;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AdminServiceImpl extends RemoteServiceServlet implements
		AdminService {

	private static final Logger log = Logger.getLogger(AdminServiceImpl.class
			.getName());

	@Override
	public void deleteAllPhotos() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Extent<Photo> extent = pm.getExtent(Photo.class);
		List<Photo> allPhotos = new ArrayList<Photo>();
		for (Photo photo : extent) {
			log.info(photo.toString());
			allPhotos.add(photo);
		}
		extent.closeAll();
		try {
			pm.deletePersistentAll(allPhotos);
			log.info("All Photos deleted.");
		} catch (Exception e) {
			log.warning("Exception during delete all Photos: " + e);
		} finally {
			pm.close();
		}
	}

	@Override
	public void deleteAllTags() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Extent<Tag> extent = pm.getExtent(Tag.class);
		List<Tag> allTags = new ArrayList<Tag>();
		for (Tag tag : extent) {
			// log.info(tag.toString());
			allTags.add(tag);
		}
		extent.closeAll();
		try {
			pm.deletePersistentAll(allTags);
			log.info("All tags deleted.");
		} catch (Exception e) {
			log.warning("Exception during delete all tags: " + e);
		} finally {
			pm.close();
		}
	}

	@Override
	public int getPhotoCount() {
		PeerDatabase pd = DefaultPeer.get();
		return pd.getCount();
	}

	@Override
	public int getTagCount() {
		PeerDatabase pd = DefaultPeer.get();
		return pd.getTagCount();
	}

	@Override
	public void importTags() {
		Scheduler.schedule("sendTagData", Collections.EMPTY_LIST);
	}

	@Override
	public long getServerPhotoCount() {
		Batch batch = new Batch("getServerPhotoCount");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(batch);
		} finally {
			pm.close();
		}
		long batchId = batch.getKey();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(url("/admin/task/photoCount").param("minDate", "0").param(
				"count", "0").param("batchId", String.valueOf(batchId)));
		return batchId;
	}
}
