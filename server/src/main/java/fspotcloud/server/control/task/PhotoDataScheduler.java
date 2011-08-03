package fspotcloud.server.control.task;

public interface PhotoDataScheduler {
	void schedulePhotoDataImport(int offset, int limit);
}
