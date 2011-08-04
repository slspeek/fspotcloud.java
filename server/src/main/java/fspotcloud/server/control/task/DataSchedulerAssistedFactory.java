package fspotcloud.server.control.task;

import com.google.inject.assistedinject.Assisted;

public interface DataSchedulerAssistedFactory {
	DataScheduler get(String kind, @Assisted("delayedCall") DataScheduler recursiveCall);
}
