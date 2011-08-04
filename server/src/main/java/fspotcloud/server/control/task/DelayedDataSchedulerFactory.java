package fspotcloud.server.control.task;

public interface DelayedDataSchedulerFactory {
	DelayedDataScheduler get(String kind);
}
