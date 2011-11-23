package fspotcloud.server.model.api;

import java.util.List;

public interface Photos {

	public abstract Photo getOrNew(String id);

	public abstract void save(Photo photo);

	public abstract void saveAll(List<Photo> photoList);

	public abstract Photo getById(String id);

	public abstract void deleteAll(List<String> keys);

}