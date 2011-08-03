package fspotcloud.server.control.task;

public interface DataSchedulerFactory {
	DataScheduler get(String kind);
}
