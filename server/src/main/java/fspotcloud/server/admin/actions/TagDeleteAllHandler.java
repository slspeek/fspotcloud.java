package fspotcloud.server.admin.actions;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.server.control.task.actions.intern.DeleteTags;
import fspotcloud.shared.dashboard.actions.TagDeleteAll;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class TagDeleteAllHandler extends
		SimpleActionHandler<TagDeleteAll, VoidResult> {

	final private TaskQueueDispatch dispatchAsync;
	
	@Inject
	public TagDeleteAllHandler(TaskQueueDispatch dispatchAsync) {
		super();
		this.dispatchAsync = dispatchAsync;
	}
	@Override
	public VoidResult execute(TagDeleteAll action, ExecutionContext context)
			throws DispatchException {
		try {
			dispatchAsync.execute(new DeleteTags());
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}