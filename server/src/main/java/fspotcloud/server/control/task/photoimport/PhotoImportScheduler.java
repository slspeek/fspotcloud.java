package fspotcloud.server.control.task.photoimport;

public interface PhotoImportScheduler {
	void schedulePhotoImport(String tagId, String minKey, int offset, int limit);
}
