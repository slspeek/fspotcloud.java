package fspotcloud.server.model.photo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fspotcloud.server.util.PMF;

public class PhotoManager {
	private static final long STEP = 450;
	private static final Logger log = Logger.getLogger(PhotoManager.class
			.getName());
	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		log.warning("TagId: " + tagId);
		List<String> keys = new ArrayList<String>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Photo.class);
		query.setFilter("tagList == paramTag");
		query.declareParameters("String paramTag");// + " && imageLoaded==True");
		query.setOrdering("date");
		query.setRange(0, 30);
		List<Photo> result = (List<Photo>) query.execute(tagId);
		for(Photo photo: result) {
			keys.add(photo.getName());
		}
		pm.close();
		return keys;
	}
	
	public Photo getOrNew(String id, PersistenceManager pm) {
		Photo photo = null;
		try {
			photo = pm.getObjectById(Photo.class, id);
		} catch (JDOObjectNotFoundException notExistedYet) {
			photo = new Photo();
			photo.setName(id);
		}
		return photo;
	}
	
	public List<Photo> getOldestPhotosChunk(PersistenceManager pm) {
		Query query = pm.newQuery(Photo.class);
		query.setOrdering("date");
		query.setRange(0, STEP);
		List<Photo> result = (List<Photo>) query.execute();
		return result;
	}
}
