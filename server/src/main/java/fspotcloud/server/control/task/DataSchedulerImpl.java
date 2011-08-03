package fspotcloud.server.control.task;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import fspotcloud.server.control.SchedulerInterface;

public class DataSchedulerImpl implements DataScheduler {

	final private DataScheduler recursiveCall;
	final private SchedulerInterface scheduler;
	final private int MAX_DATA_TICKS;
	final private String kind;

	@Inject
	public DataSchedulerImpl(
			SchedulerInterface scheduler, @Named("maxTicks") int maxTicks,
			@Assisted String kind, @Assisted("delayedCall") DataScheduler recursiveCall) {
		super();
		this.kind = kind;
		MAX_DATA_TICKS = maxTicks;
		this.recursiveCall = recursiveCall;
		this.scheduler = scheduler;
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
			List<String> args = new ArrayList<String>();
			args.add(String.valueOf(beginning));
			args.add(String.valueOf(MAX_DATA_TICKS));
			scheduler.schedule("send" + kind + "Data", args);
		}
	}
}
