package fspotcloud.server.model.photo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fspotcloud.server.util.PMF;

public class PhotoManager {
	private static final long STEP = 450;

	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		List<String> keys = new ArrayList<String>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Photo.class);
		query.setFilter("tagList==" + tagId + " && imageLoaded==true");
		query.setOrdering("date");
		List<Photo> result = (List<Photo>) query.execute();
		for(Photo photo: result) {
			keys.add(photo.getName());
		}
		return keys;
	}
	
	public List<Photo> getOldestPhotosChunk(PersistenceManager pm) {
		Query query = pm.newQuery(Photo.class);
		query.setOrdering("date");
		query.setRange(0, STEP);
		List<Photo> result = (List<Photo>) query.execute();
		return result;
	}
}
