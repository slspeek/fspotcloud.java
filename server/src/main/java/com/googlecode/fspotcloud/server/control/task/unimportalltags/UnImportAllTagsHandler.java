package com.googlecode.fspotcloud.server.control.task.unimportalltags;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteTags;
import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import com.googlecode.fspotcloud.server.control.task.actions.intern.UnImportAllTags;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.logging.Logger;

public class UnImportAllTagsHandler extends SimpleActionHandler<UnImportAllTags, VoidResult> {

    final private static Logger log = Logger.getLogger(UnImportAllTagsHandler.class.getName());
    final private TaskQueueDispatch dispatchAsync;
    final private Tags tagManager;

    @Inject
    public UnImportAllTagsHandler(TaskQueueDispatch dispatchAsync, Tags tagManager) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.tagManager = tagManager;

    }

    @Override
    public VoidResult execute(UnImportAllTags action, ExecutionContext context)
            throws DispatchException {
        for(Tag importedTag: tagManager.getImportedTags()) {
            dispatchAsync.execute(new UnImportTag(importedTag.getId()));
        }
        dispatchAsync.execute(new DeleteTags());
        return new VoidResult();
    }
}