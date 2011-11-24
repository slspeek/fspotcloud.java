package fspotcloud.server.admin.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.UnImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class UnImportTagHandler extends
		SimpleActionHandler<UnImportTag, VoidResult> {

	
	private static final Logger log = Logger
	.getLogger(UnImportTagHandler.class.getName());
	final private Tags tagManager;
	final private TaskQueueDispatch dispatchAsync;
	final private PeerDatabases peerDatabases;

	@Inject
	public UnImportTagHandler(Tags tagManager, TaskQueueDispatch dispatchAsync, PeerDatabases peerDatabases) {
		super();
		this.tagManager = tagManager;
		this.dispatchAsync = dispatchAsync;
		this.peerDatabases = peerDatabases;
	}
	
	@Override
	public VoidResult execute(UnImportTag action, ExecutionContext context)
			throws DispatchException {
		log.info("Executing: " + action.getTagId());
		try {
			String tagId = action.getTagId();
			Tag tag = tagManager.getById(tagId);
			List<String> keys = getKeys(tag.getCachedPhotoList());
			NullCallback<VoidResult> callback = new NullCallback<VoidResult>();
			dispatchAsync.execute(new DeletePhotos(keys), callback);
			if (tag.isImportIssued()) {
				tag.setImportIssued(false);
				tag.setCachedPhotoList(new TreeSet<PhotoInfo>());
				tag.setCount(0);
				tagManager.save(tag);
			}
			clearTreeCache();
					
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

	private List<String> getKeys(SortedSet<PhotoInfo> set) {
		List<String> result = new ArrayList<String> ();
		for (PhotoInfo info:set) {
			result.add(info.getId());
		}
		return result;
	}
	
	private void clearTreeCache() {
		PeerDatabase peer = peerDatabases.get();
		if (peer.getCachedTagTree() != null) {
			peer.setCachedTagTree(null);
			peerDatabases.save(peer);
		}
	}
}