package fspotcloud.server.control.task.tagdelete;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.server.control.task.actions.intern.DeleteTags;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class DeleteTagsHandler extends
		SimpleActionHandler<DeleteTags, VoidResult> {

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
		if (!tagManager.deleteAll()) {
			dispatchAsync.execute(new DeleteTags());
		}
		return new VoidResult();
	}

}