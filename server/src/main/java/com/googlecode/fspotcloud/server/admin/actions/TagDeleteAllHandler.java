package com.googlecode.fspotcloud.server.admin.actions;

import javax.inject.Inject;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllPhotos;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteTags;
import com.googlecode.fspotcloud.shared.dashboard.actions.TagDeleteAll;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.user.AdminPermission;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

public class TagDeleteAllHandler extends SimpleActionHandler<TagDeleteAll, VoidResult> {

    final private TaskQueueDispatch dispatchAsync;
    private final AdminPermission adminPermission;

    @Inject
    public TagDeleteAllHandler(TaskQueueDispatch dispatchAsync, AdminPermission adminPermission) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(TagDeleteAll action, ExecutionContext context)
            throws DispatchException {
        adminPermission.chechAdminPermission();
        try {
            dispatchAsync.execute(new DeleteTags());
            dispatchAsync.execute(new DeleteAllPhotos());
        } catch (Exception e) {
            throw new ActionException(e);
        }
        return new VoidResult();
    }
}