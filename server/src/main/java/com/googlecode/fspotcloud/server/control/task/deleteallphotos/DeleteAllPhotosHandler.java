package com.googlecode.fspotcloud.server.control.task.deleteallphotos;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteTags;
import javax.inject.Inject;

import com.googlecode.fspotcloud.server.model.api.Photos;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllPhotos;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.logging.Logger;

public class DeleteAllPhotosHandler extends SimpleActionHandler<DeleteAllPhotos, VoidResult> {

    final private static Logger log = Logger.getLogger(DeleteAllPhotosHandler.class.getName());
    final private TaskQueueDispatch dispatchAsync;
    final private Photos photoManager;

    @Inject
    public DeleteAllPhotosHandler(TaskQueueDispatch dispatchAsync, Photos photoManager) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.photoManager = photoManager;
    }

    @Override
    public VoidResult execute(DeleteAllPhotos action, ExecutionContext context)
            throws DispatchException {
        photoManager.deleteBulk(30);
        if (!photoManager.isEmpty()) {
            dispatchAsync.execute(new DeleteAllPhotos());
        }
        return new VoidResult();
    }
}