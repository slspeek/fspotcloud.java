package fspotcloud.server.control.task.tagimport;

import com.google.inject.assistedinject.Assisted;

public interface DataSchedulerAssistedFactory {
	DataScheduler get(String kind, @Assisted("delayedCall") DataScheduler recursiveCall);
}
