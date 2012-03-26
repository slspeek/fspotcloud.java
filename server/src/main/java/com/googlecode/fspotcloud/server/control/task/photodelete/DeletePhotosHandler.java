package com.googlecode.fspotcloud.server.control.task.photodelete;

import java.util.Iterator;
import java.util.SortedSet;

import javax.inject.Inject;
import javax.inject.Named;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeletePhotos;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.TreeSet;

public class DeletePhotosHandler extends SimpleActionHandler<DeletePhotos, VoidResult> {

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
        Tag tag = tagManager.find(action.getTagId());
        Iterator<PhotoInfo> it = action.getToBoDeleted().iterator();
        for (int i = 0; i < MAX_DELETE_TICKS && it.hasNext(); i++) {
            String key = it.next().getId();
            checkForDeletion(tag, tag.getId(), key, it);
        }
        tagManager.save(tag);
        if (!action.getToBoDeleted().isEmpty()) {
            dispatchAsync.execute(action);
        }
        return new VoidResult();
    }

    private void checkForDeletion(Tag tag, String deleteTagId, String key,
            Iterator<PhotoInfo> it) {
        Photo photo = photos.find(key);
        if (photo != null) {
            boolean moreImports = false;
            for (String tagId : photo.getTagList()) {
                Tag tagRelated = tagManager.find(tagId);
                if (tagRelated != null) {
                    if (!deleteTagId.equals(tagId)) {
                        if (tagRelated.isImportIssued()) {
                            moreImports = true;
                            break;
                        }
                    }
                }
            }
            if (!moreImports) {
                photos.delete(photo);
                final TreeSet<PhotoInfo> cachedPhotoList = tag.getCachedPhotoList();
                cachedPhotoList.remove(find(tag.getCachedPhotoList(), key));
                tag.setCachedPhotoList(cachedPhotoList);
            }
        }
        it.remove();

    }

    private PhotoInfo find(SortedSet<PhotoInfo> set, String id) {
        for (PhotoInfo info : set) {
            if (info.getId().equals(id)) {
                return info;
            }
        }
        return null;
    }
}