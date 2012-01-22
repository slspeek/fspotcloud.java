package fspotcloud.server.model.api;

import java.util.List;

public interface Photos {

	Photo getOrNew(String id);

	void save(Photo photo);

	void saveAll(List<Photo> photoList);

	Photo getById(String id);

	void deleteAll(List<String> keys);
	
	void delete(String id);

}