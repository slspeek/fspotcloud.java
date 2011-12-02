package fspotcloud.server.control.task.photodelete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class DeletePhotosHandler extends
		SimpleActionHandler<DeletePhotos, VoidResult> {

	final private int MAX_DELETE_TICKS;
	final private TaskQueueDispatch dispatchAsync;
	final private Photos photos;
	private Tags tagManager;

	@Inject
	public DeletePhotosHandler(@Named("maxDelete") int maxDeleteTicks,
			TaskQueueDispatch dispatchAsync, Photos photos, Tags tagManager) {
		super();
		MAX_DELETE_TICKS = maxDeleteTicks;
		this.dispatchAsync = dispatchAsync;
		this.photos = photos;
		this.tagManager = tagManager;
	}

	@Override
	public VoidResult execute(DeletePhotos action, ExecutionContext context)
			throws DispatchException {
		Tag tag = tagManager.getById(action.getTagId());
		Iterator<PhotoInfo> it = tag.getCachedPhotoList().iterator();
		for (int i = 0; i < MAX_DELETE_TICKS && it.hasNext(); i++) {
			String key = it.next().getId();
			checkForDeletion(tag.getId(), key, it);
		}
		tagManager.save(tag);
		if (!tag.getCachedPhotoList().isEmpty()) {
			dispatchAsync.execute(action);
		}
		return new VoidResult();
	}

	private void checkForDeletion(String deleteTagId, String key,
			Iterator<PhotoInfo> it) {
		Photo photo = photos.getById(key);
		if (photo != null) {
			boolean moreImports = false;
			for (String tagId : photo.getTagList()) {
				Tag tag = tagManager.getById(tagId);
				if (tag != null) {
					if (!deleteTagId.equals(tagId)) {
						if (tag.isImportIssued()) {
							moreImports = true;
						}
					}
				}
			}
			if (!moreImports) {
				it.remove();
				List<String> keys = new ArrayList<String>();
				keys.add(key);
				photos.deleteAll(keys);
			}
		}
	}

}