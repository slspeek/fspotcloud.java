package fspotcloud.server.control.task.tagimport;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.TagDataCallback;
import fspotcloud.server.control.task.actions.intern.TagImportAction;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetTagData;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class TagImportHandler extends
		SimpleActionHandler<TagImportAction, VoidResult> {

	final private TaskQueueDispatch recursiveCall;
	final private int MAX_DATA_TICKS;
	final private ControllerDispatchAsync dispatch;

	@Inject
	public TagImportHandler(ControllerDispatchAsync dispatch,
			TaskQueueDispatch recursiveCall, @Named("maxTicks") int maxTicks) {
		this.dispatch = dispatch;
		this.recursiveCall = recursiveCall;
		MAX_DATA_TICKS = maxTicks;
	}

	public VoidResult execute(TagImportAction action, ExecutionContext context) {
		int start = action.getOffset();
		int count = action.getLimit();
		int countWeWillDo = -1;
		int needToScheduleCount = (int) Math.ceil((double) count
				/ (double) MAX_DATA_TICKS);
		if (needToScheduleCount > MAX_DATA_TICKS) {
			// Schedule the next task
			int maxTicksSquare = MAX_DATA_TICKS * MAX_DATA_TICKS;
			int nextStart = start + maxTicksSquare;
			int nextCount = count - maxTicksSquare;
			recursiveCall.execute(new TagImportAction(nextStart, nextCount),
					new NullCallback<VoidResult>());
			countWeWillDo = MAX_DATA_TICKS;
		} else {
			countWeWillDo = needToScheduleCount;
		}
		// Do our part of the job, scheduling the head
		for (int i = 0; i < countWeWillDo; i++) {
			int beginning = start + i * MAX_DATA_TICKS;
			GetTagData botAction = new GetTagData(beginning, MAX_DATA_TICKS);
			TagDataCallback callback = new TagDataCallback(null, null);
			dispatch.execute(botAction, callback);
		}
		return new VoidResult();
	}

}