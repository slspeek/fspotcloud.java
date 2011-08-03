package fspotcloud.server.control.task;

public interface DataScheduler {
	void scheduleDataImport(int offset, int limit);
}
