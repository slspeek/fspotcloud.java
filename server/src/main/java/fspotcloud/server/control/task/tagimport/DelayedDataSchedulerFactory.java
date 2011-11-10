package fspotcloud.server.control.task.tagimport;

public interface DelayedDataSchedulerFactory {
	DelayedDataScheduler get(String kind);
}
