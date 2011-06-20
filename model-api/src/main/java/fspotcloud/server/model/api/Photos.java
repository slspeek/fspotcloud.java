package fspotcloud.server.model.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface Photos {

	@SuppressWarnings("unchecked")
	public abstract List<String> getPhotoKeysForTag(String tagId);

	public abstract List<Photo> getPhotosForTagAfter(String tagId, Date after,
			int limit);

	public abstract List<Photo> getEmptyPhotosForTagAfter(String tagId,
			Date after, int limit);

	@SuppressWarnings("unchecked")
	public abstract List<Photo> getPhotosStartingAtDate(Date minDate, int limit);

	public abstract Photo getOrNew(String id);

	@SuppressWarnings("unchecked")
	public abstract List<Photo> getOldestPhotosChunk();

	public abstract void delete(Photo p);

	public abstract void deleteAll(Collection<Photo> photos);

	public abstract void save(Photo photo);

	public abstract void saveAll(List<Photo> photoList);

	public abstract Photo getById(String id);

}