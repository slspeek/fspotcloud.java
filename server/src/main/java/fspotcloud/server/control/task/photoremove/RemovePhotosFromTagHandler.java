package fspotcloud.server.control.task.photoremove;

import java.util.Iterator;
import java.util.SortedSet;

import javax.inject.Inject;
import javax.inject.Named;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.PhotoRemovedFromTag;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class RemovePhotosFromTagHandler extends SimpleActionHandler<RemovePhotosFromTagAction, VoidResult> {

    final private int MAX_DELETE_TICKS;
    final private TaskQueueDispatch dispatchAsync;
    final private Photos photos;
    private Tags tagManager;

    @Inject
    public RemovePhotosFromTagHandler(@Named("maxDelete") int maxDeleteTicks,
            TaskQueueDispatch dispatchAsync, Photos photos, Tags tagManager) {
        super();
        MAX_DELETE_TICKS = maxDeleteTicks;
        this.dispatchAsync = dispatchAsync;
        this.photos = photos;
        this.tagManager = tagManager;
    }

    @Override
    public VoidResult execute(RemovePhotosFromTagAction action,
            ExecutionContext context) throws DispatchException {

        Tag tag = tagManager.find(action.getTagId());
        Iterator<PhotoRemovedFromTag> it = action.getToBoDeleted().iterator();
        for (int i = 0; i < MAX_DELETE_TICKS && it.hasNext(); i++) {
            PhotoRemovedFromTag operation = it.next();

            checkForDeletion(tag, tag.getId(), operation.getPhotoId(), it);
        }
        tagManager.save(tag);
        if (!action.getToBoDeleted().isEmpty()) {
            dispatchAsync.execute(action);
        }
        return new VoidResult();
    }

    private void checkForDeletion(Tag tag, String deleteTagId, String key,
            Iterator<PhotoRemovedFromTag> it) {
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
                tag.getCachedPhotoList().remove(find(tag.getCachedPhotoList(), key));
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