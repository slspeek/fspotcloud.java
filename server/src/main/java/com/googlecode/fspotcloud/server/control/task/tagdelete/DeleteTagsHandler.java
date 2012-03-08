package com.googlecode.fspotcloud.server.control.task.tagdelete;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteTags;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.logging.Logger;

public class DeleteTagsHandler extends SimpleActionHandler<DeleteTags, VoidResult> {

    final private static Logger log = Logger.getLogger(DeleteTagsHandler.class.getName());
    final private TaskQueueDispatch dispatchAsync;
    final private Tags tagManager;

    @Inject
    public DeleteTagsHandler(TaskQueueDispatch dispatchAsync, Tags tagManager) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.tagManager = tagManager;

    }

    @Override
    public VoidResult execute(DeleteTags action, ExecutionContext context)
            throws DispatchException {
        log.info("Delete tags entered");
        tagManager.deleteBulk(30);
        if (!tagManager.isEmpty()) {
            dispatchAsync.execute(new DeleteTags());
        }
        return new VoidResult();
    }
}