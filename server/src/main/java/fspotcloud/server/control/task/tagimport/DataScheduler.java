package fspotcloud.server.control.task.tagimport;

public interface DataScheduler {
	void scheduleDataImport(int offset, int limit);
}
