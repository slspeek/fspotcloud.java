package fspotcloud.server.control.task.tagimport;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PhotoDataCallback;
import fspotcloud.server.control.callback.TagDataCallback;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.GetTagData;

public class DefaultDataScheduler implements DataScheduler {

	final private DataScheduler recursiveCall;
	final private int MAX_DATA_TICKS;
	final private String kind;
	final private ControllerDispatchAsync dispatch;

	@Inject
	public DefaultDataScheduler(ControllerDispatchAsync dispatch,
		@Named("maxTicks") int maxTicks,
			@Assisted String kind, @Assisted("delayedCall") DataScheduler recursiveCall) {
		super();
		this.dispatch = dispatch;
		this.kind = kind;
		MAX_DATA_TICKS = maxTicks;
		this.recursiveCall = recursiveCall;
	}

	@Override
	public void scheduleDataImport(int start, int count) {
		int countWeWillDo = -1;
		int needToScheduleCount = (int) Math.ceil((double) count
				/ (double) MAX_DATA_TICKS);
		if (needToScheduleCount > MAX_DATA_TICKS) {
			// Schedule the next task
			int maxTicksSquare = MAX_DATA_TICKS * MAX_DATA_TICKS;
			int nextStart = start + maxTicksSquare;
			int nextCount = count - maxTicksSquare;
			recursiveCall.scheduleDataImport(nextStart, nextCount);
			countWeWillDo = MAX_DATA_TICKS;
		} else {
			countWeWillDo = needToScheduleCount;
		}
		// Do our part of the job, scheduling the head
		for (int i = 0; i < countWeWillDo; i++) {
			int beginning = start + i * MAX_DATA_TICKS;
			
			if (kind.equals("Photo")) {
				throw new IllegalStateException("Do not go here.");
				
			} else if (kind.equals("Tag")) {
				GetTagData action = new GetTagData(beginning, MAX_DATA_TICKS);
				TagDataCallback callback = new TagDataCallback(null, null);
				dispatch.execute(action, callback);
			}
			
		}
	}
}
