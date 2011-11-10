package fspotcloud.server.control.task.tagimport;

public interface DataSchedulerFactory {
	DataScheduler get(String kind);
}
